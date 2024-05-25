package a3d.scene;

import a3d.Color;
import a3d.Ray;
import a3d.Vec3D;
import a3d.object.Object;
import a3d.object.sphere.light.Light;

import java.util.ArrayList;

import static a3d.Main.REFLECTION_NB;

/**
 *  Basic class to represent a scene.
 *  @author HERVOUET Léo
 *  @version 1.0
 */
public class Scene {

    public static final int LEVEL_MAX = REFLECTION_NB;
    public static final double EPSILON = 0.001;
    public static final double SHADOW_COLOR_DIVIDER = 8;
    protected ArrayList<Object> m_objects;
    protected ArrayList<Light> m_lights;
    protected final Color m_ambientLight;

    /** A scene that containt objects, light sources and have an ambient light.
     *  @param ambientLight The ambient light of the scene.
     */
    public Scene(Color ambientLight)
    {
        m_objects = new ArrayList<>();
        m_lights = new ArrayList<>();
        m_ambientLight = ambientLight;
    }

    /** To find a color for a ray.
     *  @param ray The ray to find the color.
     *  @param level The level of propagation.
     *  @return The color that the ray touch.
     */
    public Color findColor(Ray ray, int level)
    {
        if(level > LEVEL_MAX)
        {
            return new Color(0, 0, 0, 0);
        }
        double minLambda = Double.MAX_VALUE;
        Object minObj = null;

        for(Object obj : m_objects)
        {
            Double lambda = obj.computeIntersection(ray);
            if(lambda != null && lambda < minLambda && lambda > EPSILON)
            {
                minLambda = lambda;
                minObj = obj;
            }
        }
        if(minObj == null)
        {
            return new Color(0, 0, 0, 0);
        }
        if(minObj instanceof Light)
        {
            return new Color(minObj.m_color, true);
        }

        Vec3D intersectionPoint = Vec3D.add(ray.m_startingPoint, Vec3D.scale(ray.m_direction, minLambda));
        Color colorIntersectionPoint = minObj.getColor(intersectionPoint);
        Color color = Color.multiply(colorIntersectionPoint, m_ambientLight);
        boolean shadows = true;

        for(Light light : m_lights)
        {
            Vec3D lightDir = Vec3D.sub(light.m_position, intersectionPoint);
            Ray shadingRay = new Ray(Vec3D.add(intersectionPoint, Vec3D.scale(lightDir, EPSILON)), new Vec3D(lightDir));
            boolean visible = true;

            for(Object obj : m_objects)
            {
                if(!(obj instanceof Light) && minObj != obj){
                    Double lambda = obj.computeIntersection(shadingRay);
                    if(lambda != null && (EPSILON < lambda && lambda < 1))
                    {
                        visible = false;
                        break;
                    }
                }
            }

            if(visible)
            {
                shadows = false;
                Vec3D objNormal = minObj.computeIntersectionNormal(intersectionPoint);
                Vec3D intersectionPointNormal = Vec3D.normalize(intersectionPoint);
                Vec3D lightDirNormal = Vec3D.normalize(lightDir);

                color.add(Color.multiply(colorIntersectionPoint, Color.multiply(light.m_color, objNormal.dotProduct(lightDirNormal))));
                Vec3D h = Vec3D.sub(lightDirNormal, intersectionPointNormal);
                double v = Math.pow(Math.max(objNormal.dotProduct(h), 0D), minObj.m_shininess);
                color.add(Color.multiply(light.m_specular, Color.multiply(minObj.m_specular, v)));

                if(minObj.m_coeffReflection > 0.0){
                    Ray ref = new Ray(new Vec3D(intersectionPoint), Vec3D.sub(ray.m_direction, Vec3D.scale(objNormal, 2 * (objNormal.dotProduct(ray.m_direction)))));
                    color.add(Color.multiply(findColor(ref, level + 1), minObj.m_coeffReflection));
                }
            }
            else{
                color.add(Color.multiply(colorIntersectionPoint, Color.multiply(m_ambientLight,0.2))); // Add ambient shadow color
            }
        }

        if(shadows){
            color.div(SHADOW_COLOR_DIVIDER);
        }

        return color;
    }

    /** Add the light to the objects and lights lists.
     *  @param light The light to add to the scene.
     */
    public void addLight(Light light)
    {
        m_objects.add(light);
        m_lights.add(light);
    }

}

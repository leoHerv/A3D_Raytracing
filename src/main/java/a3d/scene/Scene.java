package a3d.scene;

import a3d.Color;
import a3d.Ray;
import a3d.Vec3D;
import a3d.object.Object;
import a3d.object.sphere.light.Light;

import java.util.ArrayList;

/**
 *  Basic class to represent a scene.
 *  @author HERVOUET Léo
 *  @version 1.0
 */
public class Scene {

    public static final int LEVEL_MAX = 10;
    public static final double EPSILON = 0;
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
            if(lambda != null && lambda < minLambda && lambda > 0)
            {
                minLambda = lambda;
                minObj = obj;
            }
        }
        if(minObj == null)
        {
            return new Color(0, 0, 0, 0);
        }

        Vec3D intersectionPoint = Vec3D.add(ray.m_startingPoint, Vec3D.scale(ray.m_direction, minLambda));
        Color color = Color.multiply(minObj.m_color, m_ambientLight);

        /*

        for(Light light : m_lights)
        {
            Vec3D lightDir = Vec3D.sub(light.m_position, intersectionPoint);
            Ray shading = new Ray(intersectionPoint, Vec3D.sub(light.m_position, intersectionPoint));
            boolean visible = true;

            for(Object obj : m_objects)
            {
                Double lambda = obj.computeIntersection(shading);
                if(lambda != null && (EPSILON < lambda && lambda < 1))
                {
                    visible = false;
                }
            }

            if(visible)
            {
                color.add(Color.multiply(light.m_color, minObj.m_color)); // TODO add : * (normale . (lightDir / normeLightDir)
                Vec3D h = Vec3D.sub(lightDir, Vec3D.div(intersectionPoint, Vec3D.normalize(intersectionPoint)));
                color.add(Color.multiply(light.m_specular, minObj.m_specular)); // TODO add : * (normale . h)
            }

            // TODO Add recursion.
            //Ray ref = new Ray(intersectionPoint, ray.m_direction - 2(normale . ray.m_direction) . normale);
            //color.add(Color.multiply(minObj.m_coeffReflection, findColor(ref, level + 1));
        }
        */

        return color;
    }

}

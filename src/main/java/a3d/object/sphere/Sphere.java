package a3d.object.sphere;

import a3d.Color;
import a3d.Vec3D;
import a3d.object.Object;

/**
 *  Basic class to represent a Sphere.
 *  @author HERVOUET Léo
 *  @version 1.0
 */
public class Sphere extends Object
{
    private final double m_center;
    private final double m_radius;

    /** A Sphere with a color, a specular color, a coefficient of reflection, a center and a radius.
     *  @param color          The color of the sphere.
     *  @param specular       The specular color of the sphere.
     *  @param coeffRefection The reflection of the sphere.
     *  @param center         The center of the sphere.
     *  @param radius         The radius of the sphere.
     */
    public Sphere(Color color, Color specular, double coeffRefection, double center, double radius) {
        super(color, specular, coeffRefection);
        m_center = center;
        m_radius = radius;
    }

    /**
     *  Give the point were there is an intersection between the sphere and the ray.
     *  @param ray The ray.
     *  @return A value if an intersection exist else return null.
     */
    @Override
    public Double computeIntersection(Vec3D ray) {
        return null;
    }
}

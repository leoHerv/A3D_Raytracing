package a3d.object.sphere;

import a3d.Color;
import a3d.Ray;
import a3d.Vec3D;
import a3d.object.Object;

/**
 *  Basic class to represent a Sphere.
 *  @author HERVOUET Léo
 *  @version 1.0
 */
public class Sphere extends Object
{
    private final double m_radius;

    /** A Sphere with a color, a specular color, a coefficient of reflection, a center and a radius.
     *  @param position       The position of the sphere.
     *  @param color          The color of the sphere.
     *  @param specular       The specular color of the sphere.
     *  @param coeffRefection The reflection of the sphere.
     *  @param radius         The radius of the sphere.
     */
    public Sphere(Vec3D position, Color color, Color specular, double coeffRefection, double radius) {
        super(position, color, specular, coeffRefection);
        m_radius = radius;
    }

    /**
     *  Give the point were there is an intersection between the sphere and the ray.
     *  @param ray The ray.
     *  @return A value if an intersection exist else return null.
     */
    @Override
    public Double computeIntersection(Ray ray) {
        // TODO
        return null;
    }
}

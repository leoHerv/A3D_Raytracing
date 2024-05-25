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
     *  @param shininess      The shininess of the sphere.
     */
    public Sphere(Vec3D position, Color color, Color specular, double coeffRefection, double radius, double shininess) {
        super(position, color, specular, coeffRefection, shininess);
        m_radius = radius;
    }

    /**
     *  Give the point were there is an intersection between the sphere and the ray.
     *  @param ray The ray.
     *  @return A value if an intersection exist else return null.
     */
    @Override
    public Double computeIntersection(Ray ray) {
        Vec3D P = ray.m_startingPoint; // Point of the ray.
        Vec3D dir = ray.m_direction; // Direction of the ray.
        Vec3D C = m_position; // Center
        Vec3D CP = Vec3D.sub(P, C);


        double alpha = dir.dotProduct(dir);
        //double beta = dir.dotProduct(P) - dir.dotProduct(C);
        double beta = 2 * dir.dotProduct(CP);
        double omega = CP.dotProduct(CP) - m_radius * m_radius;

        double delta = beta * beta - 4 * alpha * omega;

        if(delta == 0){
            return (-beta) / (2 * alpha);
        }
        else if(delta > 0){
            double racineDelta = Math.sqrt(delta);
            double r1 = (-beta + racineDelta) / (2 * alpha);
            double r2 = (-beta - racineDelta) / (2 * alpha);
            return minPositive(r1, r2);
        }
        else{
            return null;
        }
    }

    /** Give the min positive value.
     * @param a The first value.
     * @param b The second value.
     * @return The minimum positive value.
     */
    private static Double minPositive(double a, double b)
    {
        // If the values are negative.
        if (a < 0 && b < 0) {
            return null;
        }
        // If just one value is positive.
        if (a >= 0 && b < 0) {
            return a;
        }
        if (a < 0 && b >= 0) {
            return b;
        }
        // If both values are positive.
        return Math.min(a, b);
    }

    /** Give the normal to the intersection with the sphere.
     *  @param intersection The intersection use to have the normal.
     *  @return The normal.
     */
    @Override
    public Vec3D computeIntersectionNormal(Vec3D intersection) {
        return Vec3D.sub(intersection, m_position).normalize();
    }
}

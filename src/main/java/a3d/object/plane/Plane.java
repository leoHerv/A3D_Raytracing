package a3d.object.plane;

import a3d.Color;
import a3d.Ray;
import a3d.Vec3D;
import a3d.object.Object;

/**
 *  Basic class to represent a Plane.
 *  @author HERVOUET Léo
 *  @version 1.0
 */
public class Plane extends Object
{
    protected final Vec3D m_normal;

    /** A Plane with a color, a specular color and a coefficient of reflection.
     *  @param color           The color of the plane.
     *  @param specular        The specular color of the plane.
     *  @param coeffReflection The reflection of the plane.
     *  @param normal          The normal to the point of the plane.
     *  @param shininess       The shininess of the plane.
     */
    public Plane(Vec3D position, Color color, Color specular, double coeffReflection, Vec3D normal, double shininess) {
        super(position, color, specular, coeffReflection, shininess);
        m_normal = normal;
    }

    /** Give the point on the ray were there is an intersection.
     *  @param ray The ray.
     *  @return A value if an intersection exist else return null.
     *
     *  Ray   : m_startingPoint(P), m_direction(D)
     *  Plane : m_position(A), m_normal(n)
     *
     *  lambda = (A.n - P.n) / D.n
     */
    @Override
    public Double computeIntersection(Ray ray)
    {
        double division = ray.m_direction.dotProduct(m_normal);
        if(division == 0){
            return null;
        }
        return (m_position.dotProduct(m_normal) - ray.m_startingPoint.dotProduct(m_normal)) / division;
    }

    /** Give the normal to the intersection with the plane.
     *  @param intersection The intersection use to have the normal.
     *  @return The normal.
     */
    @Override
    public Vec3D computeIntersectionNormal(Vec3D intersection)
    {
        return new Vec3D(m_normal).normalize();
    }
}

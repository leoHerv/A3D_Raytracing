package a3d.object.plane;

import a3d.Color;
import a3d.Vec3D;
import a3d.object.Object;

/**
 *  Basic class to represent a Plane.
 *  @author HERVOUET Léo
 *  @version 1.0
 */
public class Plane extends Object
{
    private final Vec3D m_point;
    private final Vec3D m_normal;


    /** A Plane with a color, a specular color and a coefficient of reflection.
     *  @param color          The color of the plane.
     *  @param specular       The specular color of the plane.
     *  @param coeffRefection The reflection of the plane.
     *  @param point          The point of the plane.
     *  @param normal         The normal to the point of the plane.
     */
    public Plane(Color color, Color specular, double coeffRefection, Vec3D point, Vec3D normal) {
        super(color, specular, coeffRefection);
        m_point = point;
        m_normal = normal;
    }

    /** Give the point on the ray were there is an intersection.
     *  @param ray The ray.
     *  @return A value if an intersection exist else return null.
     */
    @Override
    public Double computeIntersection(Vec3D ray) {
        // TODO
        return null;
    }
}

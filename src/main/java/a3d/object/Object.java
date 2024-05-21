package a3d.object;

import a3d.Color;
import a3d.Ray;
import a3d.Vec3D;

/**
 *  Basic class to represent an Object.
 *  @author HERVOUET Léo
 *  @version 1.0
 */
public abstract class Object
{
    public final Vec3D m_position;
    public final Color m_color;
    public final Color m_specular;
    public final double m_coeffReflection;

    /**
     *  An Object with a color, a specular color and a coefficient of reflection.
     *  @param position The position of the object.
     *  @param color The color of the object.
     *  @param specular The specular color of the object.
     *  @param coeffReflection The reflection of the object.
     */
    public Object(Vec3D position, Color color, Color specular, double coeffReflection)
    {
        m_position = position;
        m_color = color;
        m_specular = specular;
        m_coeffReflection = coeffReflection;
    }

    /** Give the point on the ray were there is an intersection.
     *  @param ray The ray.
     *  @return A value if an intersection exist else return null.
     */
    public abstract Double computeIntersection(Ray ray);
}

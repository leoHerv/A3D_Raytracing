package a3d.object.plane;

import a3d.Color;
import a3d.Vec3D;

/**
 *  Class to represent a Plane with a checkerboard pattern.
 *  @author HERVOUET Léo
 *  @version 1.0
 */
public class PlaneCheckerboard extends Plane
{
    private final Color m_color2;
    private final double m_size;

    /**
     *  A Plane with a checkerboard pattern, a specular color, and a coefficient of reflection.
     *  @param position        The position of the plane.
     *  @param color1          The first color of the checkerboard.
     *  @param color2          The second color of the checkerboard.
     *  @param specular        The specular color of the plane.
     *  @param coeffReflection The reflection coefficient of the plane.
     *  @param normal          The normal to the plane.
     *  @param size            The size of each square in the checkerboard pattern.
     *  @param shininess       The shininess of the plane.
     */
    public PlaneCheckerboard(Vec3D position, Color color1, Color color2, Color specular, double coeffReflection, Vec3D normal, double size, double shininess) {
        super(position, color1, specular, coeffReflection, normal, shininess);
        m_color2 = color2;
        m_size = size;
    }

    /**
     * Determine the color at a given point on the plane based on the checkerboard pattern.
     * @param point The point on the plane.
     * @return The color at the given point.
     */
    private Color getColorAt(Vec3D point)
    {
        Vec3D tangent1;
        Vec3D tangent2;

        // Find a vector that is not parallel to the normal to construct the local coordinate system.
        if (Math.abs(m_normal.x) > Math.abs(m_normal.y)) {
            tangent1 = new Vec3D(m_normal.z, 0, -m_normal.x).normalize();
        } else {
            tangent1 = new Vec3D(0, -m_normal.z, m_normal.y).normalize();
        }
        tangent2 = Vec3D.crossProduct(m_normal, tangent1).normalize();

        // Project the point onto the plane.
        double x = Vec3D.sub(point, m_position).dotProduct(tangent1) / m_size;
        double y = Vec3D.sub(point, m_position).dotProduct(tangent2) / m_size;

        if (((int)Math.floor(x) % 2 == 0) == ((int)Math.floor(y) % 2 == 0)) {
            return m_color;
        } else {
            return m_color2;
        }
    }

    /** Give the color for a point on the checkerboard plane.
     *  @param point The point were we need to get the color.
     *  @return The color for the point.
     */
    @Override
    public Color getColor(Vec3D point) {
        return getColorAt(point);
    }
}

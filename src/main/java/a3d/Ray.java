package a3d;

/**
 *  Basic class to represent a ray.
 *  @author HERVOUET Léo
 *  @version 1.0
 */
public class Ray
{
    private final Vec3D m_startingPoint;
    private final Vec3D m_direction;

    public Ray(Vec3D startingPoint, Vec3D direction)
    {
        m_startingPoint = startingPoint;
        m_direction = direction;
    }

    public Ray(Vec3D direction)
    {
        this(new Vec3D(0, 0, 0), direction);
    }
}

package a3d.scene.scenes;

import a3d.Color;
import a3d.Vec3D;
import a3d.object.plane.Plane;
import a3d.object.sphere.light.Light;
import a3d.scene.Scene;

/**
 *  Class to represent a scene with plans.
 *  @author HERVOUET Léo
 *  @version 1.0
 */
public class BasicScene extends Scene
{

    /** A scene that contain 4 plans.
     */
    public BasicScene() {
        super(new Color(1, 1, 1, 1));

        Color blue = new Color(1, 0, 0, 1);
        Color green = new Color(0, 1, 0, 1);
        Color red = new Color(0, 0, 1, 1);
        Color yellow = new Color(0, 1, 1, 1);
        Color white = new Color(1, 1, 1, 1);

        //=== Objects ===//

        // Bottom plane.
        Vec3D posBottom = new Vec3D(0, -2, 0);
        Vec3D normalBottom = new Vec3D(0, 10, 0);
        m_objects.add(new Plane(posBottom, blue, white, 0.5, normalBottom));
        /*
        // Top plane.
        Vec3D posTop = new Vec3D(0, 2, 0);
        Vec3D normalTop = new Vec3D(0, -10, 0);
        m_objects.add(new Plane(posTop, green, white, 0.5, normalTop));

        // Left plane.
        Vec3D posLeft = new Vec3D(-1, 0, 0);
        Vec3D normalLeft = new Vec3D(1, 0, -0);
        m_objects.add(new Plane(posLeft, red, white, 0.5, normalLeft));

        // Right plane.
        Vec3D posRight = new Vec3D(1, 0, 0);
        Vec3D normalRight = new Vec3D(-1, 0, 0);
        m_objects.add(new Plane(posRight, yellow, white, 0.5, normalRight));
        */
        //=== Lights ===//
        m_lights.add(new Light(new Vec3D(1, 1, 1), white, white, 1));
    }
}

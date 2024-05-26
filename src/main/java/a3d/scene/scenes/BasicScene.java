package a3d.scene.scenes;

import a3d.Color;
import a3d.Vec3D;
import a3d.object.plane.Plane;
import a3d.object.plane.PlaneCheckerboard;
import a3d.object.sphere.Sphere;
import a3d.object.sphere.light.Light;
import a3d.scene.Scene;


/**
 *  Class to represent a scene with 4 plans, 3 spheres and 1 light.
 *  @author HERVOUET Léo
 *  @version 1.0
 */
public class BasicScene extends Scene
{

    public BasicScene() {
        super(new Color(1, 1, 1, 1));

        //=== Objects ===//

        // Bottom plane.
        Vec3D posBottom = new Vec3D(0, -1, 0);
        Vec3D normalBottom = new Vec3D(0, 1, 0.2);
        m_objects.add(new PlaneCheckerboard(posBottom, BLUE, WHITE, HIGH_WHITE, 0.01, normalBottom, 0.1, 1));

        // Top plane.
        Vec3D posTop = new Vec3D(0, 1, 0);
        Vec3D normalTop = new Vec3D(0, -1, 0);
        m_objects.add(new Plane(posTop, GREEN, HIGH_WHITE, 0.01, normalTop, 1));

        // Left plane.
        Vec3D posLeft = new Vec3D(-1, -10, -10);
        Vec3D normalLeft = new Vec3D(1, 0, 0);
        m_objects.add(new PlaneCheckerboard(posLeft, RED, WHITE, HIGH_WHITE, 0.01, normalLeft, 0.1, 1));

        // Right plane.
        Vec3D posRight = new Vec3D(1, 10, 10);
        Vec3D normalRight = new Vec3D(-1, 0, 0);
        m_objects.add(new Plane(posRight, YELLOW, HIGH_WHITE, 0.01, normalRight, 1));

        // Front plane.
        Vec3D posFront = new Vec3D(0, 0, -4);
        Vec3D normalFront = new Vec3D(0, 0, 1);
        m_objects.add(new Plane(posFront, CYAN, HIGH_WHITE, 0.01, normalFront, 1));

        // Back plane.
        Vec3D posBack = new Vec3D(0, 0, 2);
        Vec3D normalBack = new Vec3D(0, 0, -1);
        m_objects.add(new Plane(posBack, PURPLE, HIGH_WHITE, 0.01, normalBack, 1));

        // Balls.
        Vec3D posBall = new Vec3D(0, 0, -3);
        m_objects.add(new Sphere(posBall, PINK, HIGH_WHITE, 0.1, 0.2, 1.5));

        Vec3D posBall1 = new Vec3D(-0.6, 0, -3);
        m_objects.add(new Sphere(posBall1, LOW_WHITE, HIGH_WHITE, 0.9, 0.2, 1));

        Vec3D posBall2 = new Vec3D(0.6, 0, -3);
        m_objects.add(new Sphere(posBall2, BLACK, HIGH_WHITE, 0.9, 0.2, 4));


        //=== Lights ===//
        this.addLight(new Light(new Vec3D(0.3, 0.3, -1.5), WHITE, LOW_WHITE));
    }
}

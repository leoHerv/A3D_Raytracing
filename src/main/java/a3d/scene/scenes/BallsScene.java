package a3d.scene.scenes;

import a3d.Color;
import a3d.Vec3D;
import a3d.object.plane.Plane;
import a3d.object.plane.PlaneCheckerboard;
import a3d.object.sphere.Sphere;
import a3d.object.sphere.light.Light;
import a3d.scene.Scene;

public class BallsScene extends Scene
{

    public BallsScene()
    {
        super(new Color(1, 1, 1, 1));

        //=== Objects ===//

        // Bottom plane.
        Vec3D posBottom = new Vec3D(0, -0.5, 0);
        Vec3D normalBottom = new Vec3D(0, 1, 0);
        m_objects.add(new PlaneCheckerboard(posBottom, BLACK, WHITE, HIGH_WHITE, 0, normalBottom, 0.1, 1));

        //=== Sky ===//
        // Front plane.
        Vec3D posFront = new Vec3D(0, 0, -1000);
        Vec3D normalFront = new Vec3D(0, 0, 1);
        m_objects.add(new Plane(posFront, CYAN, HIGH_WHITE, 0, normalFront, 1));

        // Back plane.
        Vec3D posBack = new Vec3D(0, 0, 1000);
        Vec3D normalBack = new Vec3D(0, 0, -1);
        m_objects.add(new Plane(posBack, CYAN, HIGH_WHITE, 0, normalBack, 1));

        // Top plane.
        Vec3D posTop = new Vec3D(0, 100, 0);
        Vec3D normalTop = new Vec3D(0, -1, 0);
        m_objects.add(new Plane(posTop, CYAN, HIGH_WHITE, 0, normalTop, 1));


        // Big Balls.
        Vec3D posBall1 = new Vec3D(2.5, 0.1, -6);
        m_objects.add(new Sphere(posBall1, BLACK, HIGH_WHITE, 0.95, 0.6, 1));

        Vec3D posBall2 = new Vec3D(1.5, 0.1, -7);
        m_objects.add(new Sphere(posBall2, BLUE, HIGH_WHITE, 0.7, 0.6, 1));

        // Small Balls.
        Vec3D posBall3 = new Vec3D(-0.6, -0.4, -2);
        m_objects.add(new Sphere(posBall3, PINK, HIGH_WHITE, 0.1, 0.1, 1));

        Vec3D posBall4 = new Vec3D(0.3, -0.4, -2.3);
        m_objects.add(new Sphere(posBall4, RED, HIGH_WHITE, 0.1, 0.1, 1));

        Vec3D posBall5 = new Vec3D(-0.3, -0.4, -2.5);
        m_objects.add(new Sphere(posBall5, PURPLE, HIGH_WHITE, 0, 0.1, 1));

        Vec3D posBall6 = new Vec3D(-0.4, -0.3, -4.5);
        m_objects.add(new Sphere(posBall6, YELLOW, HIGH_WHITE, 0.1, 0.2, 1));

        Vec3D posBall7 = new Vec3D(0.4, -0.2, -5.5);
        m_objects.add(new Sphere(posBall7, GREEN, HIGH_WHITE, 0.1, 0.3, 1));

        Vec3D posBall8 = new Vec3D(1.5, -0.4, -5.5);
        m_objects.add(new Sphere(posBall8, BLACK, HIGH_WHITE, 0, 0.1, 1));


        //=== Lights ===//
        this.addLight(new Light(new Vec3D(0.4, 0.3, -1.5), WHITE, LOW_WHITE));
        this.addLight(new Light(new Vec3D(-0.4, 0.6, -1.7), WHITE, LOW_WHITE));
    }

}

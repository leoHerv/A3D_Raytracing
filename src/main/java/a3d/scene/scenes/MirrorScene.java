package a3d.scene.scenes;

import a3d.Color;
import a3d.Vec3D;
import a3d.object.plane.Plane;
import a3d.object.plane.PlaneCheckerboard;
import a3d.object.sphere.Sphere;
import a3d.object.sphere.light.Light;
import a3d.scene.Scene;

public class MirrorScene extends Scene
{
    public MirrorScene()
    {
        super(new Color(1, 1, 1, 1));

        //=== Objects ===//

        // Bottom plane.
        Vec3D posBottom = new Vec3D(0, -0.5, 0);
        Vec3D normalBottom = new Vec3D(0, 1, 0);
        m_objects.add(new PlaneCheckerboard(posBottom, LOW_WHITE, WHITE, HIGH_WHITE, 0, normalBottom, 0.2, 1));

        //=== Mirror's ===//
        // Left plane.
        Vec3D posLeft = new Vec3D(-1, 0, -2);
        Vec3D normalLeft = new Vec3D(1, 0, 0);
        m_objects.add(new Plane(posLeft, RED, HIGH_WHITE, 0, normalLeft, 1));

        // Right plane.
        Vec3D posRight = new Vec3D(1, 0, -2);
        Vec3D normalRight = new Vec3D(-1, 0, 0);
        m_objects.add(new Plane(posRight, GREEN, HIGH_WHITE, 0, normalRight, 1));

        //==============//

        // Back plane.
        Vec3D posBack = new Vec3D(0, 0, 5);
        Vec3D normalBack = new Vec3D(0, 0, -1);
        m_objects.add(new Plane(posBack, BLACK, LOW_WHITE, 0.8, normalBack, 1));

        // Front plane.
        Vec3D posFront = new Vec3D(0, 0, -6);
        Vec3D normalFront = new Vec3D(0, 0, 1);
        m_objects.add(new Plane(posFront, BLACK, LOW_WHITE, 0.8, normalFront, 1));

        // Top plane.
        Vec3D posTop = new Vec3D(0, 6, 0);
        Vec3D normalTop = new Vec3D(0, -1, 0);
        m_objects.add(new Plane(posTop, BLACK, HIGH_WHITE, 0, normalTop, 1));


        // Balls.
        Vec3D posBall3 = new Vec3D(-0.5, -0.4, -3);
        m_objects.add(new Sphere(posBall3, PINK, HIGH_WHITE, 0, 0.1, 0.5));

        Vec3D posBall4 = new Vec3D(0.2, -0.3, -3.3);
        m_objects.add(new Sphere(posBall4, BLUE, HIGH_WHITE, 0, 0.2, 0.5));

        Vec3D posBall5 = new Vec3D(-0.3, -0.4, -4);
        m_objects.add(new Sphere(posBall5, PURPLE, HIGH_WHITE, 0, 0.1, 0.5));

        //=== Lights ===//
        this.addLight(new Light(new Vec3D(0.3, 0.3, -1.5), WHITE, LOW_WHITE));
        this.addLight(new Light(new Vec3D(-0.4, 0.6, -1.7), WHITE, LOW_WHITE));
    }
}
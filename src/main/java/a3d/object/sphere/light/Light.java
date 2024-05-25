package a3d.object.sphere.light;

import a3d.Color;
import a3d.Vec3D;
import a3d.object.sphere.Sphere;

/**
 *  Basic class to represent a light source.
 *  @author HERVOUET Léo
 *  @version 1.0
 */
public class Light extends Sphere
{

    /** A Light source with a color, a specular color and a radius.
     *  @param position       The position of the light.
     *  @param color          The color of the light.
     *  @param specular       The specular color of the light.
     */
    public Light(Vec3D position, Color color, Color specular) {
        super(position, color, specular, 1, 0.01, 10);
    }
}

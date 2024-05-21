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

    /** A Light source with a color, a specular color, a coefficient of reflection, a center and a radius.
     *  @param position       The position of the light.
     *  @param color          The color of the light.
     *  @param specular       The specular color of the light.
     *  @param coeffReflection The reflection of the light.
     */
    public Light(Vec3D position, Color color, Color specular, double coeffReflection) {
        super(position, color, specular, coeffReflection, 1);
    }
}

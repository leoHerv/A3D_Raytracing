package a3d;

public class Main
{
    public static void main(String[] args)
    {
        System.out.println("Hello world!");
    }
}


/* Algo:

Classes:
    Rayon : point de depart(a3d.Vec3D), direction(a3d.Vec3D)
    Couleur : double B, double V, double R
    Scene : objects(ObjectList), sources(SourceList), ambianteLight(Couleur), findColor()
    Source : position(a3d.Vec3D), couleur(Couleur), speculaire(Couleur)
    Object : couleur(Couleur), speculaire(Couleur), coeffReflexion(double), computeIntersection()

Pour tout les pixels (x,y) faire
{
    construire le rayon primaire( point de depart (0,0,0), direction(x, y, -D))
    Couleur c = a3d.scene.findColor(rayon);
    image[x][y] = c;
}


Couleur findColor(Rayon rayon, int niveau)
{
    if( niveau > niveauMax)
    {
        return Couleur(0,0,0);
    }
    double minLambda = Double.MAX_VALUE;
    Object objMin = null;
    pour tous les Object obj : this.objects faire
    {
        Double lambda = obj.computeIntersection(rayon);
        if(lambda != null && lambda < minLambda){
            minLambda = lambda;
            objMin = obj;
        }
    }

    a3d.Vec3D p = rayon.depart + minLambda * rayon.direction;
    Couleur c = a3d.object.couleur * this.ambianteLight;

    Pour toutes les sources s this.sources faire
    {
        a3d.Vec3D lightDir = s.position - p;
        Rayon ombrage = new Rayon( p , s.position - p);
        boolean visible = true;

        Pour tout les Object obj de this.objects faire
        {
            Double lambda = obj.computeIntersection(ombrage);
            if(lambda != null && (PETITE_VALUE < lambda < 1)
            {
                visible = false;
            }
        }
        if(visible)
        {
            c+= s.couleur * a3d.object.couleur * (normale . (lightDir / normeLightDir));
            h = lightDir - (p / normeP);
            c+= s.speculaire * a3d.object.speculaire * normale . h;
        }

        Rayon ref = new Rayon(p, rayon.direction - 2( normale . rayon.direction) . normale;
        c+= a3d.object.coeffReflexion * this.findColor(ref, niveau + 1);

        return c;

    }
}

Intersection Plan :

Rayon : point de depart(P) + lambda * direction
Plan : point(A), normale

lambda = (A . normale - P . normale) / direction . normale;


 */
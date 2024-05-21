package a3d;

/**
 *  Basic class to represent a color.
 *  @author HERVOUET Léo
 *  @version 1.0
 */
public class Color
{
    public double B;
    public double G;
    public double R;
    public double A;

    /** A Color with a color in bleu, green, red and alpha.
     *  @param b The blue color.
     *  @param g The green color.
     *  @param r The red color.
     *  @param a The alpha color.
     */
    public Color(double b, double g, double r, double a)
    {
        B = b;
        G = g;
        R = r;
        A = a;
    }

    public Color add(Color c)
    {
        if(B + c.B > 1){
            B = 1;
        }
        else{
            B += c.B;
        }
        if(G + c.G > 1){
            G = 1;
        }
        else{
            G += c.G;
        }
        if(R + c.R > 1){
            R = 1;
        }
        else{
            R += c.R;
        }
        if(A + c.A > 1){
            A = 1;
        }
        else{
            A += c.A;
        }
        return this;
    }

    static public Color multiply(Color c1, Color c2)
    {
        return new Color(c1.B * c2.B, c1.G * c2.G, c1.R * c2.R, c1.A * c2.A);
    }

    public Color multiply(final double value)
    {
        B *= value;
        G *= value;
        R *= value;
        A *= value;
        return this;
    }



}

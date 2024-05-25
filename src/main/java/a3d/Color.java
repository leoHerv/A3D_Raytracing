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

    public final boolean m_stable;

    /** A Color with a color in bleu, green, red, alpha and is the color is 'stable' or not.
     *  @param b The blue color.
     *  @param g The green color.
     *  @param r The red color.
     *  @param a The alpha color.
     *  @param stable Tell if the color need be change or not (usefull of the HDR).
     */
    public Color(double b, double g, double r, double a, boolean stable)
    {
        B = b;
        G = g;
        R = r;
        A = a;
        m_stable = stable;
    }

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
        m_stable = false;
    }

    /** Copy of a Color.
     *  @param stable Tell if the color need be change or not (usefull of the HDR).
     */
    public Color(final Color c, boolean stable)
    {
        B = c.B;
        G = c.G;
        R = c.R;
        A = c.A;
        m_stable = stable;
    }

    private double minMax(double v)
    {
        if(v > 1){
            if(Main.ENABLE_HDR) {
                return v; // return 1 if the HDR is deactivated.
            }
            else{
                return 1;
            }
        }
        else if(v < 0){
            return 0;
        }
        else{
            return v;
        }
    }

    public Color add(Color c)
    {
        B += c.B;
        G += c.G;
        R += c.R;
        A += c.A;

        B = minMax(B);
        G = minMax(G);
        R = minMax(R);
        A = minMax(A);

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

    static public Color multiply(final Color c, final double v)
    {
        return new Color(c.B * v, c.G * v, c.R * v, c.A * v);
    }

    public Color div(final double value)
    {
        if(value != 0){
            B /= value;
            G /= value;
            R /= value;
            A /= value;
        }
        return this;
    }



}

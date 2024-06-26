package a3d;

/**
 *  Basic class to represent 3-vectors.
 *  Based on the Vec3f class of Philippe Meseure.
 *  @author HERVOUET Léo
 *  @version 1.0
 */
public class Vec3D
{
    /**
     * x, y and z values of the current vector.
     * These are public to allow fast access and simple use.
     */
    public double x, y, z;

    /**
     * Default Constructor
     */
    public Vec3D()
    {
        this.x = this.y = this.z = 0.0;
    }

    /**
     * Constructor with initialisation
     * @param x
     * @param y
     * @param z
     */
    public Vec3D(final double x, final double y, final double z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Constructor by copy
     * @param that vector to be copied in current vector
     */
    public Vec3D(final Vec3D that)
    {
        this.x = that.x;
        this.y = that.y;
        this.z = that.z;
    }

    /**
     * Set current vector's value to 0.0
     * @return current vector
     */
    public Vec3D reset()
    {
        this.x = this.y = this.z = 0.0;
        return this;
    }

    /**
     * Copy "that" vector in current vector
     * @param that vector to be copied
     * @return current vector
     */
    public Vec3D set(final Vec3D that)
    {
        this.x = that.x;
        this.y = that.y;
        this.z = that.z;
        return this;
    }

    /**
     * Copy x, y and z in current vector
     * @param x, y, z values to place into current vector
     * @return current vector
     */
    public Vec3D set(final double x, final double y, final double z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
        return this;
    }

    /**
     * @return square of the length of current vector
     */
    public double lengthSquare()
    {
        return this.x * this.x + this.y * this.y + this.z * this.z;
    }
    /**
     * @return length of current vector
     */
    public double length()
    {
        return Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
    }

    /**
     * Normalize current vector
     * @return current vector
     */
    public Vec3D normalize()
    {
        double l = this.lengthSquare();
        if (l == 0.0) return this;
        l = Math.sqrt(l);
        return this.scale(1.0 / l);
    }

    /**
     *  Normalize a vector.
     *  @param v Any vector.
     *  @return A new vector.
     */
    static public Vec3D normalize(Vec3D v)
    {
        double l = v.lengthSquare();
        if (l == 0.0) return v;
        l = Math.sqrt(l);
        return scale(v, 1.0 / l);
    }

    /**
     * Add a vector to current vector
     * @param that any vector
     * @return current vector
     */
    public Vec3D add(final Vec3D that)
    {
        this.x += that.x;
        this.y += that.y;
        this.z += that.z;
        return this;
    }

    /** Add a vector to another vector.
     *  @param v1 Any vector.
     *  @param v2 Any vector.
     *  @return The new vector.
     */
    static public Vec3D add(final Vec3D v1, final Vec3D v2)
    {
        return new Vec3D(v1.x + v2.x, v1.y + v2.y, v1.z + v2.z);
    }

    /**
     * Add two vectors v1 and v2 and put result into current vector
     * @param v1 any vector
     * @param v2 any vector
     * @return current vector
     */
    public Vec3D setAdd(final Vec3D v1, final Vec3D v2)
    {
        this.x = v1.x + v2.x;
        this.y = v1.y + v2.y;
        this.z = v1.z + v2.z;
        return this;
    }

    /**
     * Subtract a vector to current vector
     * @param that vector to subtract
     * @return current vector
     */
    public Vec3D sub(final Vec3D that)
    {
        this.x -= that.x;
        this.y -= that.y;
        this.z -= that.z;
        return this;
    }

    /** Subtract a vector with another vector.
     *  @param v1 Any vector.
     *  @param v2 Any vector.
     *  @return The new vector.
     */
    static public Vec3D sub(final Vec3D v1, final Vec3D v2)
    {
        return new Vec3D(v1.x - v2.x, v1.y - v2.y, v1.z - v2.z);
    }

    /**
     * Subtract two vectors and put result into current vector
     * @param v1 any vector
     * @param v2 any vector
     * @return
     */
    public Vec3D setSub(final Vec3D v1, final Vec3D v2)
    {
        this.x = v1.x - v2.x;
        this.y = v1.y - v2.y;
        this.z = v1.z - v2.z;
        return this;
    }

    /**
     * Scale current vector uniformly
     * @param scale uniform scale factor
     * @return current vector
     */
    public Vec3D scale(final double scale)
    {
        this.x *= scale;
        this.y *= scale;
        this.z *= scale;
        return this;
    }

    /** Scale a vector.
     *  @param v Any vector.
     *  @param scale Uniform scale factor
     *  @return The new vector.
     */
    static public Vec3D scale(final Vec3D v, final double scale)
    {
        return new Vec3D(v.x * scale, v.y * scale, v.z * scale);
    }

    /**
     * Scale current vector with specific factors for each coordinate
     * @param scalex scale factor for x
     * @param scaley scale factor for y
     * @param scalez scale factor for z
     * @return current vector
     */
    public Vec3D scale(final double scalex, final double scaley, final double scalez)
    {
        this.x *= scalex;
        this.y *= scaley;
        this.z *= scalez;
        return this;
    }

    /**
     * Scale a given vector by a uniform scale and put result into current vector
     * @param scale scale factor
     * @param that vector to scale
     * @return current vector
     */
    public Vec3D setScale(final double scale, final Vec3D that)
    {
        this.x = scale * that.x;
        this.y = scale * that.y;
        this.z = scale * that.z;
        return this;
    }

    /**
     * Scale a given vector by factors provided in another vector and put result into current vector
     * @param v1 vector to scale
     * @param v2 scale factors for x, y and z
     * @return current vector
     */
    public Vec3D setScale(final Vec3D v1, final Vec3D v2)
    {
        this.x = v1.x * v2.x;
        this.y = v1.y * v2.y;
        this.z = v1.z * v2.z;
        return this;
    }

    /**
     * Add a given vector that is before-hand scaled, to the current vector
     * @param scale scale factor
     * @param that vector to scale and add to current vector
     * @return current vector
     */
    public Vec3D addScale(final double scale, final Vec3D that)
    {
        this.x += scale * that.x;
        this.y += scale * that.y;
        this.z += scale * that.z;
        return this;
    }

    /** Divide a vector with another vector.
     *  @param v1 Any vector.
     *  @param v2 Any vector.
     *  @return The new vector.
     */
    static public Vec3D div(final Vec3D v1, final Vec3D v2)
    {
        double nx = 0;
        double ny = 0;
        double nz = 0;
        if(v2.x != 0){
            nx = v1.x / v2.x;
        }
        if(v2.y != 0){
            ny =v1.y / v2.y;
        }
        if(v2.z != 0){
            nz = v1.z / v2.z;
        }
        return new Vec3D(nx, ny, nz);
    }

    /**
     * Multiply a given vector by a matrix and put result into current vector
     * @param mat any matrix
     * @param v any vector
     * @return current vector
     */
    public Vec3D setMatMultiply(final double[] mat, final Vec3D v)
    {
        this.x = mat[0] * v.x + mat[1] * v.y + mat[2] * v.z;
        this.y = mat[3] * v.x + mat[4] * v.y + mat[5] * v.z;
        this.z = mat[6] * v.x + mat[7] * v.y + mat[8] * v.z;
        return this;
    }

    /**
     * Multiply a given vector by the transpose of a matrix and put result into current vector
     * @param mat any matrix
     * @param v any vector
     * @return current vector
     */
    public Vec3D setTransposeMatMultiply(final double[] mat, final Vec3D v)
    {
        this.x = mat[0] * v.x + mat[3] * v.y + mat[6] * v.z;
        this.y = mat[1] * v.x + mat[4] * v.y + mat[7] * v.z;
        this.z = mat[2] * v.x + mat[5] * v.y + mat[8] * v.z;
        return this;
    }

    /**
     * Compute dot (inner) product with another vector
     * @param v vector with which dotproduct is computed
     * @return result of dot product
     */
    public double dotProduct(final Vec3D v)
    {
        return this.x * v.x + this.y * v.y + this.z * v.z;
    }

    /**
     * Fill current vector with the cross product of two vectors.
     * Take care of parameters order, cross-product is anti-commutative!
     * @param v1 First vector
     * @param v2 Second vector
     * @return current vector, filled with cross product v1*v2
     */
    public Vec3D setCrossProduct(final Vec3D v1, final Vec3D v2)
    {
        this.x = v1.y * v2.z - v1.z * v2.y;
        this.y = v1.z * v2.x - v1.x * v2.z; // take care of this value !!
        this.z = v1.x * v2.y - v1.y * v2.x;
        return this;
    }

    /**
     * Fill current vector with the cross product of two vectors.
     * Take care of parameters order, cross-product is anti-commutative!
     * @param v1 First vector
     * @param v2 Second vector
     * @return current vector, filled with cross product v1*v2
     */
    public static Vec3D crossProduct(final Vec3D v1, final Vec3D v2)
    {
        double x = v1.y * v2.z - v1.z * v2.y;
        double y = v1.z * v2.x - v1.x * v2.z; // take care of this value !!
        double z = v1.x * v2.y - v1.y * v2.x;
        return new Vec3D(x, y, z);
    }
}


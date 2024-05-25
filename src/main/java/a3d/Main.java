package a3d;

import a3d.scene.Scene;
import a3d.scene.scenes.BasicScene;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.invoke.VolatileCallSite;

public class Main
{
    /**
     *
     * @param fout : output file stream
     * @param n : short to write to disc in little endian
     */
    private static void writeShort(FileOutputStream fout,int n) throws IOException
    {
        fout.write(n&255);
        fout.write((n>>8)&255);
    }

    /**
     *
     * @param filename name of final TGA file
     * @param buffer buffer that contains the image. 3 bytes per pixel ordered this way : Blue, Green, Red
     * @param width Width of the image
     * @param height Height of the image
     * @throws FileNotFoundException
     * @throws UnsupportedEncodingException
     * @throws IOException
     */
    private static void saveTGA(String filename, byte buffer[], int width, int height) throws IOException, UnsupportedEncodingException {

        FileOutputStream fout = new FileOutputStream(new File(filename));

        fout.write(0); // Comment size, no comment
        fout.write(0); // Colormap type: No colormap
        fout.write(2); // Image type
        writeShort(fout,0); // Origin
        writeShort(fout,0); // Length
        fout.write(0); // Depth
        writeShort(fout,0); // X origin
        writeShort(fout,0); // Y origin
        writeShort(fout,width); // Width of the image
        writeShort(fout,height); // Height of the image
        fout.write(24); // Pixel size in bits (24bpp)
        fout.write(0); // Descriptor

        /* Write the buffer */
        fout.write(buffer);

        fout.close();
    }

    public static void main(String[] args)
    {
        int w=1024;
        int h=768;
        byte buffer[]=new byte[3*w*h];

        Scene basicScene = new BasicScene();

        for(int row = 0; row < w; row++){ // for each row of the image
            for(int col = 0; col < h; col++){ // for each column of the image

                int index = 3*((col*w)+row); // compute index of color for pixel (x,y) in the buffer

                // Ensure that the pixel is black
                buffer[index]=0; // blue : take care, blue is the first component !!!
                buffer[index+1]=0; // green
                buffer[index+2]=0; // red (red is the last component !!!)

                double x = ((double)row - ((double)w / 2D)) / (double)w;
                double y = ((double)col - ((double)h / 2D)) / (double)w;

                Ray ray = new Ray(new Vec3D(0, 0, 0), new Vec3D(x, y, -1));
                Color color = basicScene.findColor(ray,0);

                buffer[index] =   (byte)(color.B * (double)255); // blue
                buffer[index+1] = (byte)(color.G * (double)255); // green
                buffer[index+2] = (byte)(color.R * (double)255); // red
            }
        }
        try {
            saveTGA("basicScene.tga",buffer,w,h);
        }
        catch(Exception e)
        {
            System.err.println("TGA file not created :"+e);
        }
    }
}
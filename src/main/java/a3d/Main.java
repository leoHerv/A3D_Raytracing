package a3d;

import a3d.scene.Scene;
import a3d.scene.scenes.BasicScene;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class Main
{
    public static final boolean ENABLE_HDR = false;
    public static final int ANTI_ALIASING_SAMPLES_NB = 8;
    public static final int REFLECTION_NB = 10;

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

    private static Color averageColor(Color[] colors) {
        double r = 0;
        double g = 0;
        double b = 0;
        boolean stable = false;
        for (Color color : colors) {
            b += color.B;
            g += color.G;
            r += color.R;
            if(color.m_stable){
                stable = true;
            }
        }
        int numColors = colors.length;
        return new Color(b / numColors, g / numColors, r / numColors, 1.0, stable);
    }

    public static void main(String[] args) {
        int w = 1024;
        int h = 768;
        byte buffer[] = new byte[3 * w * h];
        double bufferHDR[] = new double[3 * w * h];

        Scene basicScene = new BasicScene();

        int samples = ANTI_ALIASING_SAMPLES_NB; // Number of samples for anti-aliasing

        HashMap<Integer, Boolean> hashMapStable = new HashMap<>();

        // Progress bar
        int totalPixels = w * h;
        AtomicInteger pixelsProcessed = new AtomicInteger(0);

        // Calculate the maximum HDR value in parallel
        double maxValueHDR = IntStream.range(0, w * h).parallel().mapToDouble(index -> {
            int row = index % w;
            int col = index / w;

            Color[] colors = new Color[samples * samples];
            int sampleIndex = 0;

            for (int i = 0; i < samples; i++) {
                for (int j = 0; j < samples; j++) {
                    double x = ((double)row + (i + 0.5) / samples - (double)w / 2D) / (double)w;
                    double y = ((double)col + (j + 0.5) / samples - (double)h / 2D) / (double)w;

                    Ray ray = new Ray(new Vec3D(0, 0, 0), new Vec3D(x, y, -1));
                    colors[sampleIndex++] = basicScene.findColor(ray, 0);
                }
            }

            Color averageColor = averageColor(colors);

            int bufferIndex = 3 * ((col * w) + row);

            bufferHDR[bufferIndex] = averageColor.B;
            bufferHDR[bufferIndex + 1] = averageColor.G;
            bufferHDR[bufferIndex + 2] = averageColor.R;
            if(averageColor.m_stable){
                hashMapStable.put(index, true);
            }

            // Update progress
            int progress = (int) ((pixelsProcessed.incrementAndGet() / (double) totalPixels) * 100);
            System.out.print("\rProgress: " + progress + "%");

            return Math.max(Math.max(averageColor.B, averageColor.G), averageColor.R);
        }).max().orElse(0);

        // Normalize HDR values and fill the buffer in parallel
        IntStream.range(0, w * h).parallel().forEach(index -> {
            int row = index % w;
            int col = index / w;
            int bufferIndex = 3 * ((col * w) + row);

            if (ENABLE_HDR && !hashMapStable.containsKey(index)) {
                buffer[bufferIndex] = (byte) Math.min((bufferHDR[bufferIndex] / maxValueHDR) * 255.0, 255.0); // blue
                buffer[bufferIndex + 1] = (byte) Math.min((bufferHDR[bufferIndex + 1] / maxValueHDR) * 255.0, 255.0); // green
                buffer[bufferIndex + 2] = (byte) Math.min((bufferHDR[bufferIndex + 2] / maxValueHDR) * 255.0, 255.0); // red
            } else {
                buffer[bufferIndex] = (byte) Math.min(bufferHDR[bufferIndex] * 255.0, 255.0); // blue
                buffer[bufferIndex + 1] = (byte) Math.min(bufferHDR[bufferIndex + 1] * 255.0, 255.0); // green
                buffer[bufferIndex + 2] = (byte) Math.min(bufferHDR[bufferIndex + 2] * 255.0, 255.0); // red
            }
        });

        try {
            saveTGA("basicScene.tga", buffer, w, h);
        } catch (Exception e) {
            System.err.println("TGA file not created :" + e);
        }
    }
}
package lab2;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageConverter {

    public static void main(String[] args) throws IOException {
        String bmpFile = "image.bmp";
        BMPImageReader bmpReader = new BMPImageReader();
        byte[] imagePixels = bmpReader.readImage(bmpFile);
        int width = 0;
        int height = 0;

        String ppmFile = "image.ppm";

        try {
            convertBmpToPpm(imagePixels, width, height, ppmFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void convertBmpToPpm(byte[] imagePixels, int width, int height, String ppmFile) throws IOException {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        int pixelIndex = 0;

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int r = imagePixels[pixelIndex++] & 0xFF;
                int g = imagePixels[pixelIndex++] & 0xFF;
                int b = imagePixels[pixelIndex++] & 0xFF;
                int rgb = (r << 16) | (g << 8) | b;
                image.setRGB(x, y, rgb);
            }
        }

        File ppmFileResult = new File(ppmFile);
        ImageIO.write(image, "PPM", ppmFileResult);

    }

}
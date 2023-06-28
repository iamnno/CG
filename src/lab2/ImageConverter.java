package lab2;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ImageConverter {

    public static void main(String[] args) throws IOException {
        String bmpFile = "test2.bmp";
        BMPImageReader bmpReader = new BMPImageReader();
        byte[] imagePixels = bmpReader.readImage(bmpFile);
        int width = bmpReader.getWidth(bmpFile);
        int height = bmpReader.getHeight(bmpFile);

        String ppmFile = "copy.ppm";

        try {
            convertBmpToPpm(imagePixels, width, height, ppmFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public static void convertBmpToPpm(byte[] imagePixels, int width, int height, String ppmFile) throws IOException {
//        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
//        int pixelIndex = 0;
//
//        for (int y = 0; y < height; y++) {
//            for (int x = 0; x < width; x++) {
//                int r = imagePixels[pixelIndex++] & 0xFF;
//                int g = imagePixels[pixelIndex++] & 0xFF;
//                int b = imagePixels[pixelIndex++] & 0xFF;
//                int rgb = (r << 16) | (g << 8) | b;
//                image.setRGB(x, y, rgb);
//            }
//        }
//
//        File ppmFileResult = new File(ppmFile);
//        ImageIO.write(image, "PPM", ppmFileResult);
//
//    }

    public static void convertBmpToPpm(byte[] imagePixels, int width, int height, String ppmFile) throws IOException {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(ppmFile);
            String ppmHeader = "P6\n" + width + " " + height + "\n255\n";
            fileOutputStream.write(ppmHeader.getBytes());

            for (int i = 0; i < imagePixels.length; i += 3) {
                int red = imagePixels[i] & 0xFF;
                int green = imagePixels[i + 1] & 0xFF;
                int blue = imagePixels[i + 2] & 0xFF;

                fileOutputStream.write((byte) red);
                fileOutputStream.write((byte) green);
                fileOutputStream.write((byte) blue);
            }

            fileOutputStream.close();
            System.out.println("Файл PPM успешно сохранен.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
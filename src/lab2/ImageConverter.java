package lab2;

import java.io.FileOutputStream;
import java.io.IOException;

public class ImageConverter {

    public static void main(String[] args) throws IOException {
        String bmpFile = "image.bmp";
        BMPImageData bmpData = new BMPImageReader().readImage(bmpFile);
        byte[] imagePixels = bmpData.getImagePixels();
        int width = bmpData.getWidth();
        int height = bmpData.getHeight();

        String ppmFile = "image.ppm";

        try {
            convertBmpToPpm(imagePixels, width, height, ppmFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
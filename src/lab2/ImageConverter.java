package lab2;

import java.io.FileOutputStream;
import java.io.IOException;

public class ImageConverter {

    public static void main(String[] args) throws IOException {
        String bmpFile = "image.bmp";
        BMPImageReader bmpReader = new BMPImageReader();
        byte[] imagePixels = bmpReader.readImage(bmpFile);
        int width = bmpReader.getWidth(bmpFile);
        int height = bmpReader.getHeight(bmpFile);

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
            reverseArray(imagePixels);

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
    public static void reverseArray(byte[] array) {
        int left = 0;
        int right = array.length - 1;

        while (left < right) {

            byte temp = array[left];
            array[left] = array[right];
            array[right] = temp;

            left++;
            right--;
        }
    }


}
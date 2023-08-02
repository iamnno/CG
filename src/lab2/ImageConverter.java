package lab2;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class ImageConverter {

    public static void main(String[] args) throws IOException {

        String inputFilePath = null;
        String goalFormat = null;
        String outputFilePath = null;

        for (String arg : args) {
            if (arg.startsWith("--source=")) {
                inputFilePath = arg.substring("--source=".length());
            } else if (arg.startsWith("--goal-format=")) {
                goalFormat = arg.substring("--goal-format=".length());
            } else if (arg.startsWith("--output=")) {
                outputFilePath = arg.substring("--output=".length());
            }
        }

        if (inputFilePath == null || goalFormat == null) {
            System.out.println("Error: Missing required arguments!");
            System.out.println("Usage: java lab2.ImageConverter --source=<sourceFile> --goal-format=<goalFormat> [--output=<outputFile>]");
            return;
        }
        if (outputFilePath == null) outputFilePath = inputFilePath.replace(".bmp",".ppm");

        if (goalFormat.equals("ppm")){
            BMPImageData bmpData = new BMPImageReader().readImage(inputFilePath);
            byte[] imagePixels = bmpData.getImagePixels();
            int width = bmpData.getWidth();
            int height = bmpData.getHeight();

            try {
                convertBmpToPpm(imagePixels, width, height, outputFilePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (goalFormat.equals("bmp")){
//            PPMImageData ppmData = new PPMImageReader().readImage(inputFilePath);
//
//            String bmpFile = (outputFilePath != null) ? outputFilePath : inputFilePath.replace(".ppm", ".bmp");
//            convertPpmToBmp(ppmData, bmpFile);
        }



    }

//    public static void convertPpmToBmp(PPMImageData ppmData, String bmpFile) throws IOException {
//        try (FileOutputStream fileOutputStream = new FileOutputStream(bmpFile)) {
//            int width = ppmData.getWidth();
//            int height = ppmData.getHeight();
//            byte[] imagePixels = ppmData.getImagePixels();
//            int imageSize = width * height * 3;
//            int imageDataOffset = 54;
//            int fileSize = imageSize + imageDataOffset;
//
//            // BMP Header (14 байт)
//            fileOutputStream.write(new byte[]{(byte) 'B', (byte) 'M'});
//            fileOutputStream.write(intToByteArray(fileSize));
//            fileOutputStream.write(new byte[]{0, 0, 0, 0});
//            fileOutputStream.write(intToByteArray(imageDataOffset));
//
//            // BMP Info Header (40 байт)
//            fileOutputStream.write(intToByteArray(40));
//            fileOutputStream.write(intToByteArray(width));
//            fileOutputStream.write(intToByteArray(height));
//            fileOutputStream.write(new byte[]{1, 0});
//            fileOutputStream.write(new byte[]{24, 0});
//            fileOutputStream.write(new byte[]{0, 0, 0, 0});
//            fileOutputStream.write(new byte[]{0, 0, 0, 0});
//            fileOutputStream.write(new byte[]{0, 0, 0, 0});
//            fileOutputStream.write(new byte[]{0, 0, 0, 0});
//            fileOutputStream.write(new byte[]{0, 0, 0, 0});
//
//            // BMP Data (24 бита на пиксель, инвертируем порядок байтов)
//            for (int i = imageSize - 3; i >= 0; i -= 3) {
//                byte blue = imagePixels[i];
//                byte green = imagePixels[i + 1];
//                byte red = imagePixels[i + 2];
//
//                fileOutputStream.write(blue);
//                fileOutputStream.write(green);
//                fileOutputStream.write(red);
//            }
//        }
//    }
//
//    private static byte[] intToByteArray(int value) {
//        return new byte[]{
//                (byte) (value & 0x000000FF),
//                (byte) ((value >> 8) & 0x000000FF),
//                (byte) ((value >> 16) & 0x000000FF),
//                (byte) ((value >> 24) & 0x000000FF)
//        };
//    }

    public static void convertBmpToPpm(byte[] imagePixels, int width, int height, String ppmFile) throws IOException {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(ppmFile);
            String ppmHeader = "P6\n" + width + " " + height + "\n255\n";
            fileOutputStream.write(ppmHeader.getBytes());
            reverseArray(imagePixels);

            for (int row = 0; row < height; row++) {
                int rowStart = row * width * 3; // Каждый пиксель представлен тремя значениями RGB (3 * ширина)
                int rowEnd = rowStart + width * 3 - 1;
                while (rowStart < rowEnd) {
                    byte temp = imagePixels[rowStart];
                    imagePixels[rowStart] = imagePixels[rowEnd];
                    imagePixels[rowEnd] = temp;
                    rowStart++;
                    rowEnd--;
                }
            }

            for (int i = 0; i < imagePixels.length; i += 3) {
                int blue = imagePixels[i] & 0xFF;
                int green = imagePixels[i + 1] & 0xFF;
                int red = imagePixels[i + 2] & 0xFF;

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
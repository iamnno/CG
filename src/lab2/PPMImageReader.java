package lab2;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.DataInputStream;

public class PPMImageReader {
    public PPMImageData readImage(String file) throws IOException {

        FileInputStream inputStream = new FileInputStream(file);
        DataInputStream dataInputStream = new DataInputStream(inputStream);
        byte[] imagePixels = null;
        PPMImageData imageData = null;

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));

            // Чтение заголовка файла PPM
            String format = reader.readLine();
            String comment = reader.readLine();
            String dimensions = reader.readLine();
            String maxColorValue = reader.readLine();

            // Разбор размеров из строки dimensions
            String[] dimensionsArr = dimensions.split(" ");
            int width = Integer.parseInt(dimensionsArr[0]);
            int height = Integer.parseInt(dimensionsArr[1]);

            StringBuilder pixelDataBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                pixelDataBuilder.append(line).append(" ");
            }
            String str = pixelDataBuilder.toString();

            imagePixels = str.getBytes();

            // Вывод результатов
            System.out.println("Формат: " + format);
            System.out.println("Высота: " + height);
            System.out.println("Ширина: " + width);
            System.out.println("Массив пикселей: " + imagePixels.length + " байт");

            imageData = new PPMImageData(imagePixels, width, height);

            reader.close();
            dataInputStream.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return imageData;
    }
}

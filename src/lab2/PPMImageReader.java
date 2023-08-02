package lab2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PPMImageReader {
//    public PPMImageData readImage(String ppmFile) throws IOException {
//        try (BufferedReader reader = new BufferedReader(new FileReader(ppmFile))) {
//            int width = 0;
//            int height = 0;
//            int maxValue = 0;
//
//            // Чтение заголовка PPM файла
//            String header = reader.readLine();
//
//            // Проверка на формат P3 (текстовый PPM)
//            if (header.startsWith("P3")) {
//                // Пропускаем комментарии, если есть
//                String line;
//                while ((line = reader.readLine()) != null && line.startsWith("#")) {
//                }
//
//                // Чтение размеров изображения
//                String[] dimensions = line.trim().split("\\s+");
//                width = Integer.parseInt(dimensions[0]);
//                height = Integer.parseInt(dimensions[1]);
//
//                // Чтение максимального значения цвета
//                maxValue = Integer.parseInt(reader.readLine());
//
//                // Чтение пикселей
//                byte[] imagePixels = new byte[width * height * 3];
//                int pixelIndex = 0;
//                while ((line = reader.readLine()) != null) {
//                    String[] pixelValues = line.trim().split("\\s+");
//                    for (String pixelValue : pixelValues) {
//                        imagePixels[pixelIndex] = (byte) Integer.parseInt(pixelValue);
//                        pixelIndex++;
//                    }
//                }
//
//                // Создаем объект PPMImageData с прочитанными данными
//                return new PPMImageData(imagePixels, width, height);
//            } else {
//                throw new IOException("Неподдерживаемый формат PPM: " + header);
//            }
//        }
//    }
}

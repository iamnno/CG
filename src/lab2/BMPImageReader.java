package lab2;

import java.io.FileInputStream;
import java.io.IOException;

public class BMPImageReader {
    public BMPImageData readImage(String file) throws IOException {

        FileInputStream inputStream = new FileInputStream(file);
        byte[] imagePixels = null;
        BMPImageData imageData = null;

        try {

            // Чтение заголовка BMP файла (54 байта)
            byte[] header = new byte[54];
            inputStream.read(header);

            // Определение ширины и высоты изображения из заголовка
            int width = byteArrayToInt(header, 18);
            int height = byteArrayToInt(header, 22);

            // Чтение пикселей изображения
            int imageSize = width * height * 3; // Размер пикселей (без выравнивания)
            imagePixels = new byte[imageSize];
            inputStream.read(imagePixels);

            // Создание объекта ImageData и передача данных
            imageData = new BMPImageData(imagePixels, width, height);


        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }

        return imageData;
    }

    private int byteArrayToInt(byte[] bytes, int offset) {
        return (bytes[offset + 3] & 0xFF) << 24 |
                (bytes[offset + 2] & 0xFF) << 16 |
                (bytes[offset + 1] & 0xFF) << 8 |
                bytes[offset] & 0xFF;
    }

}
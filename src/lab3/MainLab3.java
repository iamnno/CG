package lab3;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class MainLab3 {
    public static void main(String[] args) throws IOException {

        String inputFilePath = null;
        String outputFilePath = null;

        for (String arg : args) {
            if (arg.startsWith("--source=")) {
                inputFilePath = arg.substring("--source=".length());
            } else if (arg.startsWith("--output=")) {
                outputFilePath = arg.substring("--output=".length());
            }
        }


        int imageWidth = 800;
        int imageHeight = 600;
        int[][] image = new int[imageHeight][imageWidth];
        for (int i = 0; i < imageHeight; i++) {
            for (int j = 0; j < imageWidth; j++) {
                image[i][j] = 255;
            }
        }

        List<double[]> vertices = OBJFileReader.readOBJVertices(inputFilePath);
        List<Triangle> triangles = OBJFileReader.readOBJTriangles(inputFilePath);

        // Проходим по каждой вершине и устанавливаем соответствующий пиксель на изображении
        for (double[] vertex : vertices) {
            int x = (int) Math.round((vertex[0] + 1) * imageWidth / 2);
            int y = (int) Math.round((-vertex[1] + 1) * imageHeight / 2);
            if (x >= 0 && x < imageWidth && y >= 0 && y < imageHeight) {
                image[y][x] = 0; // черный цвет
            }
        }

        // Записываем изображение в файл PPM
        writePPMImage(outputFilePath, image);
    }

    public static void writePPMImage(String filePath, int[][] image) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            int height = image.length;
            int width = image[0].length;
            int maxColorValue = 255;

            writer.println("P3");
            writer.println(width + " " + height);
            writer.println(maxColorValue);

            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    writer.print(image[i][j] + " " + image[i][j] + " " + image[i][j] + " ");
                }
                writer.println();
            }
        }

    }
}

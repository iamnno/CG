package lab3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OBJFileReader {
    public static List<double[]> readOBJVertices(String filePath) throws IOException {
        List<double[]> vertices = new ArrayList<>();

        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        while ((line = reader.readLine()) != null) {
            if (line.startsWith("v ")) {
                String[] values = line.split("\\s+");
                double x = Double.parseDouble(values[1]);
                double y = Double.parseDouble(values[2]);
                double z = Double.parseDouble(values[3]);
                vertices.add(new double[]{x, y, z});
            }
        }
        reader.close();

        return vertices;
    }

    public static List<Triangle> readOBJTriangles(String filePath) throws IOException {
        List<Triangle> triangles = new ArrayList<>();

        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        while ((line = reader.readLine()) != null) {
            if (line.startsWith("f ")) {
                String[] indices = line.split("\\s+");
                int v1 = Integer.parseInt(indices[1]) - 1; // Индексы вершин начинаются с 1
                int v2 = Integer.parseInt(indices[2]) - 1;
                int v3 = Integer.parseInt(indices[3]) - 1;
                triangles.add(new Triangle(v1, v2, v3));
            }
        }
        reader.close();

        return triangles;
    }

}

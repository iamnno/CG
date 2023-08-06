package lab4.Scenes;

import lab1.DataStructures.Point;
import lab1.DataStructures.Vector3D;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Mesh {
    private List<Vertex> vertices;
    private List<int[]> faces;

    public Mesh() {
        vertices = new ArrayList<>();
        faces = new ArrayList<>();
    }

    // Метод для додавання вершини до мешу
    public void addVertex(double x, double y, double z) {
        vertices.add(new Vertex(x, y, z));
    }

    // Метод для додавання грані до мешу
    public void addFace(int v1, int v2, int v3) {
        int[] face = {v1, v2, v3};
        faces.add(face);
    }

    // Метод для завантаження мешу з файлу .obj
    public void loadFromObjFile(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line;

        while ((line = reader.readLine()) != null) {
            String[] parts = line.split("\\s+");
            if (parts[0].equals("v")) {
                double x = Double.parseDouble(parts[1]);
                double y = Double.parseDouble(parts[2]);
                double z = Double.parseDouble(parts[3]);
                addVertex(x, y, z);
            } else if (parts[0].equals("f")) {
                int v1 = Integer.parseInt(parts[1]) - 1;
                int v2 = Integer.parseInt(parts[2]) - 1;
                int v3 = Integer.parseInt(parts[3]) - 1;
                addFace(v1, v2, v3);
            }
        }

        reader.close();
    }

    // Метод для отримання вершин мешу
    public List<Vertex> getVertices() {
        return vertices;
    }

    // Метод для отримання граней мешу
    public List<int[]> getFaces() {
        return faces;
    }

    // Внутрішній клас для представлення вершини мешу
    private class Vertex {
        private double x;
        private double y;
        private double z;

        public Vertex(double x, double y, double z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        // Метод для отримання координати x вершини
        public double getX() {
            return x;
        }

        // Метод для отримання координати y вершини
        public double getY() {
            return y;
        }

        // Метод для отримання координати z вершини
        public double getZ() {
            return z;
        }

        // Метод для створення точки з вершини
        public Point toPoint() {
            return new Point(x, y, z);
        }

        // Метод для обчислення вектора нормалі для вершини
        public Vector3D computeNormal() {
            // В зручному випадку, де грані мешу зберігаються у порядку за годинниковою стрілкою, використовуємо кросс-продукт
            // для обчислення нормалі до грані
            List<int[]> neighboringFaces = getNeighboringFaces();
            Vector3D normalSum = new Vector3D(0, 0, 0);
            for (int[] face : neighboringFaces) {
                Point p1 = vertices.get(face[0]).toPoint();
                Point p2 = vertices.get(face[1]).toPoint();
                Point p3 = vertices.get(face[2]).toPoint();

                Vector3D v1 = p2.subtract(p1);
                Vector3D v2 = p3.subtract(p1);

                Vector3D normal = v1.crossProduct(v2).normalize();
                normalSum = normalSum.add(normal);
            }
            return normalSum.scale(1.0 / neighboringFaces.size()).normalize();
        }

        // Метод для знаходження граней, які містять поточну вершину
        private List<int[]> getNeighboringFaces() {
            List<int[]> neighboringFaces = new ArrayList<>();
            for (int[] face : faces) {
                if (faceContainsVertex(face, vertices.indexOf(this))) {
                    neighboringFaces.add(face);
                }
            }
            return neighboringFaces;
        }

        // Метод для перевірки, чи містить грань задану вершину
        private boolean faceContainsVertex(int[] face, int vertexIndex) {
            return face[0] == vertexIndex || face[1] == vertexIndex || face[2] == vertexIndex;
        }
    }
}

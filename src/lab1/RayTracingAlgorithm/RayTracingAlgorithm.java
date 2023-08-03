package lab1.RayTracingAlgorithm;

import lab1.DataStructures.Camera;
import lab1.DataStructures.DirectedLightSource;
import lab1.AlgorithmsIntersectionPoints.IntersectionForDisk;
import lab1.DataStructures.Point;
import lab1.DataStructures.Ray;
import lab1.DataStructures.Vector3D;

public class RayTracingAlgorithm {

    private static final int IMAGE_WIDTH = 20;
    private static final int IMAGE_HEIGHT = 20;

    private static final Vector3D LIGHT_DIRECTION = new Vector3D(1, 1, -1).normalize();

    public static void main(String[] args) {
        Camera camera = new Camera(new Point(0, 0, 0), new Vector3D(0, 0, 1));
        DirectedLightSource lightSource = new DirectedLightSource(LIGHT_DIRECTION, 255, 255, 255);

        char[][] image = new char[IMAGE_HEIGHT][IMAGE_WIDTH];

        for (int y = 0; y < IMAGE_HEIGHT; y++) {
            for (int x = 0; x < IMAGE_WIDTH; x++) {
                Ray ray = camera.getRayThroughPixel(x, y, IMAGE_WIDTH, IMAGE_HEIGHT);
                char pixelChar = traceRay(ray, lightSource);
                image[y][x] = pixelChar;
            }
        }

        // Виведення результатів в консоль
        for (int y = 0; y < IMAGE_HEIGHT; y++) {
            for (int x = 0; x < IMAGE_WIDTH; x++) {
                System.out.print(image[y][x] + " ");
            }
            System.out.println();
        }
    }

    private static char traceRay(Ray ray, DirectedLightSource lightSource) {
        Point[] intersections = IntersectionForDisk.findIntersection(ray, new Point(0, 0, 5), new Vector3D(0, 0, 1), 3);

        if (intersections.length == 0) {
            return ' ';
        }

        Point intersectionPoint = intersections[0];
        Vector3D normal = new Vector3D(0, 0, 1).normalize();
        Vector3D lightDirection = lightSource.getDirection().normalize();
        double dotProduct = lightDirection.dotProduct(normal);

        if (dotProduct < 0) {
            return ' ';
        } else if (dotProduct < 0.2) {
            return '.';
        } else if (dotProduct < 0.5) {
            return '*';
        } else if (dotProduct < 0.8) {
            return 'O';
        } else {
            return '#';
        }
    }
}
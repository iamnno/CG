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

        Scene scene = new Scene();

        char[][] image = new char[IMAGE_HEIGHT][IMAGE_WIDTH];

        for (int y = 0; y < IMAGE_HEIGHT; y++) {
            for (int x = 0; x < IMAGE_WIDTH; x++) {
                Ray ray = camera.getRayThroughPixel(x, y, IMAGE_WIDTH, IMAGE_HEIGHT);
                char pixelChar = traceRay(ray, lightSource, scene);
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

    // Метод для знаходження найближчого перетину зі списку перетинів
    private static Point findClosestIntersection(Point origin, Point[] intersections) {
        if (intersections == null || intersections.length == 0) {
            return null;
        }

        Point closestIntersection = intersections[0];
        double closestDistance = origin.distanceTo(closestIntersection);

        for (int i = 1; i < intersections.length; i++) {
            double distance = origin.distanceTo(intersections[i]);
            if (distance < closestDistance) {
                closestIntersection = intersections[i];
                closestDistance = distance;
            }
        }

        return closestIntersection;
    }

    // Метод для трасування променя та визначення його кольору з урахуванням тіней
    private static char traceRay(Ray ray, DirectedLightSource lightSource, Scene scene) {
        // Знаходимо перетин променя з об'єктами на сцені
        Point[] intersections = scene.findIntersections(ray);
        if (intersections.length == 0) {
            // Якщо перетинів не знайдено, то точка знаходиться в просторі і повертаємо пробіл
            return ' ';
        }

        // Знаходимо найближчу точку перетину для поточного променя
        Point closestIntersection = findClosestIntersection(ray.getOrigin(), intersections);
        if (closestIntersection == null) {
            // Якщо найближчого перетину немає, то повертаємо пробіл
            return ' ';
        }

        // Перевіряємо, чи знаходиться точка в тіні за допомогою класу Shadow
        boolean isInShadow = Shadow.isPointInShadow(closestIntersection, lightSource.getDirection(), scene);
        if (isInShadow) {
            // Якщо точка в тіні, повертаємо пробіл
            return ' ';
        }

        // Визначаємо напрямок нормалі до об'єкту
        Vector3D normal = new Vector3D(0, 0, 1).normalize();
        // Визначаємо напрямок світла
        Vector3D lightDirection = lightSource.getDirection().normalize();
        // Знаходимо скалярний добуток між нормаллю та напрямком світла
        double dotProduct = lightDirection.dotProduct(normal);

        if (dotProduct < 0) {
            // Якщо скалярний добуток менший 0, то світло падає на об'єкт ззаду, повертаємо пробіл
            return ' ';
        } else if (dotProduct < 0.2) {
            // Якщо скалярний добуток більший 0 і менший 0.2, то світло падає на об'єкт слабо, виводимо "."
            return '.';
        } else if (dotProduct < 0.5) {
            // Якщо скалярний добуток більший 0.2 і менший 0.5, то світло падає на об'єкт помірно, виводимо "*"
            return '*';
        } else if (dotProduct < 0.8) {
            // Якщо скалярний добуток більший 0.5 і менший 0.8, то світло падає на об'єкт сильно, виводимо "O"
            return 'O';
        } else {
            // Якщо скалярний добуток більший 0.8, то світло падає на об'єкт дуже сильно, виводимо "#"
            return '#';
        }
    }
}
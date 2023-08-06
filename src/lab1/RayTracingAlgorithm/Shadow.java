package lab1.RayTracingAlgorithm;

import lab1.DataStructures.Point;
import lab1.DataStructures.Ray;
import lab1.DataStructures.Vector3D;

public class Shadow {
    private static final double EPSILON = 0.001;

    // Метод для перевірки, чи знаходиться точка в тіні
    public static boolean isPointInShadow(Point point, Vector3D lightDirection, Scene scene) {
        // Створюємо промінь, що йде від точки перетину трасувального променя R1 до джерела світла
        Vector3D shadowRayDirection = lightDirection.reverse().normalize();
        Ray shadowRay = new Ray(point.add(shadowRayDirection.scale(EPSILON)), shadowRayDirection);

        // Знаходимо перетин променя R2 з об'єктами на сцені
        Point[] intersections = scene.findIntersections(shadowRay);

        // Якщо знайдено перетин, то точка знаходиться в тіні
        return intersections.length > 0;
    }
}
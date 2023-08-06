package lab1.RayTracingAlgorithm;

import lab1.DataStructures.Point;
import lab1.DataStructures.Ray;

public class Scene {

    private Object3D[] objects;

    public Scene() {
        this.objects = objects;
    }

    // Метод для знаходження точок перетину променя з усіма об'єктами на сцені
    public Point[] findIntersections(Ray ray) {
        Point[] intersections = new Point[0];

        for (Object3D object : objects) {
            Point[] objectIntersections = object.findIntersections(ray);
            intersections = mergeArrays(intersections, objectIntersections);
        }

        return intersections;
    }

    // Метод для об'єднання двох масивів точок перетину
    private Point[] mergeArrays(Point[] arr1, Point[] arr2) {
        Point[] result = new Point[arr1.length + arr2.length];
        System.arraycopy(arr1, 0, result, 0, arr1.length);
        System.arraycopy(arr2, 0, result, arr1.length, arr2.length);
        return result;
    }
}
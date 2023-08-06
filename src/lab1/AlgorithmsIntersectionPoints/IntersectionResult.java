package lab1.AlgorithmsIntersectionPoints;

import lab1.DataStructures.Material;
import lab1.DataStructures.Point;
import lab1.DataStructures.Vector3D;

public class IntersectionResult {
    private boolean intersects; // Прапорець, який позначає, чи є перетин
    private double t; // Параметр t, який вказує на відстань від початку променя до точки перетину
    private Point intersectionPoint; // Точка перетину променя з об'єктом
    private Vector3D normal; // Нормаль до поверхні об'єкта в точці перетину
    private Material material; // Матеріал об'єкта в точці перетину

    public static final IntersectionResult NO_INTERSECTION = new IntersectionResult(false, 0, null, null, null);

    public IntersectionResult(boolean intersects, double t, Point intersectionPoint, Vector3D normal, Material material) {
        this.intersects = intersects;
        this.t = t;
        this.intersectionPoint = intersectionPoint;
        this.normal = normal;
        this.material = material;
    }

    // Метод для перевірки, чи є перетин
    public boolean doesIntersect() {
        return intersects;
    }

    // Метод для отримання параметру t
    public double getT() {
        return t;
    }

    // Метод для отримання точки перетину
    public Point getIntersectionPoint() {
        return intersectionPoint;
    }

    // Метод для отримання нормалі до поверхні об'єкта
    public Vector3D getNormal() {
        return normal;
    }

    // Метод для отримання матеріалу об'єкта
    public Material getMaterial() {
        return material;
    }
}

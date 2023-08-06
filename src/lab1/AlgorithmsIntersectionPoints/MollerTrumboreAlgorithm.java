package lab1.AlgorithmsIntersectionPoints;

import lab1.DataStructures.Point;
import lab1.DataStructures.Ray;
import lab1.DataStructures.Vector3D;

public class MollerTrumboreAlgorithm {

    // Метод для знаходження перетину променя з трикутником за допомогою алгоритму Мьоллера-Трамбора
    // Повертає точку перетину або null, якщо перетину немає
    public static Point findIntersection(Ray ray, Point v0, Point v1, Point v2) {
        Vector3D edge1 = v1.subtract(v0);
        Vector3D edge2 = v2.subtract(v0);

        Vector3D h = ray.getDirection().crossProduct(edge2);
        double a = edge1.dotProduct(h);

        if (a > -0.000001 && a < 0.000001) {
            return null; // Промінь паралельний площині трикутника, перетину немає
        }

        double f = 1.0 / a;
        Vector3D s = ray.getOrigin().subtract(v0);
        double u = f * s.dotProduct(h);

        if (u < 0 || u > 1) {
            return null; // Точка перетину знаходиться за межами ребра v0-v1
        }

        Vector3D q = s.crossProduct(edge1);
        double v = f * ray.getDirection().dotProduct(q);

        if (v < 0 || u + v > 1) {
            return null; // Точка перетину знаходиться за межами ребра v0-v2 або v1-v2
        }

        // Обчислення параметра t для перетину променя з трикутником
        double t = f * edge2.dotProduct(q);

        if (t > 0) {
            // Точка перетину лежить впереді променя, повертаємо координати цієї точки
            return ray.getPointAtDistance(t);
        }

        // Перетину немає
        return null;
    }
}
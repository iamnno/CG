package lab1.AlgorithmsIntersectionPoints;

import lab1.DataStructures.Point;
import lab1.DataStructures.Ray;
import lab1.DataStructures.Vector3D;

public class CrossingForSphere {
    // Поля класу
    private Point center; // Центр сфери
    private double radius; // Радіус сфери

    // Конструктор класу
    public CrossingForSphere(Point center, double radius) {
        this.center = center;
        this.radius = radius;
    }

    // Методи для отримання значень полів
    public Point getCenter() {
        return center;
    }

    public double getRadius() {
        return radius;
    }

    // Метод для знаходження точок перетину променя з сферою
    public Point[] findIntersectionPoints(Ray ray) {
        // Відстань від початкової точки променя до центра сфери
        Vector3D rayToCenter = new Vector3D(center.getX() - ray.getOrigin().getX(),
                center.getY() - ray.getOrigin().getY(),
                center.getZ() - ray.getOrigin().getZ());

        // Коефіцієнти квадратного рівняння для перетину променя з сферою
        double a = ray.getDirection().dotProduct(ray.getDirection());
        double b = -2 * rayToCenter.dotProduct(ray.getDirection());
        double c = rayToCenter.dotProduct(rayToCenter) - radius * radius;

        // Обчислення дискримінанту
        double discriminant = b * b - 4 * a * c;

        // Перевірка випадків та знаходження точок перетину
        if (discriminant < 0) {
            // Промінь не перетинає сферу
            return new Point[0];
        } else if (discriminant == 0) {
            // Промінь перетинає сферу у одній точці
            double t = -b / (2 * a);
            Point intersectionPoint = ray.getPointAtDistance(t);
            return new Point[]{intersectionPoint};
        } else {
            // Промінь перетинає сферу у двох точках
            double t1 = (-b + Math.sqrt(discriminant)) / (2 * a);
            double t2 = (-b - Math.sqrt(discriminant)) / (2 * a);
            Point intersectionPoint1 = ray.getPointAtDistance(t1);
            Point intersectionPoint2 = ray.getPointAtDistance(t2);
            return new Point[]{intersectionPoint1, intersectionPoint2};
        }
    }

    // Метод для перевірки, чи промінь перетинає сферу
    public boolean isIntersecting(Ray ray) {
        // Відстань від початкової точки променя до центра сфери
        Vector3D rayToCenter = new Vector3D(center.getX() - ray.getOrigin().getX(),
                center.getY() - ray.getOrigin().getY(),
                center.getZ() - ray.getOrigin().getZ());

        // Коефіцієнти квадратного рівняння для перетину променя з сферою
        double a = ray.getDirection().dotProduct(ray.getDirection());
        double b = -2 * rayToCenter.dotProduct(ray.getDirection());
        double c = rayToCenter.dotProduct(rayToCenter) - radius * radius;

        // Обчислення дискримінанту
        double discriminant = b * b - 4 * a * c;

        // Перевірка на перетин
        return discriminant >= 0;
    }
}
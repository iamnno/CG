package lab1.AlgorithmsIntersectionPoints;

import lab1.DataStructures.Point;
import lab1.DataStructures.Ray;
import lab1.DataStructures.Vector3D;

public class IntersectionForDisk {

    // Метод для знаходження точок перетину променя з параметричним об'єктом Диск
    public static Point[] findIntersection(Ray ray, Point center, Vector3D normal, double radius) {
        // Визначення параметрів площини, що містить диск
        double nx = normal.getX();
        double ny = normal.getY();
        double nz = normal.getZ();

        // Визначення параметрів вектору, що з'єднує початок променя та центр диска
        double cx = center.getX();
        double cy = center.getY();
        double cz = center.getZ();

        double x0 = ray.getOrigin().getX();
        double y0 = ray.getOrigin().getY();
        double z0 = ray.getOrigin().getZ();

        double dx = ray.getDirection().getX();
        double dy = ray.getDirection().getY();
        double dz = ray.getDirection().getZ();

        // Обчислення параметра t для перетину променя з площиною диска
        double t = ((cx - x0) * nx + (cy - y0) * ny + (cz - z0) * nz) / (dx * nx + dy * ny + dz * nz);

        // Перевірка, чи точка перетину лежить на промені та в межах диска
        if (t >= 0) {
            Point intersectionPoint = ray.getPointAtDistance(t);
            double distanceToCenter = intersectionPoint.distanceTo(center);
            if (distanceToCenter <= radius) {
                return new Point[]{intersectionPoint};
            }
        }

        // Якщо промінь не перетинає диск, повертаємо порожній масив
        return new Point[0];
    }
}
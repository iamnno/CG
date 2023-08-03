package lab1.AlgorithmsIntersectionPoints;

import lab1.DataStructures.Plane;
import lab1.DataStructures.Point;
import lab1.DataStructures.Ray;
import lab1.DataStructures.Vector3D;

public class CrossingForThePlane {

    // Метод для знаходження точки перетину променя з площиною
    // Якщо перетин існує, повертаємо точку перетину, інакше повертаємо null
    public static Point findIntersection(Ray ray, Plane plane) {
        Vector3D rayDirection = ray.getDirection();
        Vector3D planeNormal = new Vector3D(plane.getNx(), plane.getNy(), plane.getNz());

        double dotProduct = rayDirection.dotProduct(planeNormal);

        if (dotProduct == 0) {
            // Промінь паралельний площині, тому перетину немає
            return null;
        }

        Point planePoint = plane.getPointOnPlane();
        Vector3D pointToOrigin = new Vector3D(planePoint.getX() - ray.getOrigin().getX(),
                planePoint.getY() - ray.getOrigin().getY(),
                planePoint.getZ() - ray.getOrigin().getZ());

        double distanceToIntersection = pointToOrigin.dotProduct(planeNormal) / dotProduct;

        if (distanceToIntersection < 0) {
            // Перетин відбувається за початком променя, тому перетину немає
            return null;
        }

        // Знаходимо точку перетину
        double intersectionX = ray.getOrigin().getX() + distanceToIntersection * rayDirection.getX();
        double intersectionY = ray.getOrigin().getY() + distanceToIntersection * rayDirection.getY();
        double intersectionZ = ray.getOrigin().getZ() + distanceToIntersection * rayDirection.getZ();
        return new Point(intersectionX, intersectionY, intersectionZ);
    }
}
package lab1.DataStructures;

import lab1.AlgorithmsIntersectionPoints.IntersectionResult;

public abstract class Sphere extends Primitive {
    private Point center;
    private double radius;
    private Material material;

    private static final double EPSILON = 1e-6;

    public Sphere(Point center, double radius, Material material) {
        this.center = center;
        this.radius = radius;
        this.material = material;
    }

    public IntersectionResult intersect(Ray ray) {
        Vector3D rayDirection = ray.getDirection();
        Vector3D rayOriginToCenter = center.subtract(ray.getOrigin());

        double a = rayDirection.dotProduct(rayDirection);
        double b = 2 * rayDirection.dotProduct(rayOriginToCenter);
        double c = rayOriginToCenter.dotProduct(rayOriginToCenter) - radius * radius;

        double discriminant = b * b - 4 * a * c;

        if (discriminant < 0) {
            // Промінь не перетинає сферу
            return IntersectionResult.NO_INTERSECTION;
        }

        double t1 = (-b + Math.sqrt(discriminant)) / (2 * a);
        double t2 = (-b - Math.sqrt(discriminant)) / (2 * a);

        if (t1 > EPSILON && t2 > EPSILON) {
            // Промінь перетинає сферу в двох точках
            Point intersectionPoint = ray.getPointAtDistance(Math.min(t1, t2));
            Vector3D normal = intersectionPoint.subtract(center).normalize();
            return new IntersectionResult(true, Math.min(t1, t2), intersectionPoint, normal, material);
        } else if (t1 > EPSILON) {
            // Промінь перетинає сферу в одній точці (масштабний промінь)
            Point intersectionPoint = ray.getPointAtDistance(t1);
            Vector3D normal = intersectionPoint.subtract(center).normalize();
            return new IntersectionResult(true, t1, intersectionPoint, normal, material);
        } else if (t2 > EPSILON) {
            // Промінь перетинає сферу в одній точці (масштабний промінь)
            Point intersectionPoint = ray.getPointAtDistance(t2);
            Vector3D normal = intersectionPoint.subtract(center).normalize();
            return new IntersectionResult(true, t2, intersectionPoint, normal, material);
        } else {
            // Промінь не перетинає сферу
            return IntersectionResult.NO_INTERSECTION;
        }
    }

    public Vector3D getNormalAtPoint(Point point) {
        return point.subtract(center).normalize();
    }

    @Override
    public Material getMaterial() {
        return material;
    }
}

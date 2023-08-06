package lab1.DataStructures;

import lab1.AlgorithmsIntersectionPoints.IntersectionResult;

public abstract class Triangle extends Primitive {
    private Point vertex1;
    private Point vertex2;
    private Point vertex3;
    private Vector3D normal;
    private Material material;

    private static final double EPSILON = 1e-6;

    public Triangle(Point vertex1, Point vertex2, Point vertex3, Material material) {
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
        this.vertex3 = vertex3;
        this.material = material;

        // Обчислюємо нормаль до трикутника
        Vector3D side1 = vertex2.subtract(vertex1);
        Vector3D side2 = vertex3.subtract(vertex1);
        this.normal = side1.crossProduct(side2).normalize();
    }

    public IntersectionResult intersect(Ray ray) {
        Vector3D rayDirection = ray.getDirection();
        Vector3D vertex1ToRayOrigin = ray.getOrigin().subtract(vertex1);

        // Обчислюємо вектори для перевірки перетину
        Vector3D edge1 = vertex2.subtract(vertex1);
        Vector3D edge2 = vertex3.subtract(vertex2);
        Vector3D edge3 = vertex1.subtract(vertex3);

        // Знаходимо нормаль до площини трикутника
        Vector3D normal = edge1.crossProduct(edge2).normalize();

        // Знаходимо дотичну до площини трикутника, перпендикулярну напрямку променя
        Vector3D rayPlaneTangent = rayDirection.crossProduct(normal);

        // Обчислюємо відстань до площини трикутника
        double distanceToPlane = vertex1ToRayOrigin.dotProduct(normal) / rayDirection.dotProduct(normal);

        if (Math.abs(distanceToPlane) < EPSILON) {
            // Промінь лежить в площині трикутника, перевіряємо, чи лежить точка перетину всередині трикутника
            Point intersectionPoint = ray.getPointAtDistance(distanceToPlane);
            Vector3D edge1ToIntersection = intersectionPoint.subtract(vertex1);
            Vector3D edge2ToIntersection = intersectionPoint.subtract(vertex2);
            Vector3D edge3ToIntersection = intersectionPoint.subtract(vertex3);

            double dot1 = edge1.dotProduct(edge1ToIntersection);
            double dot2 = edge2.dotProduct(edge2ToIntersection);
            double dot3 = edge3.dotProduct(edge3ToIntersection);

            if (dot1 > 0 && dot2 > 0 && dot3 > 0) {
                // Точка перетину лежить всередині трикутника
                return new IntersectionResult(true, distanceToPlane, intersectionPoint, normal, material);
            }
        }

        // Промінь не перетинає трикутник
        return IntersectionResult.NO_INTERSECTION;
    }

    public Vector3D getNormalAtPoint(Point point) {
        return normal;
    }

    @Override
    public Material getMaterial() {
        return material;
    }
}
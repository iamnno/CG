package lab1.DataStructures;

import lab1.AlgorithmsIntersectionPoints.IntersectionResult;

public abstract class Cube extends Primitive {
    private Point center;
    private double size;
    private Material material;

    private static final double EPSILON = 1e-6;

    public Cube(Point center, double size, Material material) {
        this.center = center;
        this.size = size;
        this.material = material;
    }

    public IntersectionResult intersect(Ray ray) {
        Vector3D rayDirection = ray.getDirection();
        Vector3D rayOriginToCenter = center.subtract(ray.getOrigin());

        double tMin = Double.NEGATIVE_INFINITY;
        double tMax = Double.POSITIVE_INFINITY;

        for (int i = 0; i < 3; i++) {
            double e = 0;
            double f = 0;

            if (i == 0) {
                e = rayOriginToCenter.getX();
                f = rayDirection.getX();
            } else if (i == 1) {
                e = rayOriginToCenter.getY();
                f = rayDirection.getY();
            } else if (i == 2) {
                e = rayOriginToCenter.getZ();
                f = rayDirection.getZ();
            }

            if (Math.abs(f) > EPSILON) {
                double t1 = (e + size / 2) / f;
                double t2 = (e - size / 2) / f;

                if (t1 > t2) {
                    double temp = t1;
                    t1 = t2;
                    t2 = temp;
                }

                if (t1 > tMin) {
                    tMin = t1;
                }

                if (t2 < tMax) {
                    tMax = t2;
                }

                if (tMin > tMax) {
                    return IntersectionResult.NO_INTERSECTION;
                }

                if (tMax < 0) {
                    return IntersectionResult.NO_INTERSECTION;
                }
            } else if (-e - size / 2 > 0 || -e + size / 2 < 0) {
                return IntersectionResult.NO_INTERSECTION;
            }
        }

        double tHit = tMin > 0 ? tMin : tMax;
        Point intersectionPoint = ray.getPointAtDistance(tHit);
        Vector3D normal = calculateNormal(intersectionPoint);

        return new IntersectionResult(true, tHit, intersectionPoint, normal, material);
    }

    private Vector3D calculateNormal(Point intersectionPoint) {
        double x = intersectionPoint.getX() - center.getX();
        double y = intersectionPoint.getY() - center.getY();
        double z = intersectionPoint.getZ() - center.getZ();

        double absX = Math.abs(x);
        double absY = Math.abs(y);
        double absZ = Math.abs(z);

        if (absX > absY && absX > absZ) {
            return new Vector3D(x, 0, 0);
        } else if (absY > absZ) {
            return new Vector3D(0, y, 0);
        } else {
            return new Vector3D(0, 0, z);
        }
    }

    public Vector3D getNormalAtPoint(Point point) {
        return calculateNormal(point);
    }

    @Override
    public Material getMaterial() {
        return material;
    }
}
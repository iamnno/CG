package lab1.DataStructures;

public class BoundingBox {
    private Point minPoint; // Мінімальна точка паралелепіпеда (нижній лівий задній кут)
    private Point maxPoint; // Максимальна точка паралелепіпеда (верхній правий передній кут)

    // Конструктор для створення обмежуючого паралелепіпеда за мінімальною і максимальною точками
    public BoundingBox(Point minPoint, Point maxPoint) {
        this.minPoint = minPoint;
        this.maxPoint = maxPoint;
    }

    // Метод для перевірки, чи перетинається промінь з обмежуючим паралелепіпедом
    public boolean intersects(Ray ray) {
        double t1 = (minPoint.getX() - ray.getOrigin().getX()) / ray.getDirection().getX();
        double t2 = (maxPoint.getX() - ray.getOrigin().getX()) / ray.getDirection().getX();

        double tmin = Math.min(t1, t2);
        double tmax = Math.max(t1, t2);

        t1 = (minPoint.getY() - ray.getOrigin().getY()) / ray.getDirection().getY();
        t2 = (maxPoint.getY() - ray.getOrigin().getY()) / ray.getDirection().getY();

        tmin = Math.max(tmin, Math.min(t1, t2));
        tmax = Math.min(tmax, Math.max(t1, t2));

        t1 = (minPoint.getZ() - ray.getOrigin().getZ()) / ray.getDirection().getZ();
        t2 = (maxPoint.getZ() - ray.getOrigin().getZ()) / ray.getDirection().getZ();

        tmin = Math.max(tmin, Math.min(t1, t2));
        tmax = Math.min(tmax, Math.max(t1, t2));

        return tmax >= tmin && tmax >= 0;
    }
}
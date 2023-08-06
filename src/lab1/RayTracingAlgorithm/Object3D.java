package lab1.RayTracingAlgorithm;

import lab1.DataStructures.Point;
import lab1.DataStructures.Ray;

public abstract class Object3D {

    // Метод для знаходження точок перетину променя з об'єктом
    public abstract Point[] findIntersections(Ray ray);
}
package lab4.AcceleratingDataStructures.OctreeNode;

import lab1.DataStructures.BoundingBox;
import lab1.DataStructures.Point;
import lab1.DataStructures.Ray;
import lab1.RayTracingAlgorithm.Object3D;

import java.util.List;

public class Octree {
    private OctreeNode root; // Корінь дерева

    // Конструктор для створення пустого дерева
    public Octree(BoundingBox bounds) {
        this.root = new OctreeNode(0, bounds);
    }

    // Метод для вставки об'єкту у дерево
    public void insert(Object3D obj) {
        root.insert(obj);
    }

    // Метод для пошуку перетину променя з об'єктами у дереві
    public List<Point> findIntersections(Ray ray) {
        return root.findIntersections(ray);
    }
}
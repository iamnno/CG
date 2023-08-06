package lab4.AcceleratingDataStructures.BVHNode;

import lab1.DataStructures.Point;
import lab1.DataStructures.Ray;
import lab1.RayTracingAlgorithm.Object3D;

import java.util.List;

public class BVHTree {
    private BVHNode root; // Корінь дерева

    // Конструктор для створення пустого дерева
    public BVHTree() {
        this.root = null;
    }

    // Метод для побудови дерева за списком об'єктів
    public void buildTree(List<Object3D> objects) {
        // TODO: Додати логіку побудови дерева
    }

    // Метод для вставки об'єкту у дерево
    private void insert(Object3D obj, BVHNode node) {
        // TODO: Додати логіку вставки об'єкту у дерево
    }

    // Метод для пошуку перетину променя з об'єктами у дереві
    public List<Point> findIntersections(Ray ray) {
        return root.findIntersections(ray);
    }
}
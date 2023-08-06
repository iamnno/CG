package lab4.AcceleratingDataStructures.KDTree;

import lab1.DataStructures.Point;
import lab1.DataStructures.Ray;
import lab1.RayTracingAlgorithm.Object3D;

import java.util.List;

public class KDTree {
    private KDTreeNode root; // Корінь дерева

    // Конструктор для створення пустого дерева
    public KDTree() {
        root = null;
    }

    // Метод для вставки об'єкту у дерево
    public void insert(Object3D obj) {
        if (root == null) {
            root = new KDTreeNode(obj);
        } else {
            root.insert(obj);
        }
    }

    // Метод для пошуку перетину променя з об'єктами у дереві
    public List<Point> findIntersections(Ray ray) {
        if (root == null) {
            return null;
        } else {
            return root.findIntersections(ray);
        }
    }
}
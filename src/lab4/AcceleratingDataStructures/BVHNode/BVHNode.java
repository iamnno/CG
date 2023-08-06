package lab4.AcceleratingDataStructures.BVHNode;

import lab1.DataStructures.BoundingBox;
import lab1.DataStructures.Point;
import lab1.DataStructures.Ray;
import lab1.RayTracingAlgorithm.Object3D;

import java.util.List;

public class BVHNode {
    private BoundingBox bounds; // Обмежуючий паралелепіпед, що містить усі об'єкти у вузлі
    private BVHNode left; // Лівий дочірній вузол
    private BVHNode right; // Правий дочірній вузол
    private List<Object3D> objects; // Список об'єктів, що містяться у вузлі

    // Конструктор для створення вузла з обмежуючим паралелепіпедом та списком об'єктів
    public BVHNode(BoundingBox bounds, List<Object3D> objects) {
        this.bounds = bounds;
        this.objects = objects;
        this.left = null;
        this.right = null;
    }

    // Метод для перевірки, чи є вузол листком (тобто містить об'єкти)
    public boolean isLeaf() {
        return left == null && right == null;
    }

    // Метод для пошуку перетину променя з об'єктами у вузлі та його дочірніх вузлах
    public List<Point> findIntersections(Ray ray) {
        // TODO: Додати логіку пошуку перетину променя з об'єктами у вузлі та його дочірніх вузлах
        return null; // Поки повертаємо null, оскільки реалізація відсутня
    }
}
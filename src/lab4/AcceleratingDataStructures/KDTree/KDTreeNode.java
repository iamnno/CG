package lab4.AcceleratingDataStructures.KDTree;

import lab1.DataStructures.Point;
import lab1.DataStructures.Ray;
import lab1.RayTracingAlgorithm.Object3D;

import java.util.List;

public class KDTreeNode {
    private Object3D object; // Об'єкт, який міститься у вузлі
    private KDTreeNode left; // Вказівник на лівий дочірній вузол
    private KDTreeNode right; // Вказівник на правий дочірній вузол

    // Конструктор для створення вузла з заданим об'єктом
    public KDTreeNode(Object3D object) {
        this.object = object;
        this.left = null;
        this.right = null;
    }

    // Метод для отримання об'єкту, що міститься у вузлі
    public Object3D getObject() {
        return object;
    }

    // Метод для отримання лівого дочірнього вузла
    public KDTreeNode getLeft() {
        return left;
    }

    // Метод для отримання правого дочірнього вузла
    public KDTreeNode getRight() {
        return right;
    }

    // Метод для вставки об'єкту у відповідне піддерево
    public void insert(Object3D obj) {
        // TODO: Додати логіку вставки об'єкту у kd-tree
    }

    // Метод для пошуку перетину променя з об'єктами в піддереві
    public List<Point> findIntersections(Ray ray) {
        // TODO: Додати логіку пошуку перетину променя з об'єктами в піддереві
        return null; // Поки повертаємо null, оскільки реалізація відсутня
    }
}
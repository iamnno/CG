package lab4.AcceleratingDataStructures.OctreeNode;

import lab1.DataStructures.BoundingBox;
import lab1.DataStructures.Point;
import lab1.DataStructures.Ray;
import lab1.RayTracingAlgorithm.Object3D;

import java.util.ArrayList;
import java.util.List;

public class OctreeNode {
    private static final int MAX_OBJECTS = 8; // Максимальна кількість об'єктів в одному вузлі
    private static final int MAX_LEVELS = 10; // Максимальна глибина дерева

    private int level; // Рівень вузла у дереві
    private List<Object3D> objects; // Список об'єктів, що містяться у вузлі
    private BoundingBox bounds; // Обмежуючий паралелепіпед, що містить усі об'єкти у вузлі
    private OctreeNode[] children; // Масив дочірніх вузлів

    // Конструктор для створення вузла на заданому рівні та обмежуючому паралелепіпеді
    public OctreeNode(int level, BoundingBox bounds) {
        this.level = level;
        this.objects = new ArrayList<>();
        this.bounds = bounds;
        this.children = new OctreeNode[8]; // Оскільки октрі поділяє простір на 8 частин, масив матиме 8 елементів
    }

    // Метод для вставки об'єкту у дерево
    public void insert(Object3D obj) {
        // TODO: Додати логіку вставки об'єкту у октрі
    }

    // Метод для пошуку перетину променя з об'єктами у вузлі та його дочірніх вузлах
    public List<Point> findIntersections(Ray ray) {
        // TODO: Додати логіку пошуку перетину променя з об'єктами у вузлі та його дочірніх вузлах
        return null; // Поки повертаємо null, оскільки реалізація відсутня
    }
}
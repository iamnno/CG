package lab1.DataStructures;

// Клас для представлення коліру
public class Color {
    private int r; // Компонента червоного кольору (від 0 до 255)
    private int g; // Компонента зеленого кольору (від 0 до 255)
    private int b; // Компонента синього кольору (від 0 до 255)

    // Конструктор для створення коліру з заданими компонентами
    public Color(int r, int g, int b) {
        this.r = clamp(r);
        this.g = clamp(g);
        this.b = clamp(b);
    }

    // Метод для отримання компоненти червоного кольору
    public int getR() {
        return r;
    }

    // Метод для отримання компоненти зеленого кольору
    public int getG() {
        return g;
    }

    // Метод для отримання компоненти синього кольору
    public int getB() {
        return b;
    }

    // Метод для обмеження значень компонент кольору до діапазону [0, 255]
    private int clamp(int value) {
        return Math.min(Math.max(value, 0), 255);
    }
}
package lab4.Light;

import lab1.DataStructures.Color;
import lab1.DataStructures.Vector3D;

// Клас для джерела світла
public class DirectedLightSource {
    private final Vector3D direction;
    private final Color color;
    private final double intensity;

    // Конструктор для створення направленого джерела світла
    public DirectedLightSource(Vector3D direction, Color color, double intensity) {
        this.direction = direction.normalize();
        this.color = color;
        this.intensity = intensity;
    }

    // Метод для створення направленого джерела світла з заданими параметрами
    public static DirectedLightSource createDirectedLightSource(Vector3D direction, Color color, double intensity) {
        return new DirectedLightSource(direction, color, intensity);
    }

    public Vector3D getDirection() {
        return direction;
    }

    public Color getColor() {
        return color;
    }

    public double getIntensity() {
        return intensity;
    }
}
package lab4.Light;

import lab1.DataStructures.Color;

// Клас для навколишнього джерела світла
public class AmbientLightSource {
    private Color color;
    private double intensity;

    // Конструктор для створення навколишнього джерела світла з заданим коліром та інтенсивністю
    public AmbientLightSource(Color color, double intensity) {
        this.color = color;
        this.intensity = intensity;
    }

    // Метод для отримання коліру навколишнього джерела світла
    public Color getColor() {
        return color;
    }

    // Метод для отримання інтенсивності навколишнього джерела світла
    public double getIntensity() {
        return intensity;
    }
}
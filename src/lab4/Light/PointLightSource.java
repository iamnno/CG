package lab4.Light;

import lab1.DataStructures.Color;
import lab1.DataStructures.Point;

// Клас для точкового джерела світла
public class PointLightSource {
    private final Point position;
    private final Color color;
    private final double intensity;

    public PointLightSource(Point position, Color color, double intensity) {
        this.position = position;
        this.color = color;
        this.intensity = intensity;
    }

    public Point getPosition() {
        return position;
    }

    public Color getColor() {
        return color;
    }

    public double getIntensity() {
        return intensity;
    }
}
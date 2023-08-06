package lab1.DataStructures;

public class Material {
    private Color color;
    private double diffuseCoefficient;

    public Material(Color color, double diffuseCoefficient) {
        this.color = color;
        this.diffuseCoefficient = diffuseCoefficient;
    }

    // Метод для отримання кольору матеріалу
    public Color getColor() {
        return color;
    }

    // Метод для отримання коефіцієнта розсіювання світла матеріалом
    public double getDiffuseCoefficient() {
        return diffuseCoefficient;
    }
}

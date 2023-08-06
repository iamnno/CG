package lab1.DataStructures;

public abstract class Primitive {
    protected Material material;

    public Primitive(Material material) {
        this.material = material;
    }

    public Primitive() {
    }

    // Абстрактний метод для знаходження точки перетину променя з примітивом
    public abstract Point findIntersection(Ray ray);

    // Абстрактний метод для знаходження нормалі до примітиву в заданій точці
    public abstract Vector3D getNormalAt(Point point);

    // Метод для отримання матеріалу примітиву
    public Material getMaterial() {
        return material;
    }
}
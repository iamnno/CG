package lab1.DataStructures;

public class Vector3D {
    // Змінні для зберігання компонент вектора x, y та z
    private double x;
    private double y;
    private double z;

    // Конструктор для створення об'єкта Vector3D зі зазначеними компонентами
    public Vector3D(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    // Методи для отримання компонент вектора x, y та z
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    // Метод для обчислення довжини вектора (модуль)
    public double magnitude() {
        return Math.sqrt(x * x + y * y + z * z);
    }

    // Метод для додавання іншого вектора до поточного вектора
    public Vector3D add(Vector3D other) {
        return new Vector3D(this.x + other.x, this.y + other.y, this.z + other.z);
    }

    // Метод для віднімання іншого вектора від поточного вектора
    public Vector3D subtract(Vector3D other) {
        return new Vector3D(this.x - other.x, this.y - other.y, this.z - other.z);
    }

    // Метод для множення вектора на скаляр
    public Vector3D multiply(double scalar) {
        return new Vector3D(this.x * scalar, this.y * scalar, this.z * scalar);
    }

    // Метод для обчислення скалярного добутку двох векторів
    public double dotProduct(Vector3D other) {
        return this.x * other.x + this.y * other.y + this.z * other.z;
    }

    // Метод для обчислення векторного добутку двох векторів
    public Vector3D crossProduct(Vector3D other) {
        double crossX = this.y * other.z - this.z * other.y;
        double crossY = this.z * other.x - this.x * other.z;
        double crossZ = this.x * other.y - this.y * other.x;
        return new Vector3D(crossX, crossY, crossZ);
    }

    // Перевизначення методу toString для представлення вектора у вигляді рядка
    @Override
    public String toString() {
        return "(" + x + ", " + y + ", " + z + ")";
    }

    // Перевизначення методу equals для порівняння двох об'єктів Vector3D
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Vector3D other = (Vector3D) obj;
        return Double.compare(this.x, other.x) == 0 &&
                Double.compare(this.y, other.y) == 0 &&
                Double.compare(this.z, other.z) == 0;
    }

    // Перевизначення методу hashCode для правильної роботи методів, що використовують хеш-таблиці
    @Override
    public int hashCode() {
        int result = 17;
        long xBits = Double.doubleToLongBits(x);
        long yBits = Double.doubleToLongBits(y);
        long zBits = Double.doubleToLongBits(z);
        result = 31 * result + (int) (xBits ^ (xBits >>> 32));
        result = 31 * result + (int) (yBits ^ (yBits >>> 32));
        result = 31 * result + (int) (zBits ^ (zBits >>> 32));
        return result;
    }

    // Метод normalize - для нормалізації вектора, тобто зміни його довжини на 1, залишаючи його спрямованість
    public Vector3D normalize() {
        double magnitude = magnitude();
        if (magnitude != 0) {
            return new Vector3D(this.x / magnitude, this.y / magnitude, this.z / magnitude);
        } else {
            return new Vector3D(0, 0, 0); // або поверніть null або згенеруйте помилку залежно від ваших потреб
        }
    }

    // Метод angleWith - для обчислення кута між поточним вектором та іншим заданим вектором
    public double angleWith(Vector3D other) {
        double dotProduct = dotProduct(other);
        double magnitudeProduct = magnitude() * other.magnitude();
        if (magnitudeProduct == 0) {
            throw new IllegalArgumentException("One of the vectors is a zero vector.");
        }
        return Math.acos(dotProduct / magnitudeProduct);
    }

    // Метод isParallelTo - для перевірки, чи є поточний вектор паралельним до заданого вектора
    public boolean isParallelTo(Vector3D other) {
        double angle = angleWith(other);
        return angle == 0 || angle == Math.PI;
    }

    // Метод isPerpendicularTo - для перевірки, чи є поточний вектор перпендикулярним до заданого вектора
    public boolean isPerpendicularTo(Vector3D other) {
        double angle = angleWith(other);
        return angle == Math.PI / 2;
    }

    // Метод projectOnto - для проекції поточного вектора на заданий вектор
    public Vector3D projectOnto(Vector3D other) {
        double scalarProjection = dotProduct(other) / other.magnitude();
        return other.clone().multiply(scalarProjection);
    }

    // Метод clone - для створення копії поточного вектора
    @Override
    public Vector3D clone() {
        return new Vector3D(this.x, this.y, this.z);
    }

    // Метод reverse - для створення вектора, який протилежний поточному
    public Vector3D reverse() {
        return new Vector3D(-this.x, -this.y, -this.z);
    }
}
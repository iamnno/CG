package lab1.DataStructures;

public class Point {
    // Змінні для зберігання координат x, y та z
    private double x;
    private double y;
    private double z;

    // Конструктор для створення об'єкта Point зі зазначеними координатами
    public Point(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    // Методи для отримання та встановлення значень координат x, y та z
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setZ(double z) {
        this.z = z;
    }

    // Метод для переміщення точки на задані зміщення по осям x, y та z
    public void move(double deltaX, double deltaY, double deltaZ) {
        x += deltaX;
        y += deltaY;
        z += deltaZ;
    }

    // Метод для додавання вектора до точки
    public void add(Vector3D vector) {
        x += vector.getX();
        y += vector.getY();
        z += vector.getZ();
    }

    // Метод для обчислення відстані між поточною точкою та іншою точкою
    public double distanceTo(Point other) {
        double deltaX = this.x - other.x;
        double deltaY = this.y - other.y;
        double deltaZ = this.z - other.z;
        return Math.sqrt(deltaX * deltaX + deltaY * deltaY + deltaZ * deltaZ);
    }

    // Перевизначення методу toString для представлення точки у вигляді рядка
    @Override
    public String toString() {
        return "(" + x + ", " + y + ", " + z + ")";
    }

    // Перевизначення методу equals для порівняння двох об'єктів Point
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Point other = (Point) obj;
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

    // Метод distanceToOrigin - для обчислення відстані від поточної точки до початку координат (точка з координатами (0, 0, 0))
    public double distanceToOrigin() {
        return Math.sqrt(x * x + y * y + z * z);
    }

    // Метод isOrigin - для перевірки, чи є поточна точка початком координат (тобто має координати (0, 0, 0))
    public boolean isOrigin() {
        return x == 0 && y == 0 && z == 0;
    }

    // Метод mirror - для створення нової точки, яка буде знаходитись в дзеркальному положенні відносно початку координат
    public Point mirror() {
        return new Point(-x, -y, -z);
    }

    // Метод slopeTo - для обчислення коефіцієнта нахилу прямої, яка проходить через поточну точку та іншу задану точку
    public double slopeTo(Point other) {
        if (this.x == other.x && this.y == other.y) {
            // Якщо пряма паралельна площині xy, то повертаємо Double.POSITIVE_INFINITY
            return Double.POSITIVE_INFINITY;
        }
        return (other.z - this.z) / Math.sqrt(Math.pow(other.x - this.x, 2) + Math.pow(other.y - this.y, 2));
    }

    // Метод isCollinear - для перевірки, чи лежать три точки разом з поточною на одній прямій
    public boolean isCollinear(Point point1, Point point2) {
        double slope1 = this.slopeTo(point1);
        double slope2 = this.slopeTo(point2);
        return Double.compare(slope1, slope2) == 0;
    }

    // Метод clone - для створення копії поточної точки
    public Point clone() {
        return new Point(this.x, this.y, this.z);
    }
}
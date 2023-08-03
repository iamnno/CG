package lab1.DataStructures;

// Клас для представлення площини у тривимірному просторі
public class Plane {
    // Змінні для зберігання компонент нормалі площини nx, ny та nz, а також точки, через яку проходить площина
    private double nx;
    private double ny;
    private double nz;
    private Point pointOnPlane;

    // Конструктор для створення об'єкта Plane з нормаллю та точкою, через яку проходить площина
    public Plane(double nx, double ny, double nz, Point pointOnPlane) {
        this.nx = nx;
        this.ny = ny;
        this.nz = nz;
        this.pointOnPlane = pointOnPlane;
    }

    // Методи для отримання компонент нормалі площини nx, ny та nz, а також точки, через яку проходить площина
    public double getNx() {
        return nx;
    }

    public double getNy() {
        return ny;
    }

    public double getNz() {
        return nz;
    }

    public Point getPointOnPlane() {
        return pointOnPlane;
    }

    //Метод getNormal - для передавання параметрів нормалі
    public Plane getNormal() {
        this.nx = nx;
        this.ny = ny;
        this.nz = nz;
        this.pointOnPlane = pointOnPlane;
        return new Plane(this.nx, this.ny, this.nz, this.pointOnPlane);
    }

    // Метод для обчислення довжини вектора (модуль)
    public double magnitude() {
        return Math.sqrt(nx * nx + ny * ny + nz * nz);
    }
    // Метод для обчислення дистанції від площини до заданої точки
    public double distanceTo(Point point) {
        double x0 = point.getX();
        double y0 = point.getY();
        double z0 = 0;

        // Обчислення дистанції використовуючи рівняння площини Ax + By + Cz + D = 0
        double distance = Math.abs(nx * x0 + ny * y0 + nz * z0 - (nx * pointOnPlane.getX() + ny * pointOnPlane.getY() + nz * pointOnPlane.getZ()));
        return distance / Math.sqrt(nx * nx + ny * ny + nz * nz);
    }

    // Перевизначення методу toString для представлення площини у вигляді рядка
    @Override
    public String toString() {
        return nx + "x + " + ny + "y + " + nz + "z = " + (nx * pointOnPlane.getX() + ny * pointOnPlane.getY() + nz * pointOnPlane.getZ());
    }

    // Перевизначення методу equals для порівняння двох об'єктів Plane
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Plane other = (Plane) obj;
        return Double.compare(this.nx, other.nx) == 0 &&
                Double.compare(this.ny, other.ny) == 0 &&
                Double.compare(this.nz, other.nz) == 0 &&
                this.pointOnPlane.equals(other.pointOnPlane);
    }

    // Перевизначення методу hashCode для правильної роботи методів, що використовують хеш-таблиці
    @Override
    public int hashCode() {
        int result = 17;
        long nxBits = Double.doubleToLongBits(nx);
        long nyBits = Double.doubleToLongBits(ny);
        long nzBits = Double.doubleToLongBits(nz);
        result = 31 * result + (int) (nxBits ^ (nxBits >>> 32));
        result = 31 * result + (int) (nyBits ^ (nyBits >>> 32));
        result = 31 * result + (int) (nzBits ^ (nzBits >>> 32));
        result = 31 * result + pointOnPlane.hashCode();
        return result;
    }

    // Метод parallelTo - для перевірки, чи є поточна площина паралельною до заданої площини
    public boolean parallelTo(Plane other) {
        double dotProduct = nx * other.nx + ny * other.ny + nz * other.nz;
        return Math.abs(dotProduct) == 1;
    }

    // Метод perpendicularTo - для перевірки, чи є поточна площина перпендикулярною до заданої площини
    public boolean perpendicularTo(Plane other) {
        double dotProduct = nx * other.nx + ny * other.ny + nz * other.nz;
        return dotProduct == 0;
    }

    // Метод translate - для зсуву площини на вектор (тривимірний зсув)
    public void translate(Vector3D translation) {
        pointOnPlane.move(translation.getX(), translation.getY(), translation.getZ());
    }

    // Метод mirror - для отримання площини, яка є дзеркальним відображенням поточної площини відносно початку координат
    public Plane mirror() {
        double newNx = -nx;
        double newNy = -ny;
        double newNz = -nz;
        double d = -(newNx * pointOnPlane.getX() + newNy * pointOnPlane.getY() + newNz * pointOnPlane.getZ());
        return new Plane(newNx, newNy, newNz, new Point(-d * newNx, -d * newNy, -d * newNz));
    }

    // Метод intersect - для знаходження точки перетину двох площин, якщо вони не паралельні
    // (потрібно перевіряти, чи існує перетин перед викликом цього методу)
    public Point intersect(Plane other) {
        double denominator = nx * other.ny - ny * other.nx;
        double x = (ny * other.pointOnPlane.getZ() - other.ny * pointOnPlane.getZ()) / denominator;
        double y = (other.nx * pointOnPlane.getZ() - nx * other.pointOnPlane.getZ()) / denominator;
        double z = 0;
        return new Point(x, y, z);
    }
}
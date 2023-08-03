package lab1.DataStructures;

public class Normal {
    // Змінні для зберігання компонент нормалі nx, ny та nz
    private double nx;
    private double ny;
    private double nz;

    // Конструктор для створення об'єкта Normal зі зазначеними компонентами
    public Normal(double nx, double ny, double nz) {
        this.nx = nx;
        this.ny = ny;
        this.nz = nz;
    }

    // Методи для отримання компонент нормалі nx, ny та nz
    public double getNx() {
        return nx;
    }

    public double getNy() {
        return ny;
    }

    public double getNz() {
        return nz;
    }

    // Метод для обчислення довжини нормалі (модуль)
    public double magnitude() {
        return Math.sqrt(nx * nx + ny * ny + nz * nz);
    }

    // Метод для нормалізації нормалі, тобто зміни її довжини на 1, залишаючи її спрямованість
    public void normalize() {
        double magnitude = magnitude();
        if (magnitude != 0) {
            this.nx /= magnitude;
            this.ny /= magnitude;
            this.nz /= magnitude;
        }
    }

    // Перевизначення методу toString для представлення нормалі у вигляді рядка
    @Override
    public String toString() {
        return "(" + nx + ", " + ny + ", " + nz + ")";
    }

    // Перевизначення методу equals для порівняння двох об'єктів Normal
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Normal other = (Normal) obj;
        return Double.compare(this.nx, other.nx) == 0 &&
                Double.compare(this.ny, other.ny) == 0 &&
                Double.compare(this.nz, other.nz) == 0;
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
        return result;
    }

    //Метод negate - для створення нормалі, яка протилежна поточній

    public Normal negate() {
        return new Normal(-nx, -ny, -nz);
    }
    //Метод add - для додавання іншої нормалі до поточної

    public void add(Normal other) {
        this.nx += other.nx;
        this.ny += other.ny;
        this.nz += other.nz;
    }
    //Метод subtract - для віднімання іншої нормалі від поточної

    public void subtract(Normal other) {
        this.nx -= other.nx;
        this.ny -= other.ny;
        this.nz -= other.nz;
    }

    //Метод dotProduct - для обчислення скалярного добутку поточної нормалі з іншою нормаллю

    public double dotProduct(Normal other) {
        return this.nx * other.nx + this.ny * other.ny + this.nz * other.nz;
    }
    //Метод isParallelTo - для перевірки, чи є поточна нормаль паралельною до заданої нормалі

    public boolean isParallelTo(Normal other) {
        double dotProduct = dotProduct(other);
        return dotProduct == 1 || dotProduct == -1;
    }
    //Метод isPerpendicularTo - для перевірки, чи є поточна нормаль перпендикулярною до заданої нормалі

    public boolean isPerpendicularTo(Normal other) {
        return dotProduct(other) == 0;
    }
    //Метод crossProduct - для обчислення векторного добутку поточної нормалі з іншою нормаллю

    public Normal crossProduct(Normal other) {
        double crossNx = this.ny * other.nz - this.nz * other.ny;
        double crossNy = this.nz * other.nx - this.nx * other.nz;
        double crossNz = this.nx * other.ny - this.ny * other.nx;
        return new Normal(crossNx, crossNy, crossNz);
    }
}

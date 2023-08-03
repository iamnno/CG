package lab1.DataStructures;

public class Ray {
    // Змінні для зберігання початкової точки та напрямку променя
    private Point origin;
    private Vector3D direction;

    // Конструктор для створення об'єкта Ray з початковою точкою та напрямком
    public Ray(Point origin, Vector3D direction) {
        this.origin = origin;
        this.direction = direction;
    }

    // Методи для отримання початкової точки та напрямку променя
    public Point getOrigin() {
        return origin;
    }

    public Vector3D getDirection() {
        return direction;
    }

    // Метод для отримання точки на промені за певну відстань від початкової точки
    public Point getPointAtDistance(double distance) {
        double x = origin.getX() + distance * direction.getX();
        double y = origin.getY() + distance * direction.getY();
        double z = origin.getZ() + distance * direction.getZ();
        return new Point(x, y, z);
    }

    // Перевизначення методу toString для представлення променя у вигляді рядка
    @Override
    public String toString() {
        return "Origin: " + origin + ", Direction: " + direction;
    }

    // Перевизначення методу equals для порівняння двох об'єктів Ray
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Ray other = (Ray) obj;
        return origin.equals(other.origin) && direction.equals(other.direction);
    }

    // Перевизначення методу hashCode для правильної роботи методів, що використовують хеш-таблиці
    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + origin.hashCode();
        result = 31 * result + direction.hashCode();
        return result;
    }

    // Метод translate - для зсуву початкової точки променя на вектор (тривимірний зсув)
    public void translate(Vector3D translation) {
        origin.move(translation.getX(), translation.getY(), translation.getZ());
    }

    // Метод reverse - для отримання протилежного променя, тобто зміни напрямку променя на протилежний
    public Ray reverse() {
        Vector3D reversedDirection = direction.clone().reverse();
        return new Ray(origin, reversedDirection);
    }

    // Метод pointAtDistance - для отримання точки на промені за певну відстань від початкової точки,
    // але цей метод не створює новий об'єкт Point, а змінює відповідні поля початкової точки променя
    public void pointAtDistance(double distance) {
        origin = getPointAtDistance(distance);
    }

    // Метод intersectPlane - для знаходження точки перетину променя з площиною,
    // якщо промінь перетинає площину (потрібно перевіряти, чи існує перетин перед викликом цього методу)
    public Point intersectPlane(Plane plane) {
        double numerator = plane.getNx() * (plane.getPointOnPlane().getX() - origin.getX()) +
                plane.getNy() * (plane.getPointOnPlane().getY() - origin.getY()) +
                plane.getNz() * (plane.getPointOnPlane().getZ() - origin.getZ());
        double denominator = plane.getNx() * direction.getX() +
                plane.getNy() * direction.getY() +
                plane.getNz() * direction.getZ();
        double distance = numerator / denominator;
        return getPointAtDistance(distance);
    }

    // Метод distanceToPoint - для обчислення відстані від початкової точки променя до заданої точки
    public double distanceToPoint(Point point) {
        Vector3D vectorToPoint = new Vector3D(point.getX() - origin.getX(),
                point.getY() - origin.getY(),
                point.getZ() - origin.getZ());
        double projectionLength = vectorToPoint.dotProduct(direction);
        Vector3D perpendicularVector = vectorToPoint.subtract(direction.clone().multiply(projectionLength));
        return perpendicularVector.magnitude();
    }
}
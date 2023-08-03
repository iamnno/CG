package lab1.DataStructures;

public class Camera {
    // Змінні для зберігання положення камери (точка, де розташована камера) і напрямку перегляду (вектор, що показує напрямок камери)
    private Point position;
    private Vector3D direction;

    // Конструктор для створення об'єкта Camera з заданим положенням і напрямком перегляду
    public Camera(Point position, Vector3D direction) {
        this.position = position;
        this.direction = direction;
    }

    // Методи для отримання положення камери і напрямку перегляду
    public Point getPosition() {
        return position;
    }

    public Vector3D getDirection() {
        return direction;
    }

    // Метод для зміни положення камери
    public void setPosition(Point newPosition) {
        this.position = newPosition;
    }

    // Метод для зміни напрямку перегляду камери
    public void setDirection(Vector3D newDirection) {
        this.direction = newDirection;
    }

    // Перевизначення методу toString для представлення камери у вигляді рядка
    @Override
    public String toString() {
        return "Position: " + position + ", Direction: " + direction;
    }

    // Перевизначення методу equals для порівняння двох об'єктів Camera
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Camera other = (Camera) obj;
        return position.equals(other.position) && direction.equals(other.direction);
    }

    // Перевизначення методу hashCode для правильної роботи методів, що використовують хеш-таблиці
    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + position.hashCode();
        result = 31 * result + direction.hashCode();
        return result;
    }

    // Метод move - для зсуву камери на заданий вектор (тривимірний зсув)
    public void move(Vector3D translation) {
        position.add(translation);
    }

    // Метод clone - для створення глибокої копії об'єкта камери
    public Camera clone() {
        return new Camera(position.clone(), direction.clone());
    }

    // Метод getRayThroughPixel - для отримання променя, який проходить через піксель на зображенні камери, використовуючи задані координати пікселя і розміри зображення
    public Ray getRayThroughPixel(int pixelX, int pixelY, int imageWidth, int imageHeight) {
        double aspectRatio = (double) imageWidth / imageHeight;
        double fovY = Math.toRadians(60);
        double halfHeight = Math.tan(fovY / 2);
        double halfWidth = aspectRatio * halfHeight;
        double normalizedX = (2.0 * pixelX / (imageWidth - 1)) - 1.0;
        double normalizedY = 1.0 - (2.0 * pixelY / (imageHeight - 1));
        double viewPlaneX = normalizedX * halfWidth;
        double viewPlaneY = normalizedY * halfHeight;
        Vector3D rayDirection = direction.clone().add(new Vector3D(viewPlaneX, viewPlaneY, 0)).normalize();
        return new Ray(position.clone(), rayDirection);
    }
}
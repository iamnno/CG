package lab1.DataStructures;

public class DirectedLightSource {
    // Змінні для зберігання напрямку світла та його колір (кожна компонента від 0 до 255)
    private Vector3D direction;
    private int red;
    private int green;
    private int blue;

    // Конструктор для створення об'єкта DirectedLightSource з заданим напрямком та кольором
    public DirectedLightSource(Vector3D direction, int red, int green, int blue) {
        this.direction = direction;
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    // Методи для отримання напрямку та кольору світла
    public Vector3D getDirection() {
        return direction;
    }

    public int getRed() {
        return red;
    }

    public int getGreen() {
        return green;
    }

    public int getBlue() {
        return blue;
    }

    // Методи для зміни напрямку та кольору світла
    public void setDirection(Vector3D newDirection) {
        this.direction = newDirection;
    }

    public void setColor(int newRed, int newGreen, int newBlue) {
        this.red = newRed;
        this.green = newGreen;
        this.blue = newBlue;
    }

    // Перевизначення методу toString для представлення напрямленого джерела світла у вигляді рядка
    @Override
    public String toString() {
        return "Direction: " + direction + ", Color: RGB(" + red + ", " + green + ", " + blue + ")";
    }

    // Перевизначення методу equals для порівняння двох об'єктів DirectedLightSource
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        DirectedLightSource other = (DirectedLightSource) obj;
        return direction.equals(other.direction) &&
                red == other.red &&
                green == other.green &&
                blue == other.blue;
    }

    // Перевизначення методу hashCode для правильної роботи методів, що використовують хеш-таблиці
    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + direction.hashCode();
        result = 31 * result + red;
        result = 31 * result + green;
        result = 31 * result + blue;
        return result;
    }

    // Метод setIntensity - для зміни інтенсивності світла. Можна використовувати одне значення і змінити інтенсивність у всіх каналах (червоному, зеленому і синьому), або можна передати окремі значення для кожного каналу
    public void setIntensity(int intensity) {
        red = green = blue = intensity;
    }

    public void setIntensity(int redIntensity, int greenIntensity, int blueIntensity) {
        red = redIntensity;
        green = greenIntensity;
        blue = blueIntensity;
    }

    // Метод multiplyIntensity - для збільшення або зменшення інтенсивності світла на певний коефіцієнт
    public void multiplyIntensity(double factor) {
        red = (int) (red * factor);
        green = (int) (green * factor);
        blue = (int) (blue * factor);
        // Заборонити інтенсивність перевищувати 255 або бути менше 0
        red = Math.min(Math.max(red, 0), 255);
        green = Math.min(Math.max(green, 0), 255);
        blue = Math.min(Math.max(blue, 0), 255);
    }

    // Метод getRGB - для отримання кольору світла у форматі RGB, який може бути використаний для подальшого використання в графічних операціях
    public int getRGB() {
        int rgb = (red << 16) | (green << 8) | blue;
        return rgb;
    }

    // Метод clone - для створення глибокої копії об'єкта напрямленого джерела світла
    public DirectedLightSource clone() {
        return new DirectedLightSource(direction.clone(), red, green, blue);
    }
}
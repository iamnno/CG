package lab1.DataStructures;

public class Matrix4x4 {
    private double[][] data;

    public Matrix4x4(double[][] data) {
        this.data = data;
    }

    // Створення матриці одиничного перетворення
    public static Matrix4x4 identity() {
        double[][] data = new double[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                data[i][j] = (i == j) ? 1.0 : 0.0;
            }
        }
        return new Matrix4x4(data);
    }

    // Створення матриці переміщення
    public static Matrix4x4 translation(double x, double y, double z) {
        double[][] data = {
                {1, 0, 0, x},
                {0, 1, 0, y},
                {0, 0, 1, z},
                {0, 0, 0, 1}
        };
        return new Matrix4x4(data);
    }

    // Створення матриці повороту навколо осі X
    public static Matrix4x4 rotationX(double angle) {
        double cosA = Math.cos(angle);
        double sinA = Math.sin(angle);
        double[][] data = {
                {1, 0, 0, 0},
                {0, cosA, -sinA, 0},
                {0, sinA, cosA, 0},
                {0, 0, 0, 1}
        };
        return new Matrix4x4(data);
    }

    // Створення матриці повороту навколо осі Y
    public static Matrix4x4 rotationY(double angle) {
        double cosA = Math.cos(angle);
        double sinA = Math.sin(angle);
        double[][] data = {
                {cosA, 0, sinA, 0},
                {0, 1, 0, 0},
                {-sinA, 0, cosA, 0},
                {0, 0, 0, 1}
        };
        return new Matrix4x4(data);
    }

    // Створення матриці повороту навколо осі Z
    public static Matrix4x4 rotationZ(double angle) {
        double cosA = Math.cos(angle);
        double sinA = Math.sin(angle);
        double[][] data = {
                {cosA, -sinA, 0, 0},
                {sinA, cosA, 0, 0},
                {0, 0, 1, 0},
                {0, 0, 0, 1}
        };
        return new Matrix4x4(data);
    }

    // Створення матриці розтягування
    public static Matrix4x4 scaling(double scaleX, double scaleY, double scaleZ) {
        double[][] data = {
                {scaleX, 0, 0, 0},
                {0, scaleY, 0, 0},
                {0, 0, scaleZ, 0},
                {0, 0, 0, 1}
        };
        return new Matrix4x4(data);
    }

    // Застосування матриці до точки
    public Point applyToPoint(Point point) {
        double x = data[0][0] * point.getX() + data[0][1] * point.getY() + data[0][2] * point.getZ() + data[0][3];
        double y = data[1][0] * point.getX() + data[1][1] * point.getY() + data[1][2] * point.getZ() + data[1][3];
        double z = data[2][0] * point.getX() + data[2][1] * point.getY() + data[2][2] * point.getZ() + data[2][3];
        return new Point(x, y, z);
    }

    // Застосування матриці до вектора
    public Vector3D applyToVector(Vector3D vector) {
        double x = data[0][0] * vector.getX() + data[0][1] * vector.getY() + data[0][2] * vector.getZ();
        double y = data[1][0] * vector.getX() + data[1][1] * vector.getY() + data[1][2] * vector.getZ();
        double z = data[2][0] * vector.getX() + data[2][1] * vector.getY() + data[2][2] * vector.getZ();
        return new Vector3D(x, y, z);
    }

    // Застосування матриці до нормалі
    public Vector3D applyToNormal(Vector3D normal) {
        // Виконуємо зворотне перетворення для нормалі (транспонуємо матрицю)
        Matrix4x4 transposed = new Matrix4x4(new double[][]{
                {data[0][0], data[1][0], data[2][0], 0},
                {data[0][1], data[1][1], data[2][1], 0},
                {data[0][2], data[1][2], data[2][2], 0},
                {0, 0, 0, 1}
        });

        double x = transposed.data[0][0] * normal.getX() + transposed.data[0][1] * normal.getY() + transposed.data[0][2] * normal.getZ();
        double y = transposed.data[1][0] * normal.getX() + transposed.data[1][1] * normal.getY() + transposed.data[1][2] * normal.getZ();
        double z = transposed.data[2][0] * normal.getX() + transposed.data[2][1] * normal.getY() + transposed.data[2][2] * normal.getZ();
        return new Vector3D(x, y, z).normalize();
    }
}
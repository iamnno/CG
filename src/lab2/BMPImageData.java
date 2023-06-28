package lab2;

public class BMPImageData {
    private byte[] imagePixels;
    private int width;
    private int height;

    public BMPImageData(byte[] imagePixels, int width, int height) {
        this.imagePixels = imagePixels;
        this.width = width;
        this.height = height;
    }

    public byte[] getImagePixels() {
        return imagePixels;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}

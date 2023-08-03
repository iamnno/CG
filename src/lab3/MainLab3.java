package lab3;

import java.io.IOException;
import java.util.List;

public class MainLab3 {
    public static void main(String[] args) throws IOException {

        String inputFilePath = null;
        String outputFilePath = null;

        for (String arg : args) {
            if (arg.startsWith("--source=")) {
                inputFilePath = arg.substring("--source=".length());
            } else if (arg.startsWith("--output=")) {
                outputFilePath = arg.substring("--output=".length());
            }
        }

        List<double[]> list = OBJFileReader.readOBJ(inputFilePath);

        for (double[] vertex : list) {
            System.out.println("Vertex: (" + vertex[0] + ", " + vertex[1] + ", " + vertex[2] + ")");
        }
    }
}

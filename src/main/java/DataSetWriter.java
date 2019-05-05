import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;
import org.nd4j.linalg.ops.transforms.Transforms;
import org.nd4j.linalg.util.FeatureUtil;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Locale;

public class DataSetWriter {

    @SuppressWarnings("Duplicates")
    public static void main(String[] args) throws IOException {

        INDArray indArray = Nd4j.linspace(0, 6.28, 100);
        Files.delete(FileSystems.getDefault().getPath("src\\main\\resources\\data"));
        Path path = FileSystems.getDefault().getPath("src\\main\\resources\\data");
        Path file1 = Files.createFile(path);
        File file = file1.toFile();
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));

        long l = System.currentTimeMillis();
        for (int i = 0; i < 500; ++i) {
            double multiplier = Math.random() * 2;
            INDArray arrsin = Transforms.sin(indArray.mul(multiplier));
            FeatureUtil.scaleMinMax(0, 1.0, arrsin.transpose());
            arrsin.transpose();
            double[] doubles = arrsin.toDoubleVector();
            for (int k = 0; k < doubles.length; ++k) {
                bufferedWriter.write(String.format(Locale.CANADA, "%.4f", doubles[k]) + ",");
            }
            bufferedWriter.write("1\n");
        }

        System.out.println("First: " + (System.currentTimeMillis() - l));

        l = System.currentTimeMillis();
        for (int i = 0; i < 500; ++i) {
            double multiplier = Math.random() * 2;
            INDArray arrcos = Transforms.cos(indArray.mul(multiplier));
            FeatureUtil.scaleMinMax(0, 1.0, arrcos.transpose());
            arrcos.transpose();
            double[] doubles = arrcos.toDoubleVector();
            for (int k = 0; k < doubles.length; ++k) {
                bufferedWriter.write(String.format(Locale.CANADA, "%.4f", doubles[k]) + ",");
            }
            bufferedWriter.write("0\n");
        }
        System.out.println("Second: " + (System.currentTimeMillis() - l));
        bufferedWriter.close();
    }
}
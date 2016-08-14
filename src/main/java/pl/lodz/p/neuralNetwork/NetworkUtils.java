package pl.lodz.p.neuralNetwork;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NetworkUtils {

    public static double[][] arraySort(double[][] array) {
        java.util.Arrays.sort(array, (double[] a, double[] b) -> Double.compare(a[0], b[0]));
        return array;
    }

    public static void savePoints(String filePath, List<Point> points) {
        try (final PrintStream out = new PrintStream(new FileOutputStream(filePath))) {
            for (Point p : points) {
                out.print(p.getX());
                out.print(" ");
                out.print(p.getY());
                out.println();
            }
        } catch (FileNotFoundException ex) {

        }
    }


    public static void randomizePatterns(double[][] inputs, double[][] outputs) {
        double[] temp;
        int howManyTimes = inputs.length;
        int first;
        int second;
        Random r = new Random();
        for (int i = 0; i < howManyTimes; i++) {
            first = r.nextInt(inputs.length);
            second = r.nextInt(inputs.length);
            while (first == second) {
                first = r.nextInt(inputs.length);
                second = r.nextInt(inputs.length);
            }
            temp = inputs[first];
            inputs[first] = inputs[second];
            inputs[second] = temp;

            temp = outputs[first];
            outputs[first] = outputs[second];
            outputs[second] = temp;

        }
    }

    public static void saveArraysToFile(String filename, double[][] array) {
        try (final PrintStream out = new PrintStream(new FileOutputStream(filename))) {

            for (double[] array1 : array) {
                for (int j = 0; j < array[0].length; j++) {
                    out.print(array1[j] + " ");
                }
                out.println();
            }
            out.println();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(NeuralNetwork.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static double[][] getColumnArrayFromArray(int index, double[][] array) {
        double[][] result = new double[array.length][];
        for (int i = 0; i < array.length; i++) {
            result[i] = new double[1];
            result[i][0] = array[i][index];
        }
        return result;
    }

}

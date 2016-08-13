package pl.lodz.p.neuralNetwork;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;

import java.io.PrintStream;
import static java.lang.Double.parseDouble;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Utils {

    public static void printSinglePattern(double[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i] + " ");
        }
    }

    public static void printPatterns(double[][] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.println("Pattern " + i);
            for (int j = 0; j < array[i].length; j++) {
                System.out.println(array[i][j] + " ");
            }

        }
    }

    public static double[][] arraySort(double[][] array) {
        java.util.Arrays.sort(array, (double[] a, double[] b) -> Double.compare(a[0], b[0]));
        return array;
    }

    public static void saveTwoDimensionArray(String filePath, double[][] array) {
        try (final PrintStream out = new PrintStream(new FileOutputStream(filePath))) {
            for (double[] array1 : array) {
                for (int j = 0; j < array1.length; j++) {
                    out.print(array1[j]);
                    out.print(" ");
                }
                out.println();
            }
        } catch (FileNotFoundException ex) {

        }
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

    public static List<Point> loadPoints(String filePath) {
        String x;
        String y;
        List<Point> pointList = new ArrayList<>();
        try (final Scanner in = new Scanner(new FileReader(filePath))) {
            while (in.hasNext()) {
                in.useDelimiter(" ");
                x = in.next();
                in.useDelimiter(" ");
                if (in.hasNext()) {
                    in.useDelimiter("\n");
                    y = in.next();
                    pointList.add(new Point(Double.parseDouble(x), Double.parseDouble(y)));
                }
            }
        } catch (FileNotFoundException ex) {

        }
        return pointList;
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

//    public static void saveNetworkToXML(String filePath, NeuralNetwork network) {
//        XStream xStream = getXstreamObject();
//        String xml = xStream.toXML(network);
//        try (final PrintStream out = new PrintStream(new FileOutputStream(filePath))) {
//            out.print(xml);
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//
//    public static NeuralNetwork loadNetworkFromXML(String filePath) {
//
//        XStream xStream = getXstreamObject();
//        NeuralNetwork tempNetwork = null;
//        try {
//            tempNetwork = (NeuralNetwork) xStream.fromXML(new FileReader("network.xml"));
//
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return tempNetwork;
//    }
//
//    private static XStream getXstreamObject() {
//        XStream xstream = new XStream();
//        xstream.alias("neuralNetwork", NeuralNetwork.class);
//        xstream.alias("neuron", Neuron.class);
//        xstream.alias("neuronLayer", NeuronLayer.class);
//        return xstream;
//    }

    public static double[][] getArraysFromFile(String filename) {
        double[][] temp = null;
        int countRows = 0;

        try (final Scanner in = new Scanner(new FileReader(filename))) {
            while (in.hasNext()) {
                in.nextLine();
                countRows++;
            }
        } catch (FileNotFoundException ex) {

        }
        temp = new double[countRows][];
        int i = 0;
        try (final Scanner in = new Scanner(new FileReader(filename))) {
            while (in.hasNext()) {
                String token = in.nextLine();
                String[] splitStrings = token.split(" ");
                double[] arr = new double[splitStrings.length];
                for (int j = 0; j < arr.length; j++) {
                    arr[j] = parseDouble(splitStrings[j]);
                }
                temp[i] = arr;
                i++;
            }
        } catch (FileNotFoundException ex) {

        }
        return temp;
    }

    public static double[][] getArraysFromJson(String filename) {
        double[][] temp = null;

        return temp;
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

    public static double[][] getColumnsArrayFromArray(double[][] array, int[] vector) {
        double[][] result = new double[array.length][];
        for (int i = 0; i < array.length; i++) {
            result[i] = new double[vector.length];
            for (int j = 0; j < vector.length; j++) {
                result[i][j] = array[i][vector[j]];
            }

        }
        return result;
    }

}

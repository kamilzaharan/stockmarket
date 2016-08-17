package pl.lodz.p.neuralNetwork;

public class Point implements Comparable<Point> {

    private final double x;
    private final double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    @Override
    public int compareTo(Point t) {

        return Double.compare(x, t.getX());
    }
}

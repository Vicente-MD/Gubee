package Open_Closed_Principle.BadExample;

public class Circle {
    private double radius;
    private static double pi = 3.1415;

    public double getRadius() {
        return radius;
    }
    public void setRadius(double radius) {
        this.radius = radius;
    }

    public double getPi() {
        return pi;
    }

    public Circle(double radius) {
        this.radius = radius;
    }
}

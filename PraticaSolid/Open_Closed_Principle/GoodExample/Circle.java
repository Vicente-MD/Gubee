package Open_Closed_Principle.GoodExample;

public class Circle implements Shape {
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

    public double area(){
        return this.radius * this.radius * pi;
    }
}

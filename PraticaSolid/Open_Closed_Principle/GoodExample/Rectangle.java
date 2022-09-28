package Open_Closed_Principle.GoodExample;

public class Rectangle extends Shape{
    private double width;
    private double height;

    public double getWidth() {
        return width;
    }
    public void setWidth(double width) {
        this.width = width;
    }
    public double getHeight() {
        return height;
    }
    public void setHeight(double height) {
        this.height = height;
    }
    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public double Area(){
        return this.width * this.height;
    }
}

package Open_Closed_Principle.BadExample;

public class AreaCalculator {
    public double area(Object[] shapes) {
        double area = 0;
        for (var shape : shapes) {
            if (shape instanceof Rectangle) {
                Rectangle rectangle = (Rectangle) shape;
                area += rectangle.getWidth() * rectangle.getHeight();
            } else {
                Circle circle = (Circle) shape;
                area += circle.getRadius() * circle.getRadius() * circle.getPi();
            }
        }

        return area;
    }
}

package Open_Closed_Principle.GoodExample;

public class AreaCalculator {
    public double Area(Shape[] shapes) {
        double area = 0;
        for (var shape : shapes) {
            area += shape.Area();
        }
        return area;
    }
}

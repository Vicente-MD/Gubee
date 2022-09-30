package Open_Closed_Principle.GoodExample;

public class AreaCalculator {
    public double area(Shape[] shapes) {
        double area = 0;
        for (var shape : shapes) {
            area += shape.area();
        }
        return area;
    }
}

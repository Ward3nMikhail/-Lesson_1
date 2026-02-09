import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;
import static org.testng.Assert.*;

public class TriangleAreaCalculatorTest {

    @Test
    public void testCalculateAreaWithBaseAndHeight() {
        assertEquals(TriangleAreaCalculator.calculateArea(5, 4), 10.0);
        assertEquals(TriangleAreaCalculator.calculateArea(10, 5), 25.0);
    }

    @Test
    public void testCalculateAreaWithSides() {
        assertEquals(TriangleAreaCalculator.calculateAreaBySides(3, 4, 5), 6.0);
        assertEquals(TriangleAreaCalculator.calculateAreaBySides(5, 6, 7), 14.6969, 0.0001);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testInvalidBaseThrowsException() {
        TriangleAreaCalculator.calculateArea(0, 5);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testInvalidHeightThrowsException() {
        TriangleAreaCalculator.calculateArea(5, -1);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testInvalidTriangleThrowsException() {
        TriangleAreaCalculator.calculateAreaBySides(1, 2, 10);
    }

    @DataProvider(name = "areaData")
    public Object[][] provideAreaData() {
        return new Object[][]{
                {5.0, 4.0, 10.0},
                {10.0, 5.0, 25.0},
                {3.0, 3.0, 4.5}
        };
    }

    @Test(dataProvider = "areaData")
    public void testCalculateAreaWithDataProvider(double base, double height, double expected) {
        assertEquals(TriangleAreaCalculator.calculateArea(base, height), expected);
    }
}

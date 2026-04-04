import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

class TriangleAreaCalculatorTest {

    @Test
    void testCalculateAreaWithBaseAndHeight() {
        assertEquals(10.0, TriangleAreaCalculator.calculateArea(5, 4));
        assertEquals(25.0, TriangleAreaCalculator.calculateArea(10, 5));
    }

    @Test
    void testCalculateAreaWithSides() {
        assertEquals(6.0, TriangleAreaCalculator.calculateAreaBySides(3, 4, 5));
        assertEquals(14.6969, TriangleAreaCalculator.calculateAreaBySides(5, 6, 7), 0.0001);
    }

    @Test
    void testInvalidBaseOrHeightThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            TriangleAreaCalculator.calculateArea(0, 5);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            TriangleAreaCalculator.calculateArea(5, -1);
        });
    }

    @Test
    void testInvalidTriangleThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            TriangleAreaCalculator.calculateAreaBySides(1, 2, 10);
        });
    }

    @ParameterizedTest
    @CsvSource({
            "5, 4, 10.0",
            "10, 5, 25.0",
            "3, 3, 4.5"
    })
    void testCalculateAreaParameterized(double base, double height, double expected) {
        assertEquals(expected, TriangleAreaCalculator.calculateArea(base, height));
    }
}

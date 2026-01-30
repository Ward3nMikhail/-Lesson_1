import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class TriangleAreaTest {
    @Test
    void testValidTriangle() {
        assertEquals(6.0, TriangleArea.calculate(3, 4, 5), 0.001);
    }

    @Test
    void testInvalidSides() {
        assertThrows(IllegalArgumentException.class, () -> TriangleArea.calculate(-1, 2, 3));
        assertThrows(IllegalArgumentException.class, () -> TriangleArea.calculate(1, 1, 3));
    }
}

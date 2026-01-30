import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class CalculatorTest {
    @Test
    void testAdd() {
        assertEquals(5, Calculator.add(2, 3));
    }

    @Test
    void testSubtract() {
        assertEquals(-1, Calculator.subtract(2, 3));
    }

    @Test
    void testMultiply() {
        assertEquals(6, Calculator.multiply(2, 3));
    }

    @Test
    void testDivide() {
        assertEquals(2.0, Calculator.divide(6, 3), 0.001);
        assertThrows(ArithmeticException.class, () -> Calculator.divide(5, 0));
    }
}

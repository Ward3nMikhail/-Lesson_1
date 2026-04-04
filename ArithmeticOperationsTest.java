import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

class ArithmeticOperationsTest {

    @Test
    void testAdd() {
        assertEquals(15, ArithmeticOperations.add(10, 5));
        assertEquals(-5, ArithmeticOperations.add(-10, 5));
        assertEquals(0, ArithmeticOperations.add(0, 0));
    }

    @Test
    void testSubtract() {
        assertEquals(5, ArithmeticOperations.subtract(10, 5));
        assertEquals(-15, ArithmeticOperations.subtract(-10, 5));
        assertEquals(0, ArithmeticOperations.subtract(5, 5));
    }

    @Test
    void testMultiply() {
        assertEquals(50, ArithmeticOperations.multiply(10, 5));
        assertEquals(-50, ArithmeticOperations.multiply(-10, 5));
        assertEquals(0, ArithmeticOperations.multiply(10, 0));
    }

    @Test
    void testDivide() {
        assertEquals(2.0, ArithmeticOperations.divide(10, 5));
        assertEquals(-2.0, ArithmeticOperations.divide(-10, 5));
        assertEquals(2.5, ArithmeticOperations.divide(5, 2));
    }

    @Test
    void testDivideByZeroThrowsException() {
        assertThrows(ArithmeticException.class, () -> {
            ArithmeticOperations.divide(10, 0);
        });
    }

    @ParameterizedTest
    @CsvSource({
            "10, 5, 15",
            "-10, 5, -5",
            "0, 0, 0"
    })
    void testAddParameterized(int a, int b, int expected) {
        assertEquals(expected, ArithmeticOperations.add(a, b));
    }

    @ParameterizedTest
    @CsvSource({
            "10, 5, 2.0",
            "-10, 5, -2.0",
            "5, 2, 2.5"
    })
    void testDivideParameterized(int a, int b, double expected) {
        assertEquals(expected, ArithmeticOperations.divide(a, b), 0.0001);
    }
}

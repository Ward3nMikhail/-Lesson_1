import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

class FactorialCalculatorTest {

    @Test
    void testFactorialOfZero() {
        assertEquals(1, FactorialCalculator.factorial(0));
    }

    @Test
    void testFactorialOfOne() {
        assertEquals(1, FactorialCalculator.factorial(1));
    }

    @Test
    void testFactorialOfFive() {
        assertEquals(120, FactorialCalculator.factorial(5));
    }

    @Test
    void testFactorialOfTen() {
        assertEquals(3628800, FactorialCalculator.factorial(10));
    }

    @Test
    void testFactorialOfTwenty() {
        assertEquals(2432902008176640000L, FactorialCalculator.factorial(20));
    }

    @Test
    void testNegativeNumberThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            FactorialCalculator.factorial(-1);
        });
    }

    @Test
    void testTooLargeNumberThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            FactorialCalculator.factorial(21);
        });
    }

    @ParameterizedTest
    @CsvSource({
            "0, 1",
            "1, 1",
            "2, 2",
            "3, 6",
            "4, 24",
            "5, 120"
    })
    void testFactorialParameterized(int input, long expected) {
        assertEquals(expected, FactorialCalculator.factorial(input));
    }
}

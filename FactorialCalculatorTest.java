import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;
import static org.testng.Assert.*;

public class FactorialCalculatorTest {

    @Test
    public void testFactorialOfZero() {
        assertEquals(FactorialCalculator.factorial(0), 1L);
    }

    @Test
    public void testFactorialOfOne() {
        assertEquals(FactorialCalculator.factorial(1), 1L);
    }

    @Test
    public void testFactorialOfFive() {
        assertEquals(FactorialCalculator.factorial(5), 120L);
    }

    @Test
    public void testFactorialOfTen() {
        assertEquals(FactorialCalculator.factorial(10), 3628800L);
    }

    @Test
    public void testFactorialOfTwenty() {
        assertEquals(FactorialCalculator.factorial(20), 2432902008176640000L);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testNegativeNumberThrowsException() {
        FactorialCalculator.factorial(-1);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testTooLargeNumberThrowsException() {
        FactorialCalculator.factorial(21);
    }

    @DataProvider(name = "factorialData")
    public Object[][] provideFactorialData() {
        return new Object[][]{
                {0, 1L},
                {1, 1L},
                {2, 2L},
                {3, 6L},
                {4, 24L},
                {5, 120L}
        };
    }

    @Test(dataProvider = "factorialData")
    public void testFactorialWithDataProvider(int input, long expected) {
        assertEquals(FactorialCalculator.factorial(input), expected);
    }
}
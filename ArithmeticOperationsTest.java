import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;
import static org.testng.Assert.*;

public class ArithmeticOperationsTest {

    @Test
    public void testAdd() {
        assertEquals(ArithmeticOperations.add(10, 5), 15);
        assertEquals(ArithmeticOperations.add(-10, 5), -5);
        assertEquals(ArithmeticOperations.add(0, 0), 0);
    }

    @Test
    public void testSubtract() {
        assertEquals(ArithmeticOperations.subtract(10, 5), 5);
        assertEquals(ArithmeticOperations.subtract(-10, 5), -15);
        assertEquals(ArithmeticOperations.subtract(5, 5), 0);
    }

    @Test
    public void testMultiply() {
        assertEquals(ArithmeticOperations.multiply(10, 5), 50);
        assertEquals(ArithmeticOperations.multiply(-10, 5), -50);
        assertEquals(ArithmeticOperations.multiply(10, 0), 0);
    }

    @Test
    public void testDivide() {
        assertEquals(ArithmeticOperations.divide(10, 5), 2.0);
        assertEquals(ArithmeticOperations.divide(-10, 5), -2.0);
        assertEquals(ArithmeticOperations.divide(5, 2), 2.5);
    }

    @Test(expectedExceptions = ArithmeticException.class)
    public void testDivideByZeroThrowsException() {
        ArithmeticOperations.divide(10, 0);
    }

    @DataProvider(name = "addData")
    public Object[][] provideAddData() {
        return new Object[][]{
                {10, 5, 15},
                {-10, 5, -5},
                {0, 0, 0}
        };
    }

    @DataProvider(name = "divideData")
    public Object[][] provideDivideData() {
        return new Object[][]{
                {10, 5, 2.0},
                {-10, 5, -2.0},
                {5, 2, 2.5}
        };
    }

    @Test(dataProvider = "addData")
    public void testAddWithDataProvider(int a, int b, int expected) {
        assertEquals(ArithmeticOperations.add(a, b), expected);
    }

    @Test(dataProvider = "divideData")
    public void testDivideWithDataProvider(int a, int b, double expected) {
        assertEquals(ArithmeticOperations.divide(a, b), expected, 0.0001);
    }
}

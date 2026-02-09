import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;
import static org.testng.Assert.*;

public class NumberComparatorTest {

    @Test
    public void testCompareGreater() {
        assertEquals(NumberComparator.compare(10, 5), "10 больше 5");
        assertEquals(NumberComparator.compare(-5, -10), "-5 больше -10");
    }

    @Test
    public void testCompareLess() {
        assertEquals(NumberComparator.compare(5, 10), "5 меньше 10");
        assertEquals(NumberComparator.compare(-10, -5), "-10 меньше -5");
    }

    @Test
    public void testCompareEqual() {
        assertEquals(NumberComparator.compare(10, 10), "10 равно 10");
        assertEquals(NumberComparator.compare(0, 0), "0 равно 0");
        assertEquals(NumberComparator.compare(-5, -5), "-5 равно -5");
    }

    @DataProvider(name = "compareData")
    public Object[][] provideCompareData() {
        return new Object[][]{
                {10, 5, "10 больше 5"},
                {5, 10, "5 меньше 10"},
                {10, 10, "10 равно 10"},
                {-5, -10, "-5 больше -10"},
                {-10, -5, "-10 меньше -5"}
        };
    }

    @Test(dataProvider = "compareData")
    public void testCompareWithDataProvider(int a, int b, String expected) {
        assertEquals(NumberComparator.compare(a, b), expected);
    }
}

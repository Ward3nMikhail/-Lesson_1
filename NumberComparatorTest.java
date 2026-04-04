import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

class NumberComparatorTest {

    @Test
    void testCompareGreater() {
        assertEquals("10 больше 5", NumberComparator.compare(10, 5));
        assertEquals("-5 больше -10", NumberComparator.compare(-5, -10));
    }

    @Test
    void testCompareLess() {
        assertEquals("5 меньше 10", NumberComparator.compare(5, 10));
        assertEquals("-10 меньше -5", NumberComparator.compare(-10, -5));
    }

    @Test
    void testCompareEqual() {
        assertEquals("10 равно 10", NumberComparator.compare(10, 10));
        assertEquals("0 равно 0", NumberComparator.compare(0, 0));
        assertEquals("-5 равно -5", NumberComparator.compare(-5, -5));
    }

    @ParameterizedTest
    @CsvSource({
            "10, 5, '10 больше 5'",
            "5, 10, '5 меньше 10'",
            "10, 10, '10 равно 10'",
            "-5, -10, '-5 больше -10'",
            "-10, -5, '-10 меньше -5'"
    })
    void testCompareParameterized(int a, int b, String expected) {
        assertEquals(expected, NumberComparator.compare(a, b));
    }
}

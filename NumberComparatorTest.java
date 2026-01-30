import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class NumberComparatorTest {
    @Test
    void testFirstGreater() {
        assertEquals("первое больше", NumberComparator.compare(5, 3));
    }

    @Test
    void testSecondGreater() {
        assertEquals("второе больше", NumberComparator.compare(3, 5));
    }

    @Test
    void testEqual() {
        assertEquals("равны", NumberComparator.compare(4, 4));
    }
}

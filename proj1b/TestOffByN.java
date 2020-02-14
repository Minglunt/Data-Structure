import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByN {
    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByN = new OffByN(1);

    // Your tests go here.
    @Test
    public void testEqualChars(){
        assertTrue(offByN.equalChars('m', 'n'));

        assertFalse(offByN.equalChars('b', 'b'));

        assertTrue(offByN.equalChars('b', 'a'));

        //assertFalse(offByN.equalChars('&', '%'));
    }
}

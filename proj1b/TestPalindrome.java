import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();
    static CharacterComparator cc = new OffByOne();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testisPalindrome() {
        // test the first isPalindrome method
        assertTrue(palindrome.isPalindrome("abcdcba"));

        assertFalse(palindrome.isPalindrome("ab"));

        assertTrue(palindrome.isPalindrome("a"));

        assertTrue(palindrome.isPalindrome(""));

        assertFalse(palindrome.isPalindrome("abcd"));

        assertTrue(palindrome.isPalindrome("abba"));

        // test the second isPalindrome method
        assertTrue(palindrome.isPalindrome("flake", cc));

        assertFalse(palindrome.isPalindrome("aaa", cc));

        assertTrue(palindrome.isPalindrome("a", cc));

        assertTrue(palindrome.isPalindrome("", cc));

    }
}
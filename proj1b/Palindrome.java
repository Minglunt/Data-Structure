public class Palindrome {
    public Deque<Character> wordToDeque(String word){
        int len = word.length();
        Deque<Character> res = new LinkedListDeque<>();
        for (int i = 0; i<len; i++){
            res.addLast(word.charAt(i));
        }
        return res;
    }

    public boolean isPalindrome(String word){
        Deque<Character> dlist = wordToDeque(word);
        int len = dlist.size() - 1;
        for (int i = 0; i < len; i++, --len) {
            if (!dlist.get(i).equals(dlist.get(len))) {
                return false;
            }
        }
        return true;
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        int len = word.length() - 1;
        for (int i = 0; i < len; i++, --len) {
            if (!cc.equalChars(word.charAt(i), word.charAt(len))) {
                return false;
            }
        }
        return true;
    }
}

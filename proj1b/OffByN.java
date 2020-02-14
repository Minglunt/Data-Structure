public class OffByN implements CharacterComparator{

    private int diff;

    public OffByN(int N){
        diff = N;
    }

    public boolean equalChars(char x, char y){
        if ((x+y >=194+diff && x+y <= 244-diff) || (x+y >= 130+diff && x+y <= 180-diff)){
            return x == y + diff || y == x + diff;
        } else {
            throw new RuntimeException("Characters should be alphabets");
        }
    }
}

public class OffByOne implements CharacterComparator{

    @Override
    public boolean equalChars(char x, char y){
        if ((x+y >194 && x+y < 244) || (x+y > 130 && x+y < 180)){
            return x == y + 1 || y == x + 1;
        } else {
            throw new RuntimeException("Characters should be alphabets");
        }
    }
}

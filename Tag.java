public class Tag {
    private int dictionaryNumber;
    private char nextSymbol;

    public Tag(int dictionaryNumber , char nextSymbol) {
        this.dictionaryNumber = dictionaryNumber;
        this.nextSymbol = nextSymbol;
    }

    @Override
    public String toString() {
        String str1 = Integer.toString(dictionaryNumber);
        return  nextSymbol +  str1 + " ";
    }
}

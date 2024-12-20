public class WordFrequency {
    private final String word;
    private final int count;

    public WordFrequency(String word, int count) {
        this.word = word;
        this.count = count;
    }

    public int getCount() {
        return this.count;
    }

    public String toString() {
        return this.word + " " + this.count;
    }
}

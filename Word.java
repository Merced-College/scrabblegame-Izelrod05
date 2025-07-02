//stores a word so you are able to sort an search for word

public class Word implements Comparable<Word> {
    private String word;

public Word(String word) {
        this.word = word.toLowerCase(); // Ensures case-insensitive matching
    }

    public String getWord() {
        return word;
    }

    //overide method
    public int compareTo(Word other) {
        return this.word.compareTo(other.word);
    }

    //overide method
    public String toString() {
        return word;
    }
}
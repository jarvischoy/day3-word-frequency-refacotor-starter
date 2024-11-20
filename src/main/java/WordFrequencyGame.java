import java.util.*;
import java.util.stream.Collectors;

public class WordFrequencyGame {
    public static final String SPACE_REGEX = "\\s+";

    public String getWordFrequency(String sentence) {
        List<WordFrequency> wordList = countWordFrequencies(sentence);
        return wordList.stream()
                .sorted(Comparator.comparingInt(WordFrequency::getCount).reversed())
                .map(WordFrequency::toString)
                .collect(Collectors.joining("\n"));
    }

    public List<WordFrequency> countWordFrequencies(String sentence) {
        List<String> words = Arrays.asList(sentence.split(SPACE_REGEX));
        HashSet<String> uniqueWords = new HashSet<>(words);
        return uniqueWords.stream()
                .map(word -> new WordFrequency(word, Collections.frequency(words, word)))
                .collect(Collectors.toList());
    }
}

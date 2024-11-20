import java.util.*;
import java.util.stream.Collectors;

public class WordFrequencyGame {
    public static final String SPACE_REGEX = "\\s+";

    public String getWordFrequency(String sentence) {
        List<WordFrequency> inputList = countWordFrequencies(sentence);
        return inputList.stream()
                .sorted(Comparator.comparingInt(WordFrequency::getCount).reversed())
                .map(WordFrequency::toString)
                .collect(Collectors.joining("\n"));
    }

    public List<WordFrequency> countWordFrequencies(String sentence) {
        List<String> words = Arrays.asList(sentence.split(SPACE_REGEX));
        HashSet<String> wordSet = new HashSet<>(words);
        return wordSet.stream()
                .map(word -> new WordFrequency(word, Collections.frequency(words, word)))
                .collect(Collectors.toList());
    }
}

import java.util.*;
import java.util.stream.Collectors;

public class WordFrequencyGame {
    public static final String SPACE_REGEX = "\\s+";
    public static final String NEW_LINE = "\n";

    public String getWordFrequency(String sentence) {
        Map<String, Long> wordCountMap = countWordFrequencies(sentence);
        return wordCountMap.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .map(entry -> new WordFrequency(entry.getKey(), entry.getValue().intValue()).toString())
                .collect(Collectors.joining(NEW_LINE));
    }

    public Map<String, Long> countWordFrequencies(String sentence) {
        return Arrays.stream(sentence.split(SPACE_REGEX))
                .collect(Collectors.groupingBy(word -> word, Collectors.counting()));
    }
}

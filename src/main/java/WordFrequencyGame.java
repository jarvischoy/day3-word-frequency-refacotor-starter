import java.util.*;
import java.util.stream.Collectors;

public class WordFrequencyGame {
    public static final String SPACE_REGEX = "\\s+";

    public String getWordFrequency(String sentence) {
        if (sentence.split(SPACE_REGEX).length == 1) {
            return sentence + " 1";
        } else {
            try {
                List<WordFrequency> wordFrequencies = getInitialWordFrequencies(sentence);

                wordFrequencies = getWordFrequencies(wordFrequencies);

                return joinResult(wordFrequencies);
            } catch (Exception e) {

                return "Calculate Error";
            }
        }
    }

    private static String joinResult(List<WordFrequency> wordFrequencies) {
        return wordFrequencies.stream()
                .map(wordFrequency -> wordFrequency.getWord() + " " + wordFrequency.getWordCount())
                .collect(Collectors.joining("\n"));
    }

    private List<WordFrequency> getWordFrequencies(List<WordFrequency> wordFrequencies) {
        //get the map for the next step of sizing the same word
        Map<String, List<WordFrequency>> wordToFrequency = getFrequencyMap(wordFrequencies);

        wordFrequencies = wordToFrequency.entrySet().stream()
                .map(entry -> new WordFrequency(entry.getKey(), entry.getValue().size()))
                .sorted((w1, w2) -> w2.getWordCount() - w1.getWordCount())
                .toList();

        return wordFrequencies;
    }

    private static List<WordFrequency> getInitialWordFrequencies(String sentence) {
        //split the input string with 1 to n pieces of spaces
        String[] words = sentence.split(SPACE_REGEX);

        List<WordFrequency> wordFrequencies = new ArrayList<>();

        Arrays.stream(words)
                .map(word -> new WordFrequency(word, 1))
                .forEach(wordFrequencies::add);

        return wordFrequencies;
    }

    private Map<String, List<WordFrequency>> getFrequencyMap(List<WordFrequency> wordFrequencies) {
        return wordFrequencies.stream()
                .collect(Collectors.groupingBy(WordFrequency::getWord));
    }

}

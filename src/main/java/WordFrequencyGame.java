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

//    public String getWordFrequency(String sentence) {
//        if (sentence.split(SPACE_REGEX).length == 1) {
//            return sentence + " 1";
//        }
//
//        List<WordFrequency> wordFrequencies = getInitialWordFrequencies(sentence);
//
//        wordFrequencies = getWordFrequencies(wordFrequencies);
//
//        return joinResult(wordFrequencies);
//    }
//
//    private static String joinResult(List<WordFrequency> wordFrequencies) {
//        return wordFrequencies.stream()
//                .map(wordFrequency -> wordFrequency.getWord() + SPACE + wordFrequency.getWordCount())
//                .collect(Collectors.joining("\n"));
//    }
//
//    private List<WordFrequency> getWordFrequencies(List<WordFrequency> wordFrequencies) {
//        Map<String, List<WordFrequency>> wordToFrequency = getFrequencyMap(wordFrequencies);
//
//        wordFrequencies = wordToFrequency.entrySet().stream()
//                .map(entry -> new WordFrequency(entry.getKey(), entry.getValue().size()))
//                .sorted((w1, w2) -> w2.getWordCount() - w1.getWordCount())
//                .toList();
//
//        return wordFrequencies;
//    }
//
//    private static List<WordFrequency> getInitialWordFrequencies(String sentence) {
//        String[] words = sentence.split(SPACE_REGEX);
//
//        List<WordFrequency> wordFrequencies = new ArrayList<>();
//
//        Arrays.stream(words)
//                .map(word -> new WordFrequency(word, 1))
//                .forEach(wordFrequencies::add);
//
//        return wordFrequencies;
//    }
//
//    private Map<String, List<WordFrequency>> getFrequencyMap(List<WordFrequency> wordFrequencies) {
//        return wordFrequencies.stream()
//                .collect(Collectors.groupingBy(WordFrequency::getWord));
//    }


    public List<WordFrequency> countWordFrequencies(String sentence) {
        List<String> words = Arrays.asList(sentence.split(SPACE_REGEX));
        HashSet<String> wordSet = new HashSet<>(words);
        return wordSet.stream()
                .map(word -> new WordFrequency(word, Collections.frequency(words, word)))
                .collect(Collectors.toList());
    }
}

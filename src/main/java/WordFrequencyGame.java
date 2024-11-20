import java.util.*;

public class WordFrequencyGame {
    public static final String SPACE_REGEX = "\\s+";

    public String getWordFrequency(String sentence) {
        if (sentence.split(SPACE_REGEX).length == 1) {
            return sentence + " 1";
        } else {
            try {
                //split the input string with 1 to n pieces of spaces
                String[] words = sentence.split(SPACE_REGEX);

                List<WordFrequency> wordFrequencies = new ArrayList<>();
                for (String word : words) {
                    WordFrequency wordFrequency = new WordFrequency(word, 1);
                    wordFrequencies.add(wordFrequency);
                }
                //get the map for the next step of sizing the same word
                Map<String, List<WordFrequency>> wordToFrequency = getFrequencyMap(wordFrequencies);

                List<WordFrequency> tempWordFrequencies = new ArrayList<>();
                for (Map.Entry<String, List<WordFrequency>> entry : wordToFrequency.entrySet()) {
                    WordFrequency wordFrequency = new WordFrequency(entry.getKey(), entry.getValue().size());
                    tempWordFrequencies.add(wordFrequency);
                }
                wordFrequencies = tempWordFrequencies;

                wordFrequencies.sort((w1, w2) -> w2.getWordCount() - w1.getWordCount());

                StringJoiner joiner = new StringJoiner("\n");
                for (WordFrequency wordFrequency : wordFrequencies) {
                    String s = wordFrequency.getWord() + " " + wordFrequency.getWordCount();
                    joiner.add(s);
                }
                return joiner.toString();
            } catch (Exception e) {

                return "Calculate Error";
            }
        }
    }

    private Map<String, List<WordFrequency>> getFrequencyMap(List<WordFrequency> wordFrequencies) {
        Map<String, List<WordFrequency>> wordToFrequency = new HashMap<>();
        for (WordFrequency wordFrequency : wordFrequencies) {
//       map.computeIfAbsent(input.getValue(), k -> new ArrayList<>()).add(input);
            if (!wordToFrequency.containsKey(wordFrequency.getWord())) {
                ArrayList wordFrequencyCount = new ArrayList<>();
                wordFrequencyCount.add(wordFrequency);
                wordToFrequency.put(wordFrequency.getWord(), wordFrequencyCount);
            } else {
                wordToFrequency.get(wordFrequency.getWord()).add(wordFrequency);
            }
        }

        return wordToFrequency;
    }

}

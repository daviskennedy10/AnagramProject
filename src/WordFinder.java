import java.io.FileNotFoundException;
import java.util.*;

public class WordFinder {
    public static void main(String[] args) {
        String dictionaryFile = "src/sowpods.txt";
        if (args.length > 0) {
            dictionaryFile = args[0];
        }

        AnagramDictionary dictionary;
        try {
            dictionary = new AnagramDictionary(dictionaryFile);
        } catch (FileNotFoundException e) {
            System.out.println("ERROR: Dictionary file \"" + dictionaryFile + "\" does not exist.");
            System.out.println("Exiting program.");
            return;
        } catch (IllegalDictionaryException e) {
            System.out.println("ERROR: " + e.getMessage());
            System.out.println("Exiting program.");
            return;
        }

        Scanner in = new Scanner(System.in);
        ScoreTable scoreTable = new ScoreTable();

        while (true) {
            System.out.print("Rack? ");
            String rackInput = in.nextLine();

            if (rackInput.equals(".")) {
                break;
            }

            Rack rack = new Rack(rackInput);
            ArrayList<String> subsets = rack.getSubsets();
            Map<String, Integer> wordScores = new HashMap<>();

            for (String subset : subsets) {
                if (subset.equals("")) continue; // skip empty subset

                ArrayList<String> words = dictionary.getAnagramsOf(subset);
                for (String word : words) {
                    if (!wordScores.containsKey(word)) {
                        wordScores.put(word, scoreTable.getScore(word));
                    }
                }
            }

            // Sort the results
            List<Map.Entry<String, Integer>> sortedWords = new ArrayList<>(wordScores.entrySet());
            sortedWords.sort((a, b) -> {
                if (b.getValue() != a.getValue()) {
                    return b.getValue() - a.getValue(); // Descending by score
                } else {
                    return a.getKey().compareTo(b.getKey()); // Alphabetical for same score
                }
            });

            // Display results
            System.out.println("We can make " + sortedWords.size() + " words from \"" + rackInput + "\"");
            if (sortedWords.size() > 0) {
                System.out.println("All of the words with their scores (sorted by score):");
                for (Map.Entry<String, Integer> entry : sortedWords) {
                    System.out.println(entry.getValue() + ": " + entry.getKey());
                }
            }
        }
    }
}

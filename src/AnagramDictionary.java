

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Arrays;


/**
 A dictionary of all anagram sets.
 Note: the processing is case-sensitive; so if the dictionary has all lower
 case words, you will likely want any string you test to have all lower case
 letters too, and likewise if the dictionary words are all upper case.
 */
public class AnagramDictionary {
    private Map<String, ArrayList<String>> anagramMap;


    /**
     Create an anagram dictionary from the list of words given in the file
     indicated by fileName.
     @param fileName  the name of the file to read from
     @throws FileNotFoundException  if the file is not found
     @throws IllegalDictionaryException  if the dictionary has any duplicate words
     */
    public AnagramDictionary(String fileName) throws FileNotFoundException,
            IllegalDictionaryException {
        anagramMap = new HashMap<>();
        Set<String> seenWords = new HashSet<>();

        Scanner in = new Scanner(new File(fileName));

        while (in.hasNext()) {
            String word = in.nextLine().trim();

            // Check for duplicate
            if (seenWords.contains(word)) {
                throw new IllegalDictionaryException("Illegal dictionary: dictionary file has a duplicate word: " + word);
            }
            seenWords.add(word);

            String sortedKey = sortString(word);

            anagramMap.putIfAbsent(sortedKey, new ArrayList<>());
            anagramMap.get(sortedKey).add(word);
        }

        in.close();

    }


    /**
     Get all anagrams of the given string. This method is case-sensitive.
     E.g. "CARE" and "race" would not be recognized as anagrams.
     @param s string to process
     @return a list of the anagrams of s
     */
    public ArrayList<String> getAnagramsOf(String string) {
        String sortedKey = sortString(string);

        // Return a new list so the caller can't modify the internal one
        return new ArrayList<>(anagramMap.getOrDefault(sortedKey, new ArrayList<>()));
    }
    private String sortString(String s) {
        char[] chars = s.toCharArray();
        Arrays.sort(chars);
        return new String(chars);

    }
}


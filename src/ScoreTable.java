import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;


public class ScoreTable{
    private Map<Character, Integer> scoreMap;



    public ScoreTable(){
        scoreMap = new HashMap<>();

        // (1 point)
        addScores(1, "AEIOULNSTR");

        // (2 points)
        addScores(2, "DG");

        // (3 points)
        addScores(3, "BCMP");

        // (4 points)
        addScores(4, "FHVWY");

        // (5 points)
        addScores(5, "K");

        // (8 points)
        addScores(8, "JX");

        // (10 points)
        addScores(10, "QZ");


    }

    private void addScores(int score, String letters){
        for (char c : letters.toCharArray()) {
            scoreMap.put(Character.toLowerCase(c), score);
            scoreMap.put(Character.toUpperCase(c), score);
        }

    }
    public int getScore(String word){
        int total = 0;
        for (char c : word.toCharArray()) {
            total += scoreMap.getOrDefault(c, 0);  // non-letter characters are worth 0
        }
        return total;
    }

    public void printScoreMap() {
        for (Map.Entry<Character, Integer> entry : scoreMap.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
    }




















}

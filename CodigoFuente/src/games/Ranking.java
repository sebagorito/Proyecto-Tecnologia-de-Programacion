package games;
import java.util.Map.Entry;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class Ranking {
    private static final String FILE_PATH = "Text/ranking.txt";
    
    public void addScore(String name, int score) {
        Map<String, Integer> scores = loadScores();
        List<Entry<String, Integer>> sortedScores = sort(scores);

        if (sortedScores.size() < 5) {
            scores.put(name, score);
        } else {
            Entry<String, Integer> lowestScore = sortedScores.get(sortedScores.size() - 1);
            if (score > lowestScore.getValue()) {
                scores.remove(lowestScore.getKey());
                scores.put(name, score);
            }
        }
        saveScores(sort(scores));
    }
    
    public Map<String, Integer> loadScores() {
        Map<String, Integer> scores = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    String name = parts[0];
                    int points = Integer.parseInt(parts[1]);
                    scores.put(name, points);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo de puntajes: " + e.getMessage());
        }

        return scores;
    }
    
    private void saveScores(List<Entry<String, Integer>> sortedScores) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Entry<String, Integer> entry : sortedScores) {
                writer.write(entry.getKey() + ":" + entry.getValue());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo de puntajes: " + e.getMessage());
        }
    }
    
    public List<Entry<String, Integer>> sort(Map<String, Integer> scores) {
        List<Entry<String, Integer>> scoreList = new ArrayList<>(scores.entrySet());
        scoreList.sort((a, b) -> b.getValue().compareTo(a.getValue()));
        return scoreList;
    }
}

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WordSearch {
    public static List<String> findWordsInFile(String filePath, String word) {
        List<String> foundWords = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] wordsInLine = line.split("\\s+");

                for (String oneEx : wordsInLine) {
                    if (oneEx.contains(word)) {
                        foundWords.add(line);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return foundWords;
    }

    public static boolean checkDependence(String pathOfFile, String requirePath) {
        List<String> req = findWordsInFile(requirePath, "***require");
        for (String item : req) {
            int oneIndex = item.indexOf("‘") + 1;
            int endIndex = item.lastIndexOf("’");
            String pathOfReq = item.substring(oneIndex, endIndex);
            if (pathOfReq.equals(pathOfFile)) {
                return true;
            }
        }
        return false;
    }
    public static void sortByRequire(List<String> files) {
        for (String item : files) {
            int startIndex = item.indexOf(" ") + 1;
            String path = item.substring(startIndex);
            List<String> req = findWordsInFile(path, "***require");
            for (String pathReq : req) {
                int oneIndex = pathReq.indexOf("‘") + 1;
                int endIndex = pathReq.lastIndexOf("’");
                String pathOfReq = pathReq.substring(oneIndex, endIndex);

                if (checkDependence(path, pathOfReq)) {
                    System.out.println("Цикличная зависимость: " + path + ' ' + pathOfReq);
                }
            }
        }
    }
}

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class WordSearch {

    public static List<String> findWordsInFile(String filePath, String word) {
        List<String> foundWords = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] wordsInLine = line.split("\\s+");  // Разделение строки на слова по пробелам


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

//        Path fileReq = Paths.get(requirePath);

        return false;


    }

    public static void sortByRequire(List<String> files) {
        for (String item : files) {
            int startIndex = item.indexOf(" ") + 1;
            String path = item.substring(startIndex);
//            String textInFile = AllFiles.processFile(result) + '\n';

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



    //                        List<String> foundWords = WordSearch.findWordsInFile(filePath, words);
//                        for (String word : foundWords) {
//                            int startIndex = word.indexOf("‘") + 1;
//                            int endIndex = word.lastIndexOf("’");
//                            String result = word.substring(startIndex, endIndex);
//                            foundFiles.add(result);
//                            System.out.println("Found word in file:" + word.substring(startIndex, endIndex));





//                    Scanner scanner = new Scanner(file);
//                    try {
//
//                        while(scanner.hasNextLine()) {
//                            String line = scanner.nextLine();
//
//                            String[] cols = line.split(" ");
//
//                            if (cols[0].equals("require")) {
//                                System.out.println(line);
//                            }
//                        }
//
//                    } finally {
//                        scanner.close();
//                    }
//                    System.out.println(file + "\t file");

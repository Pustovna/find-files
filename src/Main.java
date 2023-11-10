import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Main {
    public static void main(String[] args) throws IOException {
        // определяем объект для каталога
        File dir = new File("C://Users//sampl//SCE//OOP");
        List<String> wordsToFind = new ArrayList<>(); // arr for words
        wordsToFind.add("***require");
        List<String> files  = new ArrayList<>();
        List<String> all = AllFiles.allFiles(dir, wordsToFind, files);
        Collections.sort(all);



        try{
            File file = new File("C://Users//sampl//SCE//result.txt");
            if (file.createNewFile()){ // return true
                System.out.println("New file is created!!");
            }else{ // вернуть false
                System.out.println("file already exists.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Path path = Paths.get("C://Users//sampl//SCE//result.txt");
//        WordSearch.sortByRequire(all);

        for (String item : all) {
            System.out.println(item);
            int startIndex = item.indexOf(" ") + 1;
            String result = item.substring(startIndex);
            String textInFile = AllFiles.processFile(result) + '\n';
            try {
                byte[] bs = textInFile.getBytes();
                Files.write(path, bs, StandardOpenOption.APPEND);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
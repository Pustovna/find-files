import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.List;

public class AllFiles {
    private static boolean isTextFile(String filePath) {
        String extension = getFileExtension(filePath);
        if (extension != null) {
            return extension.equalsIgnoreCase("txt") || extension.equalsIgnoreCase("csv") || extension.equalsIgnoreCase("xml");
        }
        return false;
    }

    private static String getFileExtension(String filePath) {
        int dotIndex = filePath.lastIndexOf(".");
        if (dotIndex > 0 && dotIndex < filePath.length() - 1) {
            return filePath.substring(dotIndex + 1).toLowerCase();
        }
        return null;
    }

    public static String processFile(String filePath) {
        Path path = Paths.get(filePath);
        String content = new String();
        try {
            content = Files.readString(path);
            return content;
        } catch (IOException e) {
            e.printStackTrace();
            return content;
        }
    }

    public static List<String> allFiles(File startDir, List<String> words, List<String> foundFiles) {

        if (startDir.isDirectory()) {
            // получаем все вложенные объекты в каталоге
            for (File item : startDir.listFiles()) {
                if (item.isDirectory()) {
                    allFiles(item, words, foundFiles);
                } else {
                    String filePath = item.getAbsolutePath(); //get path
                    String fileName = item.getName(); // get file name
                    boolean isText = isTextFile(fileName);
                    if (isText) {
                        String fileNameWithPath = fileName + ' ' + filePath;
                        foundFiles.add(fileNameWithPath);
                    }
                }
            }
        }

        return foundFiles;
    }
}





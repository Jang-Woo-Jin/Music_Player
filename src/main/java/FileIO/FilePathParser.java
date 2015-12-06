package FileIO;

import java.io.File;

public class FilePathParser {

    public static String getExtension(String fileStr) {
        return fileStr.substring(fileStr.lastIndexOf(".") + 1);
    }

    public static String getPath(String fileStr) {
        return fileStr.substring(0, fileStr.lastIndexOf(File.separatorChar));
    }

    public static String getFileName(String fileStr) {
        return fileStr.substring(fileStr.lastIndexOf(File.separatorChar) + 1, fileStr.lastIndexOf("."));
    }

    public static String parseSeparator(String filePath) {
        filePath = filePath.substring(6, filePath.length());
        filePath = filePath.replaceAll("/","\\\\");
        filePath = filePath.replaceAll("%20", " ");
        return filePath;
    }
}
package FileIO;
public class FilePathParser {

    public static String getExtension(String fileStr) {
        return fileStr.substring(fileStr.lastIndexOf(".") + 1, fileStr.length());
    }

    public static String getPath(String fileStr) {
        return fileStr.substring(0, fileStr.lastIndexOf("\\"));
    }

    public static String getFileName(String fileStr) {
        return fileStr.substring(fileStr.lastIndexOf("\\") + 1, fileStr.lastIndexOf("."));
    }
}
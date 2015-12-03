package FileIO;

import OS.OSValidator;

public class FilePathParser {
    private static String directorySeperator;
    static {
        if (OSValidator.isWindows()) {
            directorySeperator = new String("\\");
        } else if (OSValidator.isPOSIX()) {
            directorySeperator = new String("/");
        }
    }

    public static String getDirectorySeperator() {
        return directorySeperator;
    }

    public static String getExtension(String fileStr) {
        return fileStr.substring(fileStr.lastIndexOf(".") + 1);
    }

    public static String getPath(String fileStr) {
        return fileStr.substring(0, fileStr.lastIndexOf(directorySeperator));
    }

    public static String getFileName(String fileStr) {
        return fileStr.substring(fileStr.lastIndexOf(directorySeperator) + 1, fileStr.lastIndexOf("."));
    }
}
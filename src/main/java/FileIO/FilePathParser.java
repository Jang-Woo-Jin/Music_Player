package FileIO;

import java.io.File;
<<<<<<< Updated upstream
import java.net.URI;
import java.net.URISyntaxException;
=======
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLDecoder;
>>>>>>> Stashed changes
import java.nio.file.Paths;

public class FilePathParser {

    public static String getPath(String fileStr) {
        return fileStr.substring(0, fileStr.lastIndexOf(File.separatorChar));
    }

    public static String getFileName(String fileStr) {
        return fileStr.substring(fileStr.lastIndexOf(File.separatorChar) + 1, fileStr.lastIndexOf("."));
    }

    public static String parseSeparator(String filePath){
        String path = null;
        try {
            path = Paths.get(new URI(filePath)).toAbsolutePath().toString();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return path;
    }
}
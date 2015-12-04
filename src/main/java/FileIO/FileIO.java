package FileIO;

import org.jetbrains.annotations.Nullable;

import java.io.*;
import java.util.ArrayList;

public class FileIO {
    @Nullable
    public static ArrayList<String> readTextFile(final String fileAddress, final String fileName) {

        ArrayList<String> information = new ArrayList<>();
        String readLine;
        BufferedReader input;

        try {
            input = new BufferedReader(new InputStreamReader(
                    new FileInputStream(fileAddress
                            + File.separatorChar
                            + fileName + ".txt"), "UTF-8"));
            while ((readLine = input.readLine()) != null) {

                if (readLine.startsWith("//") || (readLine.trim()).equals("")) {
                } else {
                    information.add(readLine);
                }
            }
            input.close();
        } catch (IOException e) {
            File file = new File(fileAddress, fileName + ".txt");
        }

        return information;
    }

    public static void writeTextFile(final String fileInformationAddress, final String fileName, ArrayList<String> writeInformation, final String delimiter) {
        File file = new File(fileInformationAddress, fileName + ".txt");
        try {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));

            for (String iter : writeInformation) {
                writer.write(iter);
                writer.write(delimiter);
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<String> readAllFileInPath(final String fileAddress) {
        File dirFile = new File(fileAddress);
        File[] fileList = dirFile.listFiles();
        ArrayList<String> information = new ArrayList<>();
        for (File tempFile : fileList) {
            if (tempFile.isFile()) {
                String tempFileName = tempFile.getName();
                if (FilePathParser.getExtension(tempFileName).equals("mp3")) {
                    information.add(FilePathParser.getFileName(tempFileName));
                }
            }
        }
        return information;
    }

    public static boolean makeDirectory(final String fileAddress) {
        try {
            File file = new File(fileAddress);
            return file.mkdir();
        } catch (SecurityException e) {
            return false;
        }
    }

    public static void renameTextFile(final String fileAddress, final String fileName, final String newName) {
        renameFile(fileAddress, fileName, newName, ".txt");
    }

    public static void renameMP3File(final String fileAddress, final String fileName, final String newName) {
        renameFile(fileAddress, fileName, newName, ".mp3");
    }

    private static void renameFile(final String fileAddress, final String fileName, final String newName, final String type) {
        String path = fileAddress;
        File oldFile = new File(path, fileName + type);
        if (oldFile.isFile()) {
            File newFile = new File(path, newName + type);
            oldFile.renameTo(newFile);
        }
    }
}

package FileIO;

import java.io.*;
import java.util.ArrayList;

public class FileIO {
    public static ArrayList<String> readTextFile(final String fileAddress, final String fileName) {

        ArrayList<String> information = new ArrayList<>();
        String readLine;
        File file = new File(fileAddress, fileName + ".txt");
        BufferedReader input;

        try {
            input = new BufferedReader(new InputStreamReader(
                    new FileInputStream(fileAddress + "//" + fileName + ".txt"), "UTF-8"));
        } catch (Exception e) {
            try {
                FileWriter fw = new FileWriter(file);
                fw.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            return null;
        }
        try {
            while ((readLine = input.readLine()) != null) {

                if (readLine.startsWith("//") || (readLine.trim()).equals("")) {
                    continue;
                } else {
                    information.add(readLine);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return information;
    }

    public static void writeTextFile(final String fileInformationAddress, final String fileName, String[] writeInformation, final String delimiter) {
        try {
            File file = new File(fileInformationAddress, fileName + ".txt");
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            for (String aWriteInformation : writeInformation) {
                writer.write(aWriteInformation);
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

    private static int readLine(final String fileAddress, final String fileName) {
        int lineNum = 0;

        String inputLine;
        File file = new File(fileAddress, fileName + ".txt");
        BufferedReader input;

        try {
            input = new BufferedReader(new InputStreamReader(
                    new FileInputStream(fileAddress + "//" + fileName + ".txt"), "UTF-8"));
        } catch (Exception e) {
            try {
                FileWriter fw = new FileWriter(file);
                fw.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            return 0;
        }

        try {
            while ((inputLine = input.readLine()) != null) {
                System.out.println(inputLine);
                if (inputLine.startsWith("//") || (inputLine.trim()).equals("")) continue;
                lineNum++;
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            input.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return lineNum;
    }

    public static void reNameTextFile(final String fileAddress, final String fileName, final String newName) {
        reNameFile(fileAddress, fileName, newName, ".txt");
    }

    public static void reNameMP3File(final String fileAddress, final String fileName, final String newName) {
        reNameFile(fileAddress, fileName, newName, ".mp3");
    }

    private static void reNameFile(final String fileAddress, final String fileName, final String newName, final String type) {
        File oldFile = new File(fileAddress, fileName + type);
        File newFile = new File(fileAddress, newName + type);
        oldFile.renameTo(newFile);
    }
}



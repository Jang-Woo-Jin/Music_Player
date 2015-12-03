package FileIO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
;

public class FileIO {
<<<<<<< HEAD
    public static String[] readTextFile(String fileAddress, String fileName) {

        int lineNum = readLine(fileAddress, fileName);
        String[] information = new String[lineNum];
        lineNum = 0;
=======
    public static ArrayList<String> readTextFile(final String fileAddress, final String fileName) {

        ArrayList<String> information = new ArrayList<String>();
>>>>>>> c6c7a654b9d29db9793671750a3ada88b50e1887
        String readLine = new String("");
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

                if (readLine.startsWith("//") || (readLine.trim()).equals("")) continue; 
                else {
<<<<<<< HEAD
                    information[lineNum] = readLine;

                    lineNum++;
=======
                    information.add(readLine);
>>>>>>> c6c7a654b9d29db9793671750a3ada88b50e1887
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

<<<<<<< HEAD
    public static void writeTextFile(String fileInformationAddress, String fileName, String[] writeInformation, final String delimiter) {
=======
    public static void writeTextFile(final String fileInformationAddress, final String fileName, String[] writeInformation, final String delimiter) {
>>>>>>> c6c7a654b9d29db9793671750a3ada88b50e1887
        try {
            File file = new File(fileInformationAddress, fileName + ".txt");
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            for (int i = 0; i < writeInformation.length; i++) {
                writer.write(writeInformation[i]);
                writer.write(delimiter);
            }
            writer.close();

        } catch (IOException e) {

            e.printStackTrace();
        }
    }

<<<<<<< HEAD
    public static ArrayList<String> readAllFileInPath(String fileAddress, String extend) {
=======
    public static ArrayList<String> readAllFileInPath(final String fileAddress, final String extend) {
>>>>>>> c6c7a654b9d29db9793671750a3ada88b50e1887
        File dirFile = new File(fileAddress);
        File[] fileList = dirFile.listFiles();
        ArrayList<String> information = new ArrayList<String>();
        int i = 0;
        for (File tempFile : fileList) {
            if (tempFile.isFile()) {
                String tempPath = tempFile.getParent();
                String tempFileName = tempFile.getName();
                if (FilePathParser.getExtension(tempFileName).equals(extend
                		)) {
                    information.add(FilePathParser.getFileName(tempFileName));
                }
            }
        }
        return information;
    }

<<<<<<< HEAD
    private static int readLine(String fileAddress, String fileName) {
=======
    private static int readLine(final String fileAddress, final String fileName) {
>>>>>>> c6c7a654b9d29db9793671750a3ada88b50e1887
        int lineNum = 0;

        String inputLine = new String("");
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

<<<<<<< HEAD
    public static void deleteTextFile(String fileAddress, String fileName){
    	 File file = new File(fileAddress+"//"+fileName + ".txt");
    	 if (file.delete()) {
    		 System.out.println("파일을 성공적으로 지웠습니다 : " + fileName);
    	 } 
    	 else {
    		 System.err.println("파일  지우기 실패 : " + fileName);
    	 }
    	 
    }
    
    public static void reNameTextFile(String fileAddress, String fileName, String newName) {
        reNameFile(fileAddress, fileName, newName, ".txt");
    }

    public static void reNameMP3File(String fileAddress, String fileName, String newName) {
        reNameFile(fileAddress, fileName, newName, ".mp3");
    }

    private static void reNameFile(String fileAddress, String fileName, String newName, String type) {
        String path = fileAddress;
        File oldfile = new File(path, fileName + ".txt");
        File newfile = new File(path, newName + ".txt");
=======
    public static void reNameTextFile(final String fileAddress, final String fileName, final String newName) {
        reNameFile(fileAddress, fileName, newName, ".txt");
    }

    public static void reNameMP3File(final String fileAddress,final  String fileName,final  String newName) {
        reNameFile(fileAddress, fileName, newName, ".mp3");
    }

    private static void reNameFile(final String fileAddress,final  String fileName,final  String newName,final  String type) {
        String path = fileAddress;
        File oldfile = new File(path, fileName + type);
        File newfile = new File(path, newName + type);
>>>>>>> c6c7a654b9d29db9793671750a3ada88b50e1887
        oldfile.renameTo(newfile);
    }
}



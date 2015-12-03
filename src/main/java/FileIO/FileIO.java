package FileIO;

import java.io.*;
import java.util.ArrayList;

public class FileIO {
    public static ArrayList<String> readTextFile(final String fileAddress, final String fileName) {

        ArrayList<String> information = new ArrayList<String>();
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

    public static void writeTextFile(final String fileInformationAddress, final String fileName, ArrayList<String> writeInformation, final String delimiter) {
    	File file = new File(fileInformationAddress, fileName + ".txt");
    	try {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file),"UTF-8"));
            
            for (String iter:writeInformation) {
                writer.write(iter);
                writer.write(delimiter);
            }
            writer.close();

        } catch (IOException e) {
        	FileWriter fw;
			try {
				fw = new FileWriter(file);
				 fw.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
           
            e.printStackTrace();
        }
    }

    public static ArrayList<String> readAllFileInPath(final String fileAddress, final String extend) {
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

    private static int readLine(final String fileAddress, final String fileName) {
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

    public static void mkdir(final String fileAddress){
    	
    }
    
    public static void reNameTextFile(final String fileAddress, final String fileName, final String newName) {
        renameFile(fileAddress, fileName, newName, ".txt");
    }

    public static void reNameMP3File(final String fileAddress, final String fileName, final String newName) {
        renameFile(fileAddress, fileName, newName, ".mp3");
    }

    private static void renameFile(final String fileAddress, final String fileName, final String newName, final String type) {
        String path = fileAddress;
        File oldfile = new File(path, fileName + type);
        File newfile = new File(path, newName + type);
        oldfile.renameTo(newfile);
    }
}



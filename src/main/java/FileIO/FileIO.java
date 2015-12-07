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

                if (!(readLine.startsWith("//") || (readLine.trim()).equals("")))
                    information.add(readLine);
            }
            input.close();
        } catch (IOException e) {
            new File(fileAddress, fileName + ".txt");
        }

        return information;
    }

    public static void writeTextFile(final String fileInformationAddress, final String fileName, ArrayList<String> writeInformation) {
        File file = new File(fileInformationAddress, fileName + ".txt");
        try {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));

            for (String iter : writeInformation) {
                writer.write(iter);
                writer.write("");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void makeDirectory(final String fileAddress) {
        try {
            new File(fileAddress).mkdir();
        } catch (SecurityException e) {
            return;
        }
    }
}

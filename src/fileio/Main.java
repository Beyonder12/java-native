package fileio;

import java.io.*;
import java.util.logging.Logger;

public class Main {

    private static final String DIR = "C:\\Users\\IFG Life\\Documents\\Github\\fajri\\java-native\\src\\fileio\\myNewFile.txt";

    private static final Logger log = Logger.getLogger("Main");


    public static void main(String[] args) throws IOException {
        String fileName = DIR + "myNewFile.txt";
        createFile(fileName);
        writeFile(fileName);
        readFile(fileName);
    }

    private static void readFile(String fileName) {
        File myFile = new File(fileName);

        // Ensure the file exists
        if (!myFile.exists()) {
            System.out.println("File does not exist.");
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(myFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }


    private static void writeFile(String dir) throws IOException {
        FileWriter fileWriter = new FileWriter(dir);
        fileWriter.write("Hello this is epicel\n");
        fileWriter.write("Hello this is epicel");
        fileWriter.close();
        log.info("Successfully wrote!");
    }
    private static void createFile(String dir) {
        try {
//            File myFile = new File("myNewFile.txt");
            File myFile = new File(dir);

            if (myFile.createNewFile()) {
                System.out.println("File created: " + myFile.getName());
            } else {

                log.info("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}

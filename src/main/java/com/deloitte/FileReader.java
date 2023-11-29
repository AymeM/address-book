package com.deloitte;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
public class FileReader {


    private static final Logger LOGGER = Logger.getLogger(FileReader.class.getName());


    // Passing a Consumer in the parameters to make this method generic to any kind of txt file following the same structure. (One object described per line)
    public void readFromFile(String filePath, Consumer<String> lineProcessor) throws RuntimeException {
        try {
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                lineProcessor.accept(line);
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            LOGGER.log(Level.SEVERE, "File not found: " + filePath, e);
            throw new RuntimeException("File not found: " + filePath, e);
        }
    }
}

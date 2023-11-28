package com.deloitte;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.function.Consumer;
public class FileReader {

    public void readFromFile(String filePath, Consumer<String> lineProcessor) throws FileNotFoundException {
        try {
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                lineProcessor.accept(line);
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            throw e; // Re-throw the exception to be handled by the caller
        }
    }
}

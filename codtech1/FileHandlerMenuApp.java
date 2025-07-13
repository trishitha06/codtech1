import java.io.*;
import java.util.Scanner;

/**
 * FileHandlerMenuApp
 *
 * A menu-driven Java application to perform basic file operations:
 * - Write to a file
 * - Read from a file
 * - Modify file content
 * - Exit the program
 *
 * Author: CODTECH Intern
 * Instructions: Follow on-screen menu prompts
 */
public class FileHandlerMenuApp {

    private static final String FILE_PATH = "sample.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n========= FILE HANDLING MENU =========");
            System.out.println("1. Write to File");
            System.out.println("2. Read from File");
            System.out.println("3. Modify File (Replace Word)");
            System.out.println("4. Exit");
            System.out.print("Enter your choice (1-4): ");
            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    writeToFile(scanner);
                    break;
                case 2:
                    readFromFile();
                    break;
                case 3:
                    System.out.print("Enter word to be replaced: ");
                    String target = scanner.nextLine();
                    System.out.print("Enter new word: ");
                    String replacement = scanner.nextLine();
                    modifyFile(target, replacement);
                    break;
                case 4:
                    System.out.println("Exiting the application. Thank you!");
                    break;
                default:
                    System.out.println("Invalid choice! Please select between 1 and 4.");
            }
        } while (choice != 4);

        scanner.close();
    }

    // Method to write user input to a file
    private static void writeToFile(Scanner scanner) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            System.out.println("Enter lines to write to the file (type 'exit' to stop):");
            while (true) {
                String line = scanner.nextLine();
                if (line.equalsIgnoreCase("exit")) break;
                writer.write(line);
                writer.newLine();
            }
            System.out.println("Content written successfully to '" + FILE_PATH + "'.");
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    // Method to read the contents of the file
    private static void readFromFile() {
        File file = new File(FILE_PATH);

        if (!file.exists()) {
            System.out.println("File does not exist. Please write something first.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            int lineNum = 1;
            System.out.println("\n--- File Content ---");
            while ((line = reader.readLine()) != null) {
                System.out.println("Line " + lineNum++ + ": " + line);
            }
            System.out.println("---------------------");
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }
    }

    // Method to modify the file (e.g., replace a word)
    private static void modifyFile(String target, String replacement) {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            System.out.println("File does not exist. Please write something first.");
            return;
        }

        StringBuilder content = new StringBuilder();

        // Read and replace
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line.replaceAll(target, replacement)).append(System.lineSeparator());
            }
        } catch (IOException e) {
            System.err.println("Error during modification: " + e.getMessage());
            return;
        }

        // Write back modified content
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(content.toString());
            System.out.println("All occurrences of '" + target + "' have been replaced with '" + replacement + "'.");
        } catch (IOException e) {
            System.err.println("Error writing modified content: " + e.getMessage());
        }
    }
}

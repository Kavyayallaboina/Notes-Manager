import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Scanner;
public class NotesManager {
    private static final String FILE_NAME = "notes.txt";
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;
        while (true) {
            System.out.println("\n--- NOTES MANAGER ---");
            System.out.println("1. Write Notes");
            System.out.println("2. Read Notes");
            System.out.println("3. Delete All Notes");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // consume newline
            switch (choice) {
                case 1:
                    writeNotes(sc);
                    break;
                case 2:
                    readNotes();
                    break;
                case 3:
                    deleteNotes();
                    break;
                case 4:
                    System.out.println("Exiting... Thank you!");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice, try again.");
            }
        }
    }
    // Write notes to file
    private static void writeNotes(Scanner sc) {
        System.out.println("Enter your notes (type 'END' on a new line to finish):");
        StringBuilder content = new StringBuilder();
        while (true) {
            String line = sc.nextLine();
            if (line.equalsIgnoreCase("END")) break;
            content.append(line).append("\n");
        }
        try (FileWriter writer = new FileWriter(FILE_NAME, true)) {
            writer.write(content.toString());
            System.out.println("Notes saved successfully.");
        } catch (IOException e) {
            System.out.println("Error writing to file.");
        }
    }
    // Read notes
    private static void readNotes() {
        System.out.println("\n--- SAVED NOTES ---");
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            boolean empty = true;
            while ((line = reader.readLine()) != null) {
                empty = false;
                System.out.println(line);
            }
            if (empty) {
                System.out.println("No notes found.");
            }
        } catch (IOException e) {
            System.out.println("No notes file found.");
        }
    }
    // Delete notes (clear file)
    private static void deleteNotes() {
        try (FileWriter writer = new FileWriter(FILE_NAME)) {
            writer.write("");
            System.out.println("All notes deleted successfully.");
        } catch (IOException e) {
            System.out.println("Error deleting notes.");
        }
    }
}

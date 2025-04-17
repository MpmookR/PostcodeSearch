package Menu;

import MinHeap.MinHeap;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class MinHeapMenu {

    private final MinHeap heap;

    public MinHeapMenu(MinHeap heap) {
        this.heap = heap;
    }

    /**
     * Displays the menu for managing postcodes using a MinHeap.
     */
    public void showMenu() {
        try (Scanner scanner = new Scanner(System.in)) {
            boolean running = true;

            while (running) {
                System.out.println("\n--- MinHeap Postcode Manager ---");
                System.out.println("1. Load postcode file");
                System.out.println("2. Count postcodes");
                System.out.println("3. Check postcode");
                System.out.println("4. Add postcode");
                System.out.println("5. Delete postcode");
                System.out.println("6. Output all postcodes to file");
                System.out.println("7. Exit");
                System.out.print("Enter your choice: ");

                String choice = scanner.nextLine().trim();

                switch (choice) {
                    case "1" -> {
                        System.out.print("Enter file path (e.g., inputFiles/1000_London_Postcodes.txt): ");
                        String filepath = scanner.nextLine().trim();
                        loadFromFile(filepath);
                    }
                    case "2" -> System.out.println("Total postcodes: " + heap.count());
                    case "3" -> {
                        System.out.print("Enter postcode to check: ");
                        String check = scanner.nextLine().trim();
                        System.out.println("Exists? " + heap.search(check));
                    }
                    case "4" -> {
                        System.out.print("Enter postcode to add: ");
                        String add = scanner.nextLine().trim();
                        boolean added = heap.insert(add);
                        System.out.println(added ? "Added successfully." : "Failed (duplicate or full).");
                    }
                    case "5" -> {
                        System.out.print("Enter postcode to delete: ");
                        String delete = scanner.nextLine().trim();
                        boolean deleted = heap.delete(delete);
                        System.out.println(deleted ? "Deleted successfully." : "Not found.");
                    }
                    case "6" -> {
                        System.out.print("Enter filename to save to (e.g., postcodes_output.txt): ");
                        String filename = scanner.nextLine().trim();
                        saveToFile(heap.getAllPostcodes(), filename);
                    }
                    case "7" -> {
                        running = false;
                        System.out.println("Returning to main menu...");
                    }
                    default -> System.out.println("Invalid option. Try again.");
                }
            }
        }
    }

    /**
     * Loads postcodes from a file and inserts them into the heap.
     */
    private void loadFromFile(String filename) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(filename));
            int inserted = 0;
            for (String line : lines) {
                if (heap.insert(line.trim())) {
                    inserted++;
                }
            }
            System.out.println("Loaded " + inserted + " postcodes from " + filename);
        } catch (IOException e) {
            System.out.println("Failed to load file: " + e.getMessage());
        }
    }

    /**
     * Saves all postcodes from the heap to a file.
     */
    private void saveToFile(List<String> postcodes, String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            for (String p : postcodes) {
                writer.write(p + "\n");
            }
            System.out.println("Saved to " + filename);
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}

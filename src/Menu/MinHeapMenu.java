package Menu;

import MinHeap.MinHeap;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class MinHeapMenu {
    private Scanner scanner;
    private MinHeap heap;

    public MinHeapMenu(MinHeap heap) {
        this.scanner = new Scanner(System.in);
        this.heap = heap;
    }

    public void showMenu() {
        boolean exit = false;

        while (!exit) {
            System.out.println("\n=== MinHeap Postcode Manager Menu ===");
            System.out.println("1. Load postcode file");
            System.out.println("2. Count postcodes");
            System.out.println("3. Add a postcode");
            System.out.println("4. Delete a postcode");
            System.out.println("5. Search for a postcode");
            System.out.println("6. Output sorted postcodes to default file (MinHeap_Output.txt)");
            System.out.println("7. Output sorted postcodes to a custom file");
            System.out.println("8. Exit");
            System.out.println("Choose an option: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                // postcodes get loaded
                case "1":
                    System.out.println("Choose a postcode file to load:");
                    System.out.println("1. 1000_London_Postcodes.txt");
                    System.out.println("2. 2000_London_Postcodes.txt");
                    System.out.println("3. 4000_London_Postcodes.txt");
                    System.out.println("4. 8000_London_Postcodes.txt");
                    System.out.println("5. 16000_London_Postcodes.txt");
                    System.out.println("Enter your choice: ");

                    String fileChoice = scanner.nextLine().trim();
                    String filename = "";

                    switch (fileChoice) {
                        case "1":
                            filename = "1000_London_Postcodes.txt";
                            break;
                        case "2":
                            filename = "2000_London_Postcodes.txt";
                            break;
                        case "3":
                            filename = "4000_London_Postcodes.txt";
                            break;
                        case "4":
                            filename = "8000_London_Postcodes.txt";
                            break;
                        case "5":
                            filename = "16000_London_Postcodes.txt";
                            break;
                        default:
                            System.out.println("Invalid file choice");
                            continue;
                    }

                    loadFromFile("inputFiles/" + filename);
                    break;

                // count postcodes
                case "2":
                    System.out.println("Total postcodes in MinHeap: " + heap.count());
                    break;

                // add postcode
                case "3":
                    System.out.println("Enter postcode to add: ");
                    String addPostcode = scanner.nextLine().trim();
                    if (heap.add(addPostcode)) {
                        System.out.println("Postcode added.");
                    } else {
                        System.out.println("Postcode already exists or heap is full.");
                    }
                    break;

                // delete postcode
                case "4":
                    System.out.println("Enter postcode to delete: ");
                    String deletePostcode = scanner.nextLine().trim();
                    if (heap.delete(deletePostcode)) {
                        System.out.println("Postcode deleted.");
                    } else {
                        System.out.println("Postcode not found.");
                    }
                    break;

                // search postcode
                case "5":
                    System.out.println("Enter postcode to search: ");
                    String searchPostcode = scanner.nextLine().trim();
                    if (heap.contains(searchPostcode)) {
                        System.out.println("Postcode found in MinHeap.");
                    } else {
                        System.out.println("Postcode not found.");
                    }
                    break;

                // output to default file
                case "6":
                    saveToFile(heap.getAllPostcodes(), "MinHeap_Output.txt");
                    break;

                // output to custom file
                case "7":
                    System.out.println("Enter filename to save sorted postcodes to: ");
                    String outputFilename = scanner.nextLine();
                    saveToFile(heap.getAllPostcodes(), outputFilename);
                    break;

                // exit
                case "8":
                    System.out.println("Exiting MinHeap menu...Goodbye!");
                    exit = true;
                    break;

                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    private void loadFromFile(String filename) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(filename));
            int inserted = 0;
            for (String line : lines) {
                if (heap.add(line.trim())) {
                    inserted++;
                }
            }
            System.out.println("Loaded " + inserted + " postcodes from " + filename);
        } catch (IOException e) {
            System.out.println("Failed to load file: " + e.getMessage());
        }
    }

    private void saveToFile(List<String> postcodes, String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            for (String postcode : postcodes) {
                writer.write(postcode + "\n");
            }
            System.out.println("Sorted postcodes saved to " + filename);
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}

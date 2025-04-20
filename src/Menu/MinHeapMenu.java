

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


  
   public MinHeapMenu() {
       this.scanner = new Scanner(System.in);
       this.heap = null; // will be initialized in loadFromFile
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
               case "1":
                   System.out.println("Choose a postcode file to load:");
                   System.out.println("1. 1000_London_Postcodes.txt");
                   System.out.println("2. 2000_London_Postcodes.txt");
                   System.out.println("3. 4000_London_Postcodes.txt");
                   System.out.println("4. 8000_London_Postcodes.txt");
                   System.out.println("5. 16000_London_Postcodes.txt");
                   System.out.println("Enter your choice: ");


                   String fileChoice = scanner.nextLine().trim();
                   String filename = switch (fileChoice) {
                       case "1" -> "1000_London_Postcodes.txt";
                       case "2" -> "2000_London_Postcodes.txt";
                       case "3" -> "4000_London_Postcodes.txt";
                       case "4" -> "8000_London_Postcodes.txt";
                       case "5" -> "16000_London_Postcodes.txt";
                       default -> {
                           System.out.println("Invalid file choice");
                           yield null;
                       }
                   };


                   if (filename != null) {
                       loadFromFile("inputFiles/" + filename);
                   }
                   break;


               case "2":
                   if (heapLoaded()) {
                       System.out.println("Total postcodes in MinHeap: " + heap.count());
                   }
                   break;


               case "3":
                   if (heapLoaded()) {
                       System.out.println("Enter postcode to add: ");
                       String addPostcode = scanner.nextLine().trim();
                       if (heap.add(addPostcode)) {
                           System.out.println("Postcode added.");
                       } else {
                           System.out.println("Postcode already exists or heap is full.");
                       }
                   }
                   break;


               case "4":
                   if (heapLoaded()) {
                       System.out.println("Enter postcode to delete: ");
                       String deletePostcode = scanner.nextLine().trim();
                       if (heap.delete(deletePostcode)) {
                           System.out.println("Postcode deleted.");
                       } else {
                           System.out.println("Postcode not found.");
                       }
                   }
                   break;


               case "5":
                   if (heapLoaded()) {
                       System.out.println("Enter postcode to search: ");
                       String searchPostcode = scanner.nextLine().trim();
                       if (heap.contains(searchPostcode)) {
                           System.out.println("Postcode found in MinHeap.");
                       } else {
                           System.out.println("Postcode not found.");
                       }
                   }
                   break;


               case "6":
                   if (heapLoaded()) {
                       saveToFile(heap.getAllPostcodes(), "MinHeap_Output.txt");
                   }
                   break;


               case "7":
                   if (heapLoaded()) {
                       System.out.println("Enter filename to save sorted postcodes to: ");
                       String outputFilename = scanner.nextLine();
                       saveToFile(heap.getAllPostcodes(), outputFilename);
                   }
                   break;


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
           this.heap = new MinHeap(lines.size());
           int inserted = 0;
           for (String line : lines) {
               if (heap.add(line.trim())) {
                   inserted++;
               }
           }
           System.out.println("Loaded " + inserted + " postcodes from " + filename);
           System.out.println("MinHeap max size is set to " + lines.size());
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


   private boolean heapLoaded() {
       if (heap == null) {
           System.out.println("Please load a postcode file first.");
           return false;
       }
       return true;
   }
}

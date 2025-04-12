package Menu;

import java.util.Scanner;
import BST.BST;

public class BSTMenu {
    private Scanner scanner;
    private BST bst;

    public BSTMenu(BST bst) {
        this.scanner = new Scanner(System.in);
        this.bst = bst;
    }

    public void showMenu() {
        boolean exit = false;

        while (!exit) {
            System.out.println("\n=== BST Postcode Manager Menu ===");
            System.out.println("1. Load postcode file");
            System.out.println("2. Count postcodes");
            System.out.println("3. Add a postcode");
            System.out.println("4. Delete a postcode");
            System.out.println("5. Searcg for a postcode");
            System.out.println("6. Output sorted postcodes to default file (BST_Output.txt)");
            System.out.println("7. Output sorted postcodes to a custom file");
            System.out.println("8. Exit");
            System.out.println("Choose an option: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                //postcodes get loaded
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
                case "2":
                    System.out.println("Total postcodes in BST: " + bst.count());
                    break;

                case "3":
                    System.out.println("Enter postcode to add: ");
                    String addPostcode = scanner.nextLine().trim();
                    if (bst.add(addPostcode)) {
                        System.out.println("Postcode added");
                    } else {
                        System.out.println("Postcode already exists.");
                    }
                    break;

                case "4":
                    System.out.println("Enter postcode to delete: ");
                    String deletePostcode = scanner.nextLine().trim();
                    if (bst.delete(deletePostcode)) {
                        System.out.println("Postcode deleted.");
                    } else {
                        System.out.println("Postcode not found");
                    }
                    break;

                case "5":
                    System.out.println("Enter postcode to search: ");
                    String searchPostcode = scanner.nextLine().trim();
                    if (bst.contains(searchPostcode)) {
                        System.out.println("Postcode found in Binary search tree!");

                    } else {
                        System.out.println("Postcode not found.");
                    }
                    break;

                case "6":
                   bst.saveToFile(); // saves to default file like "BST_Output.txt"
                   break;

               case "7":
                    System.out.println("Enter filename to save to: ");
                    String outFile = scanner.nextLine().trim();
                    bst.saveToFile(outFile);
                    break;

                case "8":
                    System.out.println("Exiting BST menu...Goodbye!");
                    exit = true;
                default:
                    System.out.println("Invalid option. Try again.");

            }

        }
    }
}

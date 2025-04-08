package Menu; //file inside the Menu folder 

// Terminal-based menu interface

import java.util.Scanner;
import AVL.AVL;

public class AVLMenu {
    private Scanner scanner;
    private AVL avl;

    public AVLMenu(AVL avl) {
        this.scanner = new Scanner(System.in);
        this.avl = avl;
    }

    public void showMenu() {
        boolean exit = false;

        while (!exit) {
            System.out.println("\n=== AVL Tree Postcode Manager Menu ====");
            System.out.println("1. Load the postcode file");
            System.out.println("2. Count postcodes");
            System.out.println("3. Add a postcode");
            System.out.println("4. Delete a postcode");
            System.out.println("5. Search for a postcode");
            System.out.println("6. Output the sorted postcodes to a default file (AVL_Output.txt)");
            System.out.println("7. Output the sorted postcodes to a custom file");
            System.out.println("8. Exit");
            System.out.println("Choose an option: ");

            String choice = scanner.nextLine();

            switch (choice) {
                // load postcodes
                case "1":
                    System.out.println("Please choose a postcode file to load:");
                    System.out.println("1. 1000_London_Postcodes.txt");
                    System.out.println("2. 2000_London_Postcodes.txt");
                    System.out.println("3. 4000_London_Postcodes.txt");
                    System.out.println("4. 8000_London_Postcodes.txt");
                    System.out.println("5. 16000_London_Postcodes.txt");
                    System.out.print("Enter your choice: ");

                    String fileChoice = scanner.nextLine();

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
                            System.out.println("Invalid choice. Returning to main menu!");
                            continue;
                    }
                    avl.getTree().clear(); // clear the tree before adding a new file
                    avl.getTree().loadFromFile("inputFiles/" + filename);
                    break;

                // count postcodes
                case "2":
                    System.out.println("Total postcodes in the AVL Tree: " + avl.count());
                    break;

                // add postcode to the tree
                case "3":
                    System.out.println("Enter postcode to add: ");
                    String addPostcode = scanner.nextLine();
                    if (avl.add(addPostcode)) {
                        System.out.println("Postcode added successfully!");
                    } else {
                        System.out.println("Postcode already exists in the Tree!");
                    }
                    break;

                // delete postcode
                case "4":
                    System.out.println("Enter postcode to delete from the tree: ");
                    String deletePostcode = scanner.nextLine();
                    if (avl.delete(deletePostcode)) {
                        System.out.println("Postcode deleted successfully!");
                    } else {
                        System.out.println("Postcode not found in the Tree!");
                    }
                    break;

                // search for a postcode
                case "5":
                    System.out.print("Enter postcode to search in the Tree: ");
                    String searchPostcode = scanner.nextLine();
                    if (avl.contains(searchPostcode)) {
                        System.out.println("Postcode found in the AVL Tree!");
                    } else {
                        System.out.println("Postcode not found in the Tree");
                    }
                    break;

                // output sorted to a default file
                case "6":
                    avl.getAllPostcodes();
                    break;

                case "7":
                    System.out.println("Enter the filename to save sorted postcodes to: ");
                    String outputFilename = scanner.nextLine();
                    avl.getAllPostcodes(outputFilename); // overloaded version of the method
                    break;

                // exit
                case "8":
                    System.out.println("Exiting the AVL menu. Goodbye!");
                    exit = true; // end the menu loop
                    break;

                default:
                    System.out.println("Invalid option! Please try again!");
            }

        }
    }

}
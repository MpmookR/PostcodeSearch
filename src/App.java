import interfaces.PostcodeManager;
import BST.BST;
import AVL.AVL;
import MinHeap.MinHeap;

import Menu.AVLMenu;
import Menu.BSTMenu;

import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        PostcodeManager manager = null;

        while (manager == null) {
            System.out.println("=== Welcome to the London Postcode Application ===");
            System.out.println("Please select the data structure you want to use:");
            System.out.println("1. Binary Search Tree (BST)");
            System.out.println("2. AVL Tree");
            System.out.println("3. Min Heap");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    manager = new BST();
                    System.out.println("Using Binary Search Tree...");
                    // launch the BST menu
                    BSTMenu bstMenu = new BSTMenu((BST) manager);
                    bstMenu.showMenu(); // user is shown BST option
                    manager = null; // reset to go back to select a different data structure.
                    break;
                case "2":
                    manager = new AVL(); // set AVL
                    System.out.println("Using AVL Tree...");
                    // launch the AVL menu
                    AVLMenu avlMenu = new AVLMenu((AVL) manager);
                    avlMenu.showMenu(); // user goes through the AVL options
                    manager = null; // reset to go back to the selection of data structure
                    break;
                case "3":
                    manager = new MinHeap();
                    System.out.println("Using Min Heap...");
                    break;
                case "4":
                    System.out.println("Exiting application. Goodbye!");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid option. Please try again.\n");
            }
        }

    }
}
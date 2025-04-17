import AVL.AVL;
import BST.BST;
import Menu.AVLMenu;
import Menu.MinHeapMenu;
import MinHeap.MinHeap;
import interfaces.PostcodeManager;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n=== Welcome to the London Postcode Application ===");
            System.out.println("Please select the data structure you want to use:");
            System.out.println("1. Binary Search Tree (BST)");
            System.out.println("2. AVL Tree");
            System.out.println("3. Min Heap");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> {
                    System.out.println("Using Binary Search Tree...");
                    PostcodeManager bst = new BST();
                    // Optional: Add BSTMenu if available
                    System.out.println("BST selected — feature not implemented yet.");
                }
                case "2" -> {
                    System.out.println("Using AVL Tree...");
                    AVL avl = new AVL();
                    AVLMenu avlMenu = new AVLMenu(avl);
                    avlMenu.showMenu();
                }
                case "3" -> {
                    System.out.println("Using Min Heap...");
                    MinHeap heap = new MinHeap(2000); // or adjust to your input file size
                    MinHeapMenu heapMenu = new MinHeapMenu(heap);
                    heapMenu.showMenu();
                }
                case "4" -> {
                    System.out.println("Exiting application. Goodbye!");
                    running = false;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }
}

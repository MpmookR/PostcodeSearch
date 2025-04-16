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
        PostcodeManager manager = null;

        while (manager == null) {
            System.out.println("=== Welcome to the London Postcode Application ===");
            System.out.println("Please select the data structure you want to use:");
            System.out.println("1. Binary Search Tree (BST)");
            System.out.println("2. AVL Tree");
            System.out.println("3. Min Heap");
            System.out.print("Enter your choice: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    manager = new BST();
                    System.out.println("Using Binary Search Tree...");
                    // Optional: Launch a menu if you have one for BST
                    break;
                case "2":
                    manager = new AVL();
                    System.out.println("Using AVL Tree...");
                    AVLMenu avlMenu = new AVLMenu((AVL) manager);
                    avlMenu.showMenu();
                    manager = null; // Reset to allow re-selection
                    break;
                    
                    case "3":
                    MinHeap heap = new MinHeap(1000); 
                    System.out.println("Using Min Heap...");
                    MinHeapMenu heapMenu = new MinHeapMenu(heap);
                    heapMenu.showMenu();
                    manager = null; // Reset like AVL
                    break;
                
            }
        }
    }
}

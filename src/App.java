import interfaces.PostcodeManager;
import BST.BST;
import AVL.AVL;
import MinHeap.MinHeap;

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
            System.out.print("Enter your choice: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    manager = new BST();
                    System.out.println("Using Binary Search Tree...");
                    break;
                case "2":
                    manager = new AVL();
                    System.out.println("Using AVL Tree...");
                    break;
                case "3":
                    manager = new MinHeap();
                    System.out.println("Using Min Heap...");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.\n");
            }
        }
        
    }
}
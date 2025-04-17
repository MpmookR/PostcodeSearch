import interfaces.PostcodeManager;
import BST.BST;
import AVL.AVL;
import MinHeap.MinHeap;

import Menu.AVLMenu;
import Menu.BSTMenu;
import Menu.MinHeapMenu;
import Menu.BenchmarkMenu;

import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n=== Welcome to the London Postcode Application ===");
            System.out.println("Please select the data structure you want to use:");
            System.out.println("1. Binary Search Tree (BST)");
            System.out.println("2. AVL Tree");
            System.out.println("3. Min Heap");
            System.out.println("4. Benchmarking Tool");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            String choice = scanner.nextLine();
            PostcodeManager manager = null;

            switch (choice) {
                case "1":
                    manager = new BST();
                    System.out.println("Using Binary Search Tree...");
                    BSTMenu bstMenu = new BSTMenu((BST) manager);
                    bstMenu.showMenu();
                    manager = null;
                    break;

                case "2":
                    manager = new AVL();
                    System.out.println("Using AVL Tree...");
                    AVLMenu avlMenu = new AVLMenu((AVL) manager);
                    avlMenu.showMenu();
                    manager = null;
                    break;

                case "3":
                    System.out.println("Using Min Heap...");
                    MinHeapMenu heapMenu = new MinHeapMenu();
                    heapMenu.showMenu();
                    break;
                    
                case "4":
                    System.out.println("Launching Benchmarking Tool...");
                    BenchmarkMenu benchmarkMenu = new BenchmarkMenu();
                    benchmarkMenu.showMenu();
                    break;

                case "5":
                    System.out.println("\nExiting application..... \nThank you for visiting us!");
                    running = false;
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }

        scanner.close();
    }
}

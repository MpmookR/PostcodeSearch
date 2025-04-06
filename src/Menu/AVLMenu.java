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
            System.out.println("3. Exit");
            System.out.println("Choose an option: ");

            String choice = scanner.nextLine();
        }
    }

}
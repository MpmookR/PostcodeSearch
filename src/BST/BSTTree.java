
package BST;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class BSTNode {
    String postcode; // the postcode to be stored
    BSTNode left;
    BSTNode right;

    BSTNode(String data) {
        this.postcode = data;
        this.left = null;
        this.right = null;
    }
}

public class BSTTree {
    private BSTNode root; // top of tree

    // constructor: creates an empty tree
    public BSTTree() {
        root = null; // placeholder for future tree
    }

    // load postcodes from a file and insert into the tree
    public void loadFromFile(String filename) {
        System.out.println("Loading postcodes from: " + filename);

        // try with resources used when scanner needs to be closed
        try (Scanner scanner = new Scanner(new File(filename))) { // closes scanner manually
            int inserted = 0;

            while (scanner.hasNextLine()) {
                String postcode = scanner.nextLine(); // read each line from the file

                if (postcode != null && !postcode.isEmpty()) {
                    insert(postcode); // insert postcode to the tree
                    inserted++; // count how many were added
                }
            }
            System.out.println("Loaded " + inserted + " postcodes from " + filename);
        } catch (FileNotFoundException e) {
            System.out.println("Error! File not found - " + filename);
        }
    }

    // Count method- returns the number of postcodes in the tree
    public int count() {
        System.out.println("Counting all postcodes in the tree...");
        return countRecursive(root);
    }

    private int countRecursive(BSTNode node) {
        if (node == null)
            return 0;
        return 1 + countRecursive(node.left) + countRecursive(node.right);
    }

    // Insert method
    public void insert(String postcode) {

        System.out.println("Inserting postcodes into BST: " + postcode);
        root = insertRecursive(root, postcode);
    }

    private BSTNode insertRecursive(BSTNode node, String postcode) {
        if (node == null) {
            return new BSTNode(postcode); // Found correct spot
        }
        int comparison = postcode.compareTo(node.postcode);
        if (comparison < 0) {
            node.left = insertRecursive(node.left, postcode);
        } else if (comparison > 0) {
            node.right = insertRecursive(node.right, postcode);
        } else {
            // for duplicates found we don't want them inserted.
            System.out.println("Duplicate postcode skipped: " + postcode);
        }

        return node;
    }

    // Delete method - deletes a postcode from the tree if it exists
    public boolean delete(String postcode) {
        if (!search(postcode)) {
            return false; // not found
        }

        root = deleteRecursive(root, postcode);
        return true;
    }

    private BSTNode deleteRecursive(BSTNode node, String postcode) {
        if (node == null)
            return null;

        int cmp = postcode.compareTo(node.postcode);
        if (cmp < 0) {
            node.left = deleteRecursive(node.left, postcode);
        } else if (cmp > 0) {
            node.right = deleteRecursive(node.right, postcode);
        } else {
            // node found
            if (node.left == null && node.right == null) {
                return null; // Leaf node
            } else if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            } else {
                // two children: replace with smallest in right subtree...
                BSTNode successor = findMin(node.right);
                node.postcode = successor.postcode;
                node.right = deleteRecursive(node.right, successor.postcode);
            }
        }
        return node;
    }

    private BSTNode findMin(BSTNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    // Search Method
    public boolean search(String postcode) {
        return searchRecursive(root, postcode);
    }

    private boolean searchRecursive(BSTNode node, String postcode) {
        if (node == null) {
            return false; // Not found
        }

        int comparison = postcode.compareTo(node.postcode);
        if (comparison == 0) {
            return true;
        } else if (comparison < 0) {
            return searchRecursive(node.left, postcode);
        } else {
            return searchRecursive(node.right, postcode);
        }
    }

    // In alphabetical order
    public String[] inOrder() {
        List<String> result = new ArrayList<>();
        inOrderTraversal(root, result);
        return result.toArray(new String[0]);
    }

    private void inOrderTraversal(BSTNode node, List<String> result) {
        if (node != null) {
            inOrderTraversal(node.left, result);
            result.add(node.postcode); // visit current node
            inOrderTraversal(node.right, result);
        }

    }
}

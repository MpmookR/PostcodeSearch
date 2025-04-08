// helper class with AVL tree logic
// AVL nodes
// insertion and rebalancing
// deletion
// rotations (LL, RR, LR, RL)
// search
// in-order traversal

package AVL;

import java.util.ArrayList;
import java.util.List;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class AVLNode {
    String postcode; // the postcode being stored
    int height; // to check if the part of the tree is unbalanced
    AVLNode left;
    AVLNode right; // links to child nodes

    AVLNode(String data) {
        this.postcode = data; // save the postcode
        this.height = 1; // new node has no children
        this.left = null;
        this.right = null;
    }
}

public class AVLTree {
    private AVLNode root; // top of the tree

    // constructor to create an empty tree
    public AVLTree() {
        root = null; // create an empty tree; placeholder for future tree
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

    // insert method
    public void insert(String postcode) {
        System.out.println("Inserting postcode: " + postcode); // print what is being added
        root = insert(root, postcode); // pass root and postcode
    }

    // recusrive insert
    private AVLNode insert(AVLNode node, String postcode) {
        if (node == null) {
            return new AVLNode(postcode);
        }

        if (postcode.compareTo(node.postcode) < 0) {
            node.left = insert(node.left, postcode);
        } else if (postcode.compareTo(node.postcode) > 0) {
            node.right = insert(node.right, postcode);
        } else {
            return node;
        }

        // update height
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));

        // balancing factor
        int balance = getBalance(node);

        // perform rotations for unbalanced tree

        // Left Left case
        if (balance > 1 && postcode.compareTo(node.left.postcode) < 0) {
            return rotateRight(node);
        }

        // Right Right Case
        if (balance < -1 && postcode.compareTo(node.right.postcode) > 0) {
            return rotateLeft(node);
        }

        // Left Right Case
        if (balance > 1 && postcode.compareTo(node.left.postcode) > 0) {
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }

        // Right Left Case
        if (balance < -1 && postcode.compareTo(node.right.postcode) < 0) {
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }

        return node; // no rotation needed
    }

    // getHeight of the node
    // ask the node: how tall are you?
    private int getHeight(AVLNode node) {
        if (node == null) {
            return 0; // if the node does not exist, its height is 0
        } else {
            return node.height; // otherwise give back the node's height
        }
    }

    // getBalance factor (left height - right height)
    private int getBalance(AVLNode node) {
        if (node == null) { // if the node is empty, the balance is 0 (not leaning)
            return 0;
        } else {
            int leftHeight = getHeight(node.left);
            int rightHeight = getHeight(node.right);
            return leftHeight - rightHeight; // if both nodes exist, check how tall the left and right sides are,
                                             // substract them to see if the tree is leaning
            // if left is taller - positive result +2
            // if right is taller - negative result -2
            // if both are the same - 0 (balanced)
        }
    }

    // rotateRight (for LL and LR cases)
    private AVLNode rotateRight(AVLNode y) {
        AVLNode x = y.left;
        AVLNode T2 = x.right; // T2 means Temporay Tree piece to be saved duting the rotation

        // rotation
        x.right = y;
        y.left = T2;

        // update height
        y.height = 1 + Math.max(getHeight(y.left), getHeight(y.right));
        x.height = 1 + Math.max(getHeight(x.left), getHeight(x.right));

        return x; // new root after rotation
    }

    // rotate left (for RR and RL cases)
    private AVLNode rotateLeft(AVLNode x) {
        AVLNode y = x.right;
        AVLNode T2 = y.left;

        // rotation
        y.left = x;
        x.right = T2;

        // update height
        x.height = 1 + Math.max(getHeight(x.left), getHeight(x.right));
        y.height = 1 + Math.max(getHeight(y.left), getHeight(y.right));

        return y; // new root after rotation
    }

    // public search method to check for the postcode inside the tree (Is this
    // postcode inside the tree?)
    public boolean search(String postcode) {
        System.out.println("Starting search for: " + postcode);
        return search(root, postcode); // pass the request to the method
    }

    private boolean search(AVLNode node, String postcode) {
        System.out.println("Checking node " + (node == null ? "null" : node.postcode));// ternary operator; if it is
                                                                                       // null - print null;
                                                                                       // else - print node.postcode
        if (node == null) {
            System.out.println("Postcode not found!");
            return false;
        }
        if (postcode.equals(node.postcode)) {
            System.out.println("Found postcode: " + postcode);
            return true; // if the postcode at this node matches the one you are searching
        } else if (postcode.compareTo(node.postcode) < 0) { // if your postcode is less than node's postcode - go left
            return search(node.left, postcode);
        } else { // if your postcode is greater - go right (e.g. in a dictionary - look first for
                 // apple and after for zebra)
            return search(node.right, postcode);
        }
    }

    // public method to return the number of postcodes in the tree
    public int count() {
        System.out.println("Counting all postcodes in the tree...");
        return count(root);
    }

    // private recursive counter
    private int count(AVLNode node) {
        if (node == null) {
            return 0; // nothing to count
        }
        System.out.println("Counting node: " + node.postcode);
        return 1 + count(node.left) + count(node.right); // root -> left -> right
    }

    // In-Order Traversal (Left - Node - Right) - will return a sroted list of
    // postcodes alphabetically
    public String[] inOrder() {
        System.out.println("Starting In-Order Traversal...");
        List<String> result = new ArrayList<>(); // arrays cannot be expanded(fixed in size); turn into list first
        inOrderTraversal(root, result);
        System.out.println("In-Order Traversal Completed. Returning the sorted postcodes array");
        return result.toArray(new String[0]); // convert list to String array (return the array of Strings with exact
                                              // size of
                                              // the list)
                                              // String[0] means create a String array Java will automatically figure
                                              // out the size
    }

    // helper method which adds the postcode to the same shared list
    private void inOrderTraversal(AVLNode node, List<String> result) {
        if (node != null) {
            System.out.println("Go left from: " + node.postcode);
            inOrderTraversal(node.left, result); // go left

            System.out.println("Visit node: " + node.postcode);
            result.add(node.postcode); // visit node; build the list, add the postcode result to the list

            System.out.println("Go right from: " + node.postcode);
            inOrderTraversal(node.right, result); // go right
        } else {
            System.out.println("Reached null branch, moving back up!");
        }
    }

    public boolean delete(String postcode) {
        if (!search(postcode)) {
            System.out.println("Postcode not found: " + postcode);
            return false;
        }

        System.out.println("Deleting postcode: " + postcode);
        AVLNode newRoot = delete(root, postcode); // delete node and get updated root
        root = newRoot; // reassign root in case it changed after deletion
        return true;
    }

    // recusrsive delete method
    private AVLNode delete(AVLNode node, String postcode) {
        if (node == null) {
            System.out.println("Reached null branch. Nothing to delete!");
            return null;
        }
        // treaverse the tree to find the right node
        if (postcode.compareTo(node.postcode) < 0) {
            System.out.println("Going left from: " + node.postcode);
            node.left = delete(node.left, postcode);
        } else if (postcode.compareTo(node.postcode) > 0) {
            System.out.println("Going right from: " + node.postcode);
            node.right = delete(node.right, postcode);

            // when comparison =0 e.g. "N1 2AB".compareTo("N1 2AB")→zero = postcode found
        } else {
            // found the node => proceed to deletion
            System.out.println("Found node to delete: " + node.postcode);

            if (node.left == null && node.right == null) {
                return null; // node has no children

            } else if (node.left == null) {
                return node.right; // node has only right child
            } else if (node.right == null) {
                return node.left; // node only has left child

            } else {
                // node has 2 children
                // find the smallest value in the right subtree(in-order successor)
                AVLNode successor = findMin(node.right);
                node.postcode = successor.postcode;
                node.right = delete(node.right, successor.postcode);
            }
        }

        // update height
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));

        // rebalance the tree
        // check balance
        int balance = getBalance(node);

        // rotation cases; rebalance if needed
        // LL case
        if (balance > 1 && getBalance(node.left) >= 0) {
            return rotateRight(node);
        }
        // LR case
        if (balance > 1 && getBalance(node.left) < 0) {
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }
        // RR case
        if (balance < -1 && getBalance(node.right) <= 0) {
            return rotateLeft(node);
        }
        // RL case
        if (balance < -1 && getBalance(node.right) > 0) {
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }
        return node; // return current node after deletion and rebalancing
    }

    // helper method to find smallest value in a subtree
    private AVLNode findMin(AVLNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    // avoid the filles adding one to another in the output
    public void clear() {
        root = null;
        System.out.println("AVL Tree has been cleared!");
    }

}

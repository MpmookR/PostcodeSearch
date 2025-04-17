// Main class 
// calls methods from BSTTree
// wrapper around BST class 

package BST;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;
import interfaces.PostcodeManager;

public class BST implements PostcodeManager {

    private BSTTree tree;

    public BST() {
        tree = new BSTTree();
        System.out.println("I am a binary search tree");
    }

    // public BSTTree getTree() {
    //     return tree;
    // } breaks encapsulation 
    public void loadFromFile(String filename) {
        tree.loadFromFile(filename);
    }
    
    public void clear() {
        tree = new BSTTree(); // Reset the tree by replacing it with a fresh one
        System.out.println("Binary search tree has been cleared");
    }
    
    @Override
    public int count() {
        return tree.count();
    }

    @Override
    public boolean contains(String postcode) {
        return tree.search(postcode);
    }

    @Override
    public boolean add(String postcode) {
        if (!tree.search(postcode)) {
            tree.insert(postcode);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean delete(String postcode) {
        return tree.delete(postcode);
    }
@Override
    // overloaded method with and without parameters for saving the file
    public List<String> getAllPostcodes() {
        // stick default name if none is provided
        return getAllPostcodes("BST_Output.txt");
    }

    // overloadded version to allow custom filenames
    public List<String> getAllPostcodes(String outputFilename) {
        String[] postcodes = tree.inOrder(); // call the inOrder() method from BSTTree class

        // write the sorted postcodes to a file
        try (PrintWriter writer = new PrintWriter(outputFilename)) {
            for (String postcode : postcodes) {
                writer.println(postcode); // one postcode per line
            }
            System.out.println("Sorted postcodes were also saved to " + outputFilename);
        } catch (FileNotFoundException e) {
            System.out.println("Error! Could not save the postcodes to a file!");
        }
        return List.of(postcodes); // Create a new List<String> from String[] array;
        // convert array to read only list to match interface return type
    }
    
}

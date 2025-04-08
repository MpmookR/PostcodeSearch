//main class implementation - implements the PostcodeManager interface 
//calls methods from AVLTree
//wrapper around AVLTree class 

package AVL;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;
import interfaces.PostcodeManager;

public class AVL implements PostcodeManager {

    private AVLTree tree;

    public AVL() {
        tree = new AVLTree();
        System.out.println("Hello! I am an AVL Tree");
    }

    public AVLTree getTree() {
        return tree;
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
        return getAllPostcodes("AVL_Output.txt");
    }

    // overloadded version to allow custom filenames
    public List<String> getAllPostcodes(String outputFilename) {
        String[] postcodes = tree.inOrder(); // call the inOrder() method from AVLTree class

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
        // read-only
        // need to convert the array back to the list, to satisfy the interface return
        // type
    }

}

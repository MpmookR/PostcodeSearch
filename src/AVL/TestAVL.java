package AVL;

public class TestAVL {
    public static void main(String[] args) {
        System.out.println("===== Starting AVL Tree Tests =====\n");

        AVLTree tree = new AVLTree();

        // Test: Insert postcodes
        String[] postcodesToAdd = { "E1 6AN", "W1A 1AA", "SW1A 0AA", "N1 9GU", "SE1 7PB" };
        for (String pc : postcodesToAdd) {
            tree.insert(pc);
        }

        // Attempt to insert duplicates
        System.out.println("\n===== Duplicate Insertion Test =====");
           
        int countBefore = tree.count();

        System.out.println("Inserting duplicate postcode: SW1A 0AA");
        tree.insert("SW1A 0AA");  // Should not be added again
        System.out.println("Inserting duplicate postcode: N1 9GU");
        tree.insert("N1 9GU");    // Should not be added again

        int countAfter = tree.count();

        System.out.println("Postcode count before duplicates: " + countBefore);
        System.out.println("Postcode count after duplicates: " + countAfter);

        //check for duplicaiton 
        if (countBefore == countAfter) {
        System.out.println("Duplicates were not inserted, as expected.");
        } else {
        System.out.println("Unexpected change in count — duplicates may have been inserted.");
        }

        System.out.println("\n===== In-Order Traversal after Insertions =====");
        String[] sorted = tree.inOrder();
        for (String pc : sorted) {
            System.out.println(pc);
        }

        // Test: Search for a postcode
        System.out.println("\n===== Search Tests =====");
        String searchPostcode = "SW1A 0AA";
        System.out.println(
                "Searching for " + searchPostcode + ": " + (tree.search(searchPostcode) ? "Found" : "Not Found"));

        String missingPostcode = "ZZ9 9ZZ";
        System.out.println(
                "Searching for " + missingPostcode + ": " + (tree.search(missingPostcode) ? "Found" : "Not Found"));

        // Test: Count the number of postcodes
        System.out.println("\nTotal number of postcodes in the tree: " + tree.count());

        // Deletion Tests
        System.out.println("\n===== Deletion Tests for Edge Cases =====");

        // Delete leaf node (0 children)
        String leafNode = "SE1 7PB";
        System.out.println("Deleting leaf node: " + leafNode);
        tree.delete(leafNode);

        // Delete node with one child
        String oneChildNode = "W1A 1AA";
        System.out.println("Deleting one-child node: " + oneChildNode);
        tree.delete(oneChildNode);

        // Delete node with two children
        String twoChildrenNode = "E1 6AN";
        System.out.println("Deleting node with two children: " + twoChildrenNode);
        tree.delete(twoChildrenNode);

        // In-Order Traversal to verify AVL structure
        System.out.println("\n===== In-Order Traversal after Deletions =====");
        sorted = tree.inOrder();
        for (String pc : sorted) {
            System.out.println(pc);
        }

        // Final count
        System.out.println("\nFinal postcode count: " + tree.count());

        // Clear test
        tree.clear();
        System.out.println("\nPostcode count after clearing: " + tree.count());

        System.out.println("\n===== AVL Tree Tests Complete =====");
    }
}
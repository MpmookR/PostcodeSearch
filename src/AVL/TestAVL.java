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

        // Test: Delete a postcode
        String deletePostcode = "W1A 1AA";
        System.out.println("\nDeleting postcode: " + deletePostcode);
        boolean deleted = tree.delete(deletePostcode);
        System.out.println("Deleted: " + deleted);

        // Verify deletion
        System.out.println("\n===== In-Order Traversal after Deletion =====");
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
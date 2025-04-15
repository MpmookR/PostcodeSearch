package BST;

public class BSTTest {
    public static void main(String[] args) {
        System.out.println("===== Running Basic BST API Tests =====");

        BST bst = new BST();

        // Test Insert/Add
        System.out.println("\n[TEST] Inserting postcodes...");
        bst.add("E1 6AN");
        bst.add("N1 9GU");
        bst.add("W1A 1AA");
        bst.add("E1 6AN"); // Duplicate, should be skipped

        // Test Count
        System.out.println("\n[TEST] Counting postcodes...");
        System.out.println("Total postcodes: " + bst.count());

        // Test Search
        System.out.println("\n[TEST] Searching postcodes...");
        System.out.println("Search E1 6AN: " + bst.contains("E1 6AN"));
        System.out.println("Search SW1A 2AA: " + bst.contains("SW1A 2AA"));

        // Test Delete
        System.out.println("\n[TEST] Deleting postcodes...");
        System.out.println("Delete W1A 1AA: " + bst.delete("W1A 1AA"));
        System.out.println("Delete SW1A 2AA (not in list): " + bst.delete("SW1A 2AA"));

        // Test InOrder
        System.out.println("\n[TEST] In-Order output (alphabetical):");
        for (String postcode : bst.getAllPostcodes()) {
            System.out.println(postcode);
        }

        System.out.println("===== BST API Test Complete =====");
    }
}

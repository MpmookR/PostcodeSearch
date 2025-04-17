package MinHeap;

public class TestMinHeap {
    public static void main(String[] args) {
        MinHeap heap = new MinHeap(10);

        System.out.println("=== MinHeap Test ===");

        // --- Insert postcodes ---
        System.out.println("\nInserting postcodes...");
        heap.insert("WIA 1AA");
        heap.insert("N1 9GU");
        heap.insert("SE1 7PB");
        heap.insert("E1 6AN");
        heap.insert("SWIA 0AA");

        // --- Count ---
        System.out.println("\nPostcode count (expected 5): " + heap.count());

        // --- Search ---
        System.out.println("\nSearching for SWIA 0AA (should be true): " + heap.search("SWIA 0AA"));
        System.out.println("Searching for ZZ9 9ZZ (should be false): " + heap.search("ZZ9 9ZZ"));

        // --- Duplicate insert ---
        System.out.println("\nInserting duplicate SWIA 0AA (should be false): " + heap.insert("SWIA 0AA"));

        // --- Delete ---
        System.out.println("\nDeleting SE1 7PB (should be true): " + heap.delete("SE1 7PB"));
        System.out.println("Deleting non-existent XYZ 123 (should be false): " + heap.delete("XYZ 123"));

        // --- Get all postcodes (sorted) ---
        System.out.println("\nAll postcodes (sorted):");
        for (String postcode : heap.getAllPostcodes()) {
            System.out.println(postcode);
        }

        // --- Extract Minimum ---
        System.out.println("\nExtracting minimum (should be the smallest postcode): " + heap.extractMinimum());
        System.out.println("New heap count (expected 3): " + heap.count());
    }
}

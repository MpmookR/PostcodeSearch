package MinHeap;

public class TestMinHeap {
    public static void main(String[] args) {
        MinHeap heap = new MinHeap(10);

        System.out.println("=== MinHeap Test ===");

        // --- Insert postcodes ---
        System.out.println("\nInserting postcodes...");
        heap.add("WIA 1AA");
        heap.add("N1 9GU");
        heap.add("SE1 7PB");
        heap.add("E1 6AN");
        heap.add("SWIA 0AA");

        // --- Count ---
        System.out.println("\nPostcode count (expected 5): " + heap.count());

        // --- Search ---
        System.out.println("\nSearching for SWIA 0AA (should be true): " + heap.contains("SWIA 0AA"));
        System.out.println("Searching for ZZ9 9ZZ (should be false): " + heap.contains("ZZ9 9ZZ"));

        // --- Duplicate insert ---
        System.out.println("\nInserting duplicate SWIA 0AA (should be false): " + heap.contains("SWIA 0AA"));

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

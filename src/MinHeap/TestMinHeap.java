package MinHeap;

public class TestMinHeap {
    public static void main(String[] args) {
        MinHeap heap = new MinHeap(10);
        
        System.out.println("=== MinHeap Test ===");

        // Test insertions
        System.out.println("Inserting postcodes...");
        heap.insert("WIA 1AA");
        heap.insert("N1 9GU");
        heap.insert("SE1 7PB");
        heap.insert("E1 6AN");
        heap.insert("SWIA 0AA");

        // Test count
        System.out.println("Postcode count: " + heap.count());

        // Test search
        System.out.println("Search SWIA 0AA: " + heap.search("SWIA 0AA"));
        System.out.println("Search ZZ9 9ZZ: " + heap.search("ZZ9 9ZZ"));

        // Test duplicate insert
        System.out.println("Inserting duplicate SWIA 0AA: " + heap.insert("SWIA 0AA"));

        // Test delete
        System.out.println("Deleting SE1 7PB: " + heap.delete("SE1 7PB"));

        // Test getAllPostcodes
        System.out.println("All postcodes: " + heap.getAllPostcodes());
    }
}
package MinHeap;


import interfaces.PostcodeManager;
import java.util.ArrayList;
import java.util.List;


public class MinHeap implements PostcodeManager {
   private String[] heap;
   private int size;
   public final int capacity;


   public MinHeap(int maxSize) {
       if (maxSize <= 0) throw new IllegalArgumentException("Capacity must be positive");
       this.capacity = maxSize;
       this.heap = new String[capacity];
       this.size = 0;
   }


   @Override
   public int count() {
       return size;
   }


   @Override
   public boolean contains(String postcode) {
       if (postcode == null) return false;
       for (int i = 0; i < size; i++) {
           if (postcode.equals(heap[i])) {
               return true;
           }
       }
       return false;
   }


   @Override
   public boolean add(String postcode) {
       if (postcode == null || size >= capacity) return false;
       if (contains(postcode)) return false;


       heap[size] = postcode;
       siftUp(size);
       size++;
       return true;
   }


   @Override
   public boolean delete(String postcode) {
       if (postcode == null) return false;


       for (int i = 0; i < size; i++) {
           if (postcode.equals(heap[i])) {
               heap[i] = heap[size - 1]; // Replace with last element
               size--;
               siftDown(i); // Rebalance heap
               return true;
           }
       }
       return false;
   }


   @Override
   public List<String> getAllPostcodes() {
       MinHeap clone = new MinHeap(capacity);
       for (int i = 0; i < size; i++) {
           clone.add(heap[i]);
       }


       List<String> sortedPostcodes = new ArrayList<>();
       while (clone.count() > 0) {
           sortedPostcodes.add(clone.extractMinimum());
       }


       return sortedPostcodes;
   }


   public String extractMinimum() {
       if (size == 0) return null;


       String min = heap[0];
       heap[0] = heap[size - 1];
       size--;
       siftDown(0);
       return min;
   }


   // ---------- Helper Methods ----------


   private void siftUp(int i) {
       while (i > 0) {
           int parent = (i - 1) / 2;
           if (heap[i].compareTo(heap[parent]) < 0) {
               swap(i, parent);
               i = parent;
           } else {
               break;
           }
       }
   }


   private void siftDown(int i) {
       while (true) {
           int left = 2 * i + 1;
           int right = 2 * i + 2;
           int smallest = i;


           if (left < size && heap[left].compareTo(heap[smallest]) < 0) {
               smallest = left;
           }
           if (right < size && heap[right].compareTo(heap[smallest]) < 0) {
               smallest = right;
           }


           if (smallest != i) {
               swap(i, smallest);
               i = smallest;
           } else {
               break;
           }
       }
   }


   private void swap(int i, int j) {
       String temp = heap[i];
       heap[i] = heap[j];
       heap[j] = temp;
   }
}

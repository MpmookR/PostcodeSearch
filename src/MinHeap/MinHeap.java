package MinHeap;

import interfaces.PostcodeManager;
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
        System.out.println("Hello! I am a MinHeap");
    }

    @Override
    public int count() {
        return size;
    }

    @Override
    //change search to contains
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
    //change insert to add
    public boolean add(String postcode) {
        if (postcode == null || size >= capacity) return false;
        if (contains(postcode)) return false; // Prevent duplicates

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
                heap[i] = heap[size - 1]; // Replace with last
                size--;
                siftDown(i); // Rebalance
                return true;
            }
        }
        return false;
    }

    @Override
    public List<String> getAllPostcodes() {
        return List.of(inOrder());
    }

    public String extractMinimum() {
        if (size == 0) return null;

        String min = heap[0];
        heap[0] = heap[size - 1];
        size--;
        siftDown(0);
        return min;
    }

    /**
     * Returns all postcodes in ascending (alphabetical) order.
     * This method does NOT modify the original heap.
     */
    public String[] inOrder() {
        MinHeap tempHeap = new MinHeap(capacity);
        for (int i = 0; i < size; i++) {
            tempHeap.add(heap[i]);
        }

        String[] result = new String[tempHeap.size];
        int i = 0;
        while (tempHeap.count() > 0) {
            result[i++] = tempHeap.extractMinimum();
        }
        return result;
    }

    // ---------- Helper Methods ----------

    private void siftUp(int i) {
        if (i < 0 || i >= size) return;

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
        if (i < 0 || i >= size) return;

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

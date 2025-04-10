package MinHeap;

import interfaces.PostcodeManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MinHeap implements PostcodeManager {
    private String[] heap;
    private int size;
    private final int DEFAULT_CAPACITY = 1000;

    public MinHeap() {
        this.heap = new String[DEFAULT_CAPACITY];
        this.size = 0;
    }

    @Override
    public int count() {
        return size;
    }

    @Override
    public boolean contains(String postcode) {
        for (int i = 0; i < size; i++) {
            if (heap[i].equals(postcode)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean add(String postcode) {
        if (size >= heap.length) return false;

        if (contains(postcode)) return false;

        heap[size] = postcode;
        siftUp(size);
        size++;
        return true;
    }

    @Override
    public boolean delete(String postcode) {
        for (int i = 0; i < size; i++) {
            if (heap[i].equals(postcode)) {
                heap[i] = heap[size - 1];
                size--;
                siftDown(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public List<String> getAllPostcodes() {
        MinHeap copy = this.copy();
        List<String> sorted = new ArrayList<>();
        while (copy.size > 0) {
            sorted.add(copy.extractMin());
        }
        return sorted;
    }

    // ------------------- Internal Methods -------------------

    private void siftUp(int i) {
        while (i > 0) {
            int parent = (i - 1) / 2;
            if (heap[i].compareTo(heap[parent]) < 0) {
                swap(i, parent);
                i = parent;
            } else break;
        }
    }

    private void siftDown(int i) {
        while (i < size) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;
            int smallest = i;

            if (left < size && heap[left].compareTo(heap[smallest]) < 0) smallest = left;
            if (right < size && heap[right].compareTo(heap[smallest]) < 0) smallest = right;

            if (smallest != i) {
                swap(i, smallest);
                i = smallest;
            } else break;
        }
    }

    private String extractMin() {
        if (size == 0) return null;

        String min = heap[0];
        heap[0] = heap[size - 1];
        size--;
        siftDown(0);
        return min;
    }

    private void swap(int i, int j) {
        String tmp = heap[i];
        heap[i] = heap[j];
        heap[j] = tmp;
    }

    private MinHeap copy() {
        MinHeap copy = new MinHeap();
        copy.size = this.size;
        copy.heap = Arrays.copyOf(this.heap, this.heap.length);
        return copy;
    }
}

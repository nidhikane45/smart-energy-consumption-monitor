package Heap;
import common.Appliance;

public class MaxHeap {
    private Appliance[] heap;
    private int size;

    public MaxHeap(int capacity) {
        heap = new Appliance[capacity];
        size = 0;
    }

    public void insert(Appliance a) {
        heap[size] = a;
        int i = size;
        size++;

        while (i > 0 && heap[i].energy > heap[(i - 1) / 2].energy) {
            Appliance temp = heap[i];
            heap[i] = heap[(i - 1) / 2];
            heap[(i - 1) / 2] = temp;
            i = (i - 1) / 2;
        }
    }

    public Appliance getMax() {
        if (size == 0) return null;
        return heap[0];
    }
}

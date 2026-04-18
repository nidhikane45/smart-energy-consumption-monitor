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
        if (size == heap.length) {
            System.out.println("Heap is full!");
            return;
        }

        heap[size] = a;
        int i = size;
        size++;

        while (i > 0 && heap[i].getEnergy() > heap[(i - 1) / 2].getEnergy()) {
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
package hashtable;
import common.Appliance;

public class HashTableApplianceStorage {
    private Appliance[] table;
    private int size;

    public HashTableApplianceStorage(int size) {
        this.size = size;
        table = new Appliance[size];
    }

    private int hash(String key) {
        int sum = 0;
        for (int i = 0; i < key.length(); i++) {
            sum += key.charAt(i);
        }
        return sum % size;
    }
    
    public void add(String n, int p, int h) {
        Appliance a = new Appliance(n, p, h);

        int index = hash(n);

        while (table[index] != null) {
            index = (index + 1) % size;
        } table[index] = a;
    }

    public void display() {
        for (int i = 0; i < size; i++) {
            if (table[i] != null) {
                System.out.println(table[i]);
            }
        }
    }
}

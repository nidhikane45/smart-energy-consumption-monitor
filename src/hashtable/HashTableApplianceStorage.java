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

    public String getAllData() {
        String result = "";
        for (int i = 0; i < size; i++) {
            if (table[i] != null) {
                result += table[i].toString() + "\n";
            }
        }
        return result;
    }

    public int getTotalEnergy() {
        int total = 0;
        for (int i = 0; i < size; i++) {
            if (table[i] != null) {
                total += table[i].energy;
            }
        }
        return total;
    }

    public Appliance search(String name) {
        int index = hash(name);
        int start = index;

        while (table[index] != null) {
            if (table[index].name.equals(name)) {
                return table[index];
            }
            index = (index + 1) % size;
            if (index == start) break;
        }
        return null;
    }
}

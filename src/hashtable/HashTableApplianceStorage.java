package hashtable;
import common.Appliance;

public class HashTableApplianceStorage {
    private Appliance[] table;
    private int size;

    public HashTableApplianceStorage(int size) {
        this.size = size;
        table = new Appliance[size];
    }

    public void add(String n, int p, int h) {
        //TO DO - implement hash table, linear probing
    }
}

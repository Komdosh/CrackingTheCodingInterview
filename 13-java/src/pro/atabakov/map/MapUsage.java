package pro.atabakov.map;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.TreeMap;

public class MapUsage {
    TreeMap<String, String> treeMap = new TreeMap<>();
    HashMap<String, String> hashMap = new HashMap<>();
    LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();

    static void main() {
        MapUsage mapUsage = new MapUsage();
        mapUsage.treeMapUsage();
        mapUsage.hashMapUsage();
        mapUsage.linkedHashMapUsage();
    }

    private void treeMapUsage() {
        for (int i = 0; i < 5; i++) {
            treeMap.put("" + i, "value" + i); // O(log(n))
        }

        for (String key : treeMap.descendingKeySet()) { // keys are always sorted
            System.out.println(key);
        }

        System.out.println(treeMap.get("1")); // O(log(n)) - this map doesn't use hash methods, so we have to traverse the whole tree
    }

    private void hashMapUsage() {
        for (int i = 0; i < 5; i++) {
            hashMap.put("" + i, "value" + i); // O(1)
        }

        // keys are stored in the hash table, so it doesn't have an order,
        // but it can be transformed in a tree in case of collisions
        for (String key : hashMap.keySet()) {
            System.out.println(key);
        }

        System.out.println(hashMap.get("1")); // O(1) - amortized cost, but O(log(n)) in case of collisions
    }

    private void linkedHashMapUsage() {
        for (int i = 0; i < 5; i++) {
            linkedHashMap.put("" + i, "value" + i); // O(1) - but entry stores a link to the previous entry (double linked list)
        }

        // keys are stored in the hash table, so it doesn't have an order,
        // but it can be transformed in a tree in case of collisions
        for (String key : linkedHashMap.sequencedKeySet()) { // Just get the first inserted entry and iterate over
            System.out.println(key);
        }

        System.out.println(linkedHashMap.get("1"));  // O(1) - amortized cost, but O(log(n)) in case of collisions, traditional hashmap
    }
}

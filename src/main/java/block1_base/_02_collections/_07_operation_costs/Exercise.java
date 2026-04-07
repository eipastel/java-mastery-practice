package block1_base._02_collections._07_operation_costs;

import java.util.*;

public class Exercise {

    public static void main(String[] args) {

        System.out.println("=== Operation Costs in Collections ===");
        System.out.println();

        int size = 200_000;

        demonstrateArrayList(size);
        demonstrateLinkedList(size);
        demonstrateHashSet(size);
        demonstrateHashMap(size);
        compareContains(size);
    }

    // -------------------------------------------------------
    // ArrayList
    // -------------------------------------------------------
    static void demonstrateArrayList(int size) {
        System.out.println("--- ArrayList ---");

        List<Integer> list = new ArrayList<>();

        // add at end: O(1) amortized
        long t = System.nanoTime();
        for (int i = 0; i < size; i++) {
            list.add(i);
        }
        System.out.println("add (end)          O(1)*  : " + ms(t) + " ms");

        // get by index: O(1)
        t = System.nanoTime();
        for (int i = 0; i < size; i++) {
            list.get(i);
        }
        System.out.println("get(i)             O(1)   : " + ms(t) + " ms");

        // set by index: O(1)
        t = System.nanoTime();
        for (int i = 0; i < size; i++) {
            list.set(i, i * 2);
        }
        System.out.println("set(i, v)          O(1)   : " + ms(t) + " ms");

        // remove at end: O(1)
        t = System.nanoTime();
        for (int i = 0; i < 10_000; i++) {
            list.remove(list.size() - 1);
        }
        System.out.println("remove(last)       O(1)   : " + ms(t) + " ms  (10k removals)");

        // add at front: O(n) — shifts all elements
        List<Integer> small = new ArrayList<>();
        t = System.nanoTime();
        for (int i = 0; i < 20_000; i++) {
            small.add(0, i); // insertion at front — shifts everything
        }
        System.out.println("add(0, v) front    O(n)   : " + ms(t) + " ms  (20k insertions at front)");

        System.out.println();
    }

    // -------------------------------------------------------
    // LinkedList
    // -------------------------------------------------------
    static void demonstrateLinkedList(int size) {
        System.out.println("--- LinkedList ---");

        LinkedList<Integer> list = new LinkedList<>();

        // addLast: O(1)
        long t = System.nanoTime();
        for (int i = 0; i < size; i++) {
            list.addLast(i);
        }
        System.out.println("addLast            O(1)   : " + ms(t) + " ms");

        // addFirst: O(1)
        t = System.nanoTime();
        for (int i = 0; i < 20_000; i++) {
            list.addFirst(i);
        }
        System.out.println("addFirst (20k)     O(1)   : " + ms(t) + " ms  (vs ArrayList O(n))");

        // get by index: O(n) — traverses nodes
        // Limited to 1000 accesses to avoid taking too long
        t = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            list.get(i);
        }
        System.out.println("get(i) (1k)        O(n)   : " + ms(t) + " ms  (very slow for random access)");

        // removeFirst / removeLast: O(1)
        t = System.nanoTime();
        for (int i = 0; i < 10_000; i++) {
            list.removeFirst();
        }
        System.out.println("removeFirst (10k)  O(1)   : " + ms(t) + " ms");

        System.out.println();
    }

    // -------------------------------------------------------
    // HashSet
    // -------------------------------------------------------
    static void demonstrateHashSet(int size) {
        System.out.println("--- HashSet ---");

        Set<Integer> set = new HashSet<>();

        // add: O(1) amortized
        long t = System.nanoTime();
        for (int i = 0; i < size; i++) {
            set.add(i);
        }
        System.out.println("add                O(1)*  : " + ms(t) + " ms");

        // contains: O(1)
        t = System.nanoTime();
        for (int i = 0; i < size; i++) {
            set.contains(i);
        }
        System.out.println("contains           O(1)   : " + ms(t) + " ms");

        // remove: O(1)
        t = System.nanoTime();
        for (int i = 0; i < size / 2; i++) {
            set.remove(i);
        }
        System.out.println("remove             O(1)   : " + ms(t) + " ms");

        System.out.println();
    }

    // -------------------------------------------------------
    // HashMap
    // -------------------------------------------------------
    static void demonstrateHashMap(int size) {
        System.out.println("--- HashMap ---");

        Map<Integer, String> map = new HashMap<>();

        // put: O(1)
        long t = System.nanoTime();
        for (int i = 0; i < size; i++) {
            map.put(i, "value" + i);
        }
        System.out.println("put                O(1)*  : " + ms(t) + " ms");

        // get: O(1)
        t = System.nanoTime();
        for (int i = 0; i < size; i++) {
            map.get(i);
        }
        System.out.println("get                O(1)   : " + ms(t) + " ms");

        // containsKey: O(1)
        t = System.nanoTime();
        for (int i = 0; i < size; i++) {
            map.containsKey(i);
        }
        System.out.println("containsKey        O(1)   : " + ms(t) + " ms");

        System.out.println();
    }

    // -------------------------------------------------------
    // Direct comparison: contains in List vs Set
    // -------------------------------------------------------
    static void compareContains(int size) {
        System.out.println("--- Comparison: contains() in List vs Set ---");

        List<Integer> list = new ArrayList<>();
        Set<Integer>  set  = new HashSet<>();

        for (int i = 0; i < size; i++) {
            list.add(i);
            set.add(i);
        }

        int target = size - 1; // worst case for the list (element at the end)

        long t = System.nanoTime();
        for (int i = 0; i < 10_000; i++) {
            list.contains(target); // O(n) — scans the entire list
        }
        System.out.println("ArrayList.contains (10k times)  O(n): " + ms(t) + " ms");

        t = System.nanoTime();
        for (int i = 0; i < 10_000; i++) {
            set.contains(target);  // O(1) — computes hash directly
        }
        System.out.println("HashSet.contains   (10k times)  O(1): " + ms(t) + " ms");

        System.out.println();
        System.out.println("Use Set/Map when you need to check existence frequently!");
    }

    // Utility: converts nanoseconds to formatted milliseconds
    static String ms(long start) {
        return String.format("%.2f", (System.nanoTime() - start) / 1_000_000.0);
    }
}

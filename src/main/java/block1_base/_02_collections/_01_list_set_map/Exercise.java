package block1_base._02_collections._01_list_set_map;

import java.util.*;

public class Exercise {

    public static void main(String[] args) {

        System.out.println("=== List, Set and Map ===");
        System.out.println();

        demonstrateList();
        demonstrateSet();
        demonstrateMap();
    }

    // -------------------------------------------------------
    // LIST: ordered sequence that allows duplicates
    // -------------------------------------------------------
    static void demonstrateList() {
        System.out.println("--- List (ArrayList) ---");

        List<String> fruits = new ArrayList<>();

        // add: inserts at the end
        fruits.add("banana");
        fruits.add("apple");
        fruits.add("orange");
        fruits.add("banana"); // duplicate allowed
        System.out.println("After adding: " + fruits);

        // access by index
        System.out.println("Element at index 1: " + fruits.get(1));

        // update by index
        fruits.set(1, "grape");
        System.out.println("After set(1, \"grape\"): " + fruits);

        // remove by index
        fruits.remove(0);
        System.out.println("After remove(0): " + fruits);

        // remove by value
        fruits.remove("orange");
        System.out.println("After remove(\"orange\"): " + fruits);

        // check existence
        System.out.println("Contains \"grape\": " + fruits.contains("grape"));
        System.out.println("Size: " + fruits.size());

        // iteration
        System.out.print("Iterating: ");
        for (String f : fruits) {
            System.out.print(f + " ");
        }
        System.out.println();

        // subList (view — not a copy)
        List<String> sub = fruits.subList(0, Math.min(2, fruits.size()));
        System.out.println("SubList [0, 2): " + sub);
        System.out.println();
    }

    // -------------------------------------------------------
    // SET: collection without duplicates
    // -------------------------------------------------------
    static void demonstrateSet() {
        System.out.println("--- Set (HashSet) ---");

        Set<String> colors = new HashSet<>();

        // add: returns false if already existed
        System.out.println("add(\"red\"):    " + colors.add("red"));
        System.out.println("add(\"blue\"):   " + colors.add("blue"));
        System.out.println("add(\"green\"):  " + colors.add("green"));
        System.out.println("add(\"red\"):    " + colors.add("red")); // duplicate ignored

        System.out.println("Set: " + colors);
        System.out.println("Size (without duplicate): " + colors.size()); // 3

        // contains: O(1)
        System.out.println("Contains \"blue\": " + colors.contains("blue"));
        System.out.println("Contains \"purple\": " + colors.contains("purple"));

        // remove
        colors.remove("blue");
        System.out.println("After removing \"blue\": " + colors);

        // LinkedHashSet preserves insertion order
        Set<String> ordered = new LinkedHashSet<>(Arrays.asList("c", "a", "b", "a"));
        System.out.println("LinkedHashSet (insertion order): " + ordered);

        // TreeSet sorts naturally
        Set<String> tree = new TreeSet<>(Arrays.asList("banana", "apple", "pineapple"));
        System.out.println("TreeSet (alphabetical order): " + tree);
        System.out.println();
    }

    // -------------------------------------------------------
    // MAP: key -> value mapping
    // -------------------------------------------------------
    static void demonstrateMap() {
        System.out.println("--- Map (HashMap) ---");

        Map<String, Integer> ages = new HashMap<>();

        // put: inserts or replaces
        ages.put("Ana", 30);
        ages.put("Bruno", 25);
        ages.put("Carla", 28);
        ages.put("Ana", 31); // replaces the value of key "Ana"

        System.out.println("Map: " + ages);
        System.out.println("Size: " + ages.size());

        // get: returns the value or null
        System.out.println("Bruno's age: " + ages.get("Bruno"));
        System.out.println("Zico's age (nonexistent): " + ages.get("Zico"));

        // getOrDefault: avoids null
        System.out.println("getOrDefault Zico: " + ages.getOrDefault("Zico", 0));

        // containsKey / containsValue
        System.out.println("Contains key \"Carla\": " + ages.containsKey("Carla"));
        System.out.println("Contains value 25: " + ages.containsValue(25));

        // removal
        ages.remove("Bruno");
        System.out.println("After removing \"Bruno\": " + ages);

        // efficient iteration via entrySet
        System.out.println("Iterating with entrySet:");
        for (Map.Entry<String, Integer> entry : ages.entrySet()) {
            System.out.println("  " + entry.getKey() + " -> " + entry.getValue());
        }

        // iteration with forEach (lambda)
        System.out.println("Iterating with forEach:");
        ages.forEach((name, age) -> System.out.println("  " + name + " is " + age + " years old"));
    }
}

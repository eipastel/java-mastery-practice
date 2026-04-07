package block1_base._02_collections._03_hashmap;

import java.util.*;

public class Exercise {

    public static void main(String[] args) {

        System.out.println("=== HashMap ===");
        System.out.println();

        // -------------------------------------------------------
        // Basic CRUD
        // -------------------------------------------------------
        System.out.println("--- Basic CRUD ---");

        Map<String, Integer> stock = new HashMap<>();

        // CREATE: put(key, value)
        stock.put("pen", 50);
        stock.put("notebook", 30);
        stock.put("eraser", 100);
        System.out.println("After insertion: " + stock);

        // READ: get(key)
        System.out.println("Stock of \"pen\":  " + stock.get("pen"));
        System.out.println("Stock of \"ruler\" (nonexistent): " + stock.get("ruler")); // null

        // UPDATE: put again with the same key replaces the value
        stock.put("pen", 75);
        System.out.println("After updating \"pen\" to 75: " + stock);

        // DELETE: remove(key)
        stock.remove("eraser");
        System.out.println("After removing \"eraser\": " + stock);
        System.out.println();

        // -------------------------------------------------------
        // getOrDefault: avoids NPE when accessing a missing key
        // -------------------------------------------------------
        System.out.println("--- getOrDefault ---");

        int rulerQty = stock.getOrDefault("ruler", 0);
        System.out.println("getOrDefault(\"ruler\", 0) = " + rulerQty);
        System.out.println();

        // -------------------------------------------------------
        // putIfAbsent: inserts only if the key does not exist
        // -------------------------------------------------------
        System.out.println("--- putIfAbsent ---");

        stock.putIfAbsent("pen", 999);     // does not replace — key already exists
        stock.putIfAbsent("scissors", 20); // inserts — key does not exist
        System.out.println("After putIfAbsent: " + stock);
        System.out.println();

        // -------------------------------------------------------
        // computeIfAbsent: computes and inserts if absent
        // Useful for maps of lists (grouping)
        // -------------------------------------------------------
        System.out.println("--- computeIfAbsent (grouping by category) ---");

        Map<String, List<String>> categories = new HashMap<>();

        addProduct(categories, "stationery", "pen");
        addProduct(categories, "stationery", "notebook");
        addProduct(categories, "electronics", "mouse");
        addProduct(categories, "stationery", "eraser");
        addProduct(categories, "electronics", "keyboard");

        categories.forEach((cat, prods) -> System.out.println("  " + cat + ": " + prods));
        System.out.println();

        // -------------------------------------------------------
        // merge: combines existing value with new value
        // -------------------------------------------------------
        System.out.println("--- merge (word count) ---");

        String[] words = {"java", "is", "great", "java", "is", "java"};
        Map<String, Integer> count = new HashMap<>();

        for (String w : words) {
            count.merge(w, 1, Integer::sum);
            // if key does not exist: inserts with value 1
            // if exists: adds the current value with 1
        }
        System.out.println("Count: " + count);
        System.out.println();

        // -------------------------------------------------------
        // Iteration
        // -------------------------------------------------------
        System.out.println("--- Iteration by entrySet (most efficient) ---");

        Map<String, Integer> prices = Map.of("pen", 2, "notebook", 15, "mouse", 80);

        for (Map.Entry<String, Integer> entry : prices.entrySet()) {
            System.out.println("  " + entry.getKey() + " => $" + entry.getValue());
        }
        System.out.println();

        System.out.println("--- Iteration with forEach + lambda ---");
        prices.forEach((product, price) ->
                System.out.println("  " + product + " costs $" + price));
        System.out.println();

        // -------------------------------------------------------
        // LinkedHashMap: preserves insertion order
        // -------------------------------------------------------
        System.out.println("--- LinkedHashMap (insertion order) ---");

        Map<String, Integer> ordered = new LinkedHashMap<>();
        ordered.put("c", 3);
        ordered.put("a", 1);
        ordered.put("b", 2);
        System.out.println("LinkedHashMap: " + ordered); // c, a, b — insertion order
    }

    // Helper method: adds a product to a category's list
    static void addProduct(Map<String, List<String>> map, String category, String product) {
        // computeIfAbsent: creates the list if the category does not exist, then adds the product
        map.computeIfAbsent(category, k -> new ArrayList<>()).add(product);
    }
}

package block1_base._02_collections._06_when_to_use_each;

import java.util.*;

public class Exercise {

    public static void main(String[] args) {

        System.out.println("=== When to use each collection? ===");
        System.out.println();

        scenarioOrderedList();
        scenarioUniqueness();
        scenarioLookupByKey();
        scenarioFIFOQueue();
        scenarioLIFOStack();
        scenarioNaturalOrder();
        scenarioInsertionOrder();
    }

    // -------------------------------------------------------
    // Scenario 1: list of items with order and possible duplicates
    // Ex: user action history
    // -------------------------------------------------------
    static void scenarioOrderedList() {
        System.out.println("--- Scenario 1: List with order and duplicates (ArrayList) ---");

        List<String> history = new ArrayList<>();
        history.add("login");
        history.add("viewed product A");
        history.add("added to cart");
        history.add("viewed product A"); // duplicate allowed
        history.add("checkout");

        System.out.println("Action history: " + history);
        System.out.println("Most recent action: " + history.get(history.size() - 1));
        System.out.println();
    }

    // -------------------------------------------------------
    // Scenario 2: guarantee element uniqueness
    // Ex: article tags, active user IDs
    // -------------------------------------------------------
    static void scenarioUniqueness() {
        System.out.println("--- Scenario 2: Uniqueness (HashSet) ---");

        Set<String> activeIds = new HashSet<>();
        activeIds.add("user_001");
        activeIds.add("user_002");
        activeIds.add("user_001"); // ignored — already exists

        System.out.println("Active IDs (no duplicates): " + activeIds);
        System.out.println("user_001 is active: " + activeIds.contains("user_001")); // O(1)
        System.out.println("user_999 is active: " + activeIds.contains("user_999")); // O(1)
        System.out.println();
    }

    // -------------------------------------------------------
    // Scenario 3: fast lookup by key
    // Ex: configuration cache, word frequency
    // -------------------------------------------------------
    static void scenarioLookupByKey() {
        System.out.println("--- Scenario 3: Lookup by key (HashMap) ---");

        Map<String, String> config = new HashMap<>();
        config.put("host", "localhost");
        config.put("port", "8080");
        config.put("db",   "production");

        // O(1) to look up by key
        System.out.println("Host: " + config.getOrDefault("host", "127.0.0.1"));
        System.out.println("Timeout: " + config.getOrDefault("timeout", "30s")); // missing key

        // Word count
        String text = "the cat saw the mouse and the mouse ran";
        Map<String, Integer> freq = new HashMap<>();
        for (String word : text.split(" ")) {
            freq.merge(word, 1, Integer::sum);
        }
        System.out.println("Word frequency: " + freq);
        System.out.println();
    }

    // -------------------------------------------------------
    // Scenario 4: FIFO queue (first in, first out)
    // Ex: print queue, task queue
    // -------------------------------------------------------
    static void scenarioFIFOQueue() {
        System.out.println("--- Scenario 4: FIFO Queue (ArrayDeque) ---");

        Deque<String> printQueue = new ArrayDeque<>();
        printQueue.offer("document1.pdf"); // adds to the end
        printQueue.offer("report.xlsx");
        printQueue.offer("photo.png");

        System.out.println("Print queue: " + printQueue);

        while (!printQueue.isEmpty()) {
            String next = printQueue.poll(); // removes from the front (FIFO)
            System.out.println("  Printing: " + next);
        }
        System.out.println();
    }

    // -------------------------------------------------------
    // Scenario 5: LIFO stack (last in, first out)
    // Ex: browser navigation history (back), undo/redo
    // -------------------------------------------------------
    static void scenarioLIFOStack() {
        System.out.println("--- Scenario 5: LIFO Stack (ArrayDeque) ---");

        Deque<String> pageHistory = new ArrayDeque<>();
        pageHistory.push("home page"); // push onto stack
        pageHistory.push("products");
        pageHistory.push("product detail");
        pageHistory.push("cart");

        System.out.println("Current page: " + pageHistory.peek());

        System.out.println("Going back (pop):");
        while (!pageHistory.isEmpty()) {
            System.out.println("  Went back to: " + pageHistory.pop()); // pop (LIFO)
        }
        System.out.println();
    }

    // -------------------------------------------------------
    // Scenario 6: elements always in natural order
    // Ex: ranking, name directory
    // -------------------------------------------------------
    static void scenarioNaturalOrder() {
        System.out.println("--- Scenario 6: Natural order (TreeSet / TreeMap) ---");

        // TreeSet: elements always sorted
        Set<String> names = new TreeSet<>();
        names.add("Carlos");
        names.add("Ana");
        names.add("Bruno");
        names.add("Ana"); // duplicate ignored
        System.out.println("Names in alphabetical order: " + names);

        // TreeMap: keys always sorted
        Map<Integer, String> ranking = new TreeMap<>();
        ranking.put(3, "Bronze");
        ranking.put(1, "Gold");
        ranking.put(2, "Silver");
        System.out.println("Sorted ranking: " + ranking);
        System.out.println();
    }

    // -------------------------------------------------------
    // Scenario 7: maintain insertion order but without duplicates
    // Ex: list of unique visitors in sequence
    // -------------------------------------------------------
    static void scenarioInsertionOrder() {
        System.out.println("--- Scenario 7: Insertion order without duplicates (LinkedHashSet) ---");

        Set<String> visitors = new LinkedHashSet<>();
        visitors.add("Alice");
        visitors.add("Bob");
        visitors.add("Alice"); // duplicate — ignored
        visitors.add("Carol");
        visitors.add("Bob");   // duplicate — ignored

        System.out.println("Unique visitors (arrival order): " + visitors);

        System.out.println("--- Scenario 7b: Map with insertion order (LinkedHashMap) ---");
        Map<String, Integer> orders = new LinkedHashMap<>();
        orders.put("pizza",   35);
        orders.put("soda",     8);
        orders.put("dessert", 12);
        System.out.println("Order (insertion order): " + orders);
    }
}

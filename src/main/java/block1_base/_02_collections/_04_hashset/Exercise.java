package block1_base._02_collections._04_hashset;

import java.util.*;

public class Exercise {

    // -------------------------------------------------------
    // Class WITHOUT equals/hashCode — HashSet does not detect duplicates
    // -------------------------------------------------------
    static class ProductWithoutContract {
        String name;
        int code;

        ProductWithoutContract(String name, int code) {
            this.name = name;
            this.code = code;
        }

        @Override
        public String toString() {
            return name + "(" + code + ")";
        }
    }

    // -------------------------------------------------------
    // Class WITH equals/hashCode — HashSet correctly detects duplicates
    // -------------------------------------------------------
    static class ProductWithContract {
        String name;
        int code;

        ProductWithContract(String name, int code) {
            this.name = name;
            this.code = code;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof ProductWithContract)) return false;
            ProductWithContract p = (ProductWithContract) o;
            return code == p.code && Objects.equals(name, p.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, code);
        }

        @Override
        public String toString() {
            return name + "(" + code + ")";
        }
    }

    public static void main(String[] args) {

        System.out.println("=== HashSet ===");
        System.out.println();

        // -------------------------------------------------------
        // Uniqueness with Strings (String already has equals/hashCode)
        // -------------------------------------------------------
        System.out.println("--- Uniqueness with Strings ---");

        Set<String> tags = new HashSet<>();
        System.out.println("add(\"java\"):   " + tags.add("java"));
        System.out.println("add(\"stream\"): " + tags.add("stream"));
        System.out.println("add(\"java\"):   " + tags.add("java")); // duplicate
        System.out.println("Set: " + tags);
        System.out.println("Size: " + tags.size()); // 2, not 3
        System.out.println();

        // -------------------------------------------------------
        // contains: O(1) — much faster than List.contains()
        // -------------------------------------------------------
        System.out.println("--- contains() in O(1) ---");
        System.out.println("Contains \"stream\": " + tags.contains("stream"));
        System.out.println("Contains \"kotlin\": " + tags.contains("kotlin"));
        System.out.println();

        // -------------------------------------------------------
        // Set operations
        // -------------------------------------------------------
        System.out.println("--- Set operations ---");

        Set<Integer> a = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5));
        Set<Integer> b = new HashSet<>(Arrays.asList(4, 5, 6, 7, 8));

        // Union
        Set<Integer> union = new HashSet<>(a);
        union.addAll(b);
        System.out.println("A union B: " + union);

        // Intersection
        Set<Integer> intersection = new HashSet<>(a);
        intersection.retainAll(b);
        System.out.println("A intersection B: " + intersection);

        // Difference (A - B)
        Set<Integer> difference = new HashSet<>(a);
        difference.removeAll(b);
        System.out.println("A difference B: " + difference);
        System.out.println();

        // -------------------------------------------------------
        // Without equals/hashCode: HashSet does not detect duplicates!
        // -------------------------------------------------------
        System.out.println("--- Class WITHOUT equals/hashCode: duplicates not detected ---");

        Set<ProductWithoutContract> setWithoutContract = new HashSet<>();
        setWithoutContract.add(new ProductWithoutContract("Pen", 1));
        setWithoutContract.add(new ProductWithoutContract("Pen", 1)); // should be a duplicate

        System.out.println("Set without contract (expected size: 1, actual): " + setWithoutContract.size());
        System.out.println("Elements: " + setWithoutContract);
        System.out.println("Without equals/hashCode, the HashSet treats each instance as unique!");
        System.out.println();

        // -------------------------------------------------------
        // With equals/hashCode: HashSet correctly detects duplicates
        // -------------------------------------------------------
        System.out.println("--- Class WITH equals/hashCode: duplicates correctly detected ---");

        Set<ProductWithContract> setWithContract = new HashSet<>();
        boolean add1 = setWithContract.add(new ProductWithContract("Pen", 1));
        boolean add2 = setWithContract.add(new ProductWithContract("Pen", 1)); // duplicate

        System.out.println("First add: " + add1);   // true
        System.out.println("Second add:  " + add2);  // false
        System.out.println("Size: " + setWithContract.size()); // 1
        System.out.println("Elements: " + setWithContract);
        System.out.println();

        // -------------------------------------------------------
        // Remove duplicates from a list using Set
        // -------------------------------------------------------
        System.out.println("--- Remove duplicates from a list ---");
        List<String> withDuplicates = Arrays.asList("a", "b", "a", "c", "b", "d");
        Set<String> withoutDuplicates = new LinkedHashSet<>(withDuplicates); // LinkedHashSet preserves order
        System.out.println("With duplicates: " + withDuplicates);
        System.out.println("Without duplicates: " + withoutDuplicates);
    }
}

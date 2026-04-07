package block1_base._04_generics._01_type_parameter_T;

import java.util.Arrays;

public class Exercise {

    // -------------------------------------------------------
    // Generic class: Box<T>
    // T can be any type — defined at each usage
    // -------------------------------------------------------
    static class Box<T> {
        private T content;

        public void store(T item) {
            this.content = item;
            System.out.println("  Storing: " + item + " (" + item.getClass().getSimpleName() + ")");
        }

        public T get() {
            return content;
        }

        public boolean isEmpty() {
            return content == null;
        }

        @Override
        public String toString() {
            return "Box<" + (content == null ? "empty" : content.getClass().getSimpleName())
                    + ">{" + content + "}";
        }
    }

    // -------------------------------------------------------
    // Generic class with two type parameters: Pair<K, V>
    // -------------------------------------------------------
    static class Pair<K, V> {
        private final K key;
        private final V value;

        Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey()   { return key; }
        public V getValue() { return value; }

        @Override
        public String toString() {
            return "Pair(" + key + ", " + value + ")";
        }
    }

    // -------------------------------------------------------
    // Generic method: has its own type parameter <T>
    // Independent of the class — works with any type
    // -------------------------------------------------------
    static <T> void swap(T[] array, int i, int j) {
        T temp   = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    // Generic method that returns the greater of two Comparable values
    static <T extends Comparable<T>> T max(T a, T b) {
        return a.compareTo(b) >= 0 ? a : b;
    }

    // Generic method that formats an array as a string
    static <T> String formatArray(T[] array) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < array.length; i++) {
            sb.append(array[i]);
            if (i < array.length - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {

        System.out.println("=== Generic Type Parameters (T) ===");
        System.out.println();

        // -------------------------------------------------------
        // Box<String>
        // -------------------------------------------------------
        System.out.println("--- Box<String> ---");

        Box<String> textBox = new Box<>();
        textBox.store("Hello, Java!");
        String text = textBox.get(); // no cast needed
        System.out.println("  Content: " + text);
        System.out.println("  " + textBox);
        System.out.println();

        // -------------------------------------------------------
        // Box<Integer>
        // -------------------------------------------------------
        System.out.println("--- Box<Integer> ---");

        Box<Integer> numberBox = new Box<>();
        numberBox.store(42);
        int number = numberBox.get(); // no cast, type-safe
        System.out.println("  Content: " + number);
        System.out.println("  " + numberBox);
        System.out.println();

        // -------------------------------------------------------
        // Box<Box<String>> — nested generics
        // -------------------------------------------------------
        System.out.println("--- Nested box: Box<Box<String>> ---");

        Box<Box<String>> boxOfBox = new Box<>();
        boxOfBox.store(textBox);
        System.out.println("  Outer box: " + boxOfBox);
        System.out.println("  Inner content: " + boxOfBox.get().get());
        System.out.println();

        // -------------------------------------------------------
        // Pair<K, V> — two type parameters
        // -------------------------------------------------------
        System.out.println("--- Pair<String, Integer> ---");

        Pair<String, Integer> pair1 = new Pair<>("age", 30);
        Pair<String, Boolean> pair2 = new Pair<>("active", true);
        Pair<Integer, Double> pair3 = new Pair<>(1, 9.99);

        System.out.println("  " + pair1);
        System.out.println("  " + pair2);
        System.out.println("  " + pair3);
        System.out.println();

        // -------------------------------------------------------
        // Generic method: swap
        // -------------------------------------------------------
        System.out.println("--- Generic method: swap ---");

        String[] fruits = {"banana", "apple", "orange", "grape"};
        System.out.println("  Before: " + formatArray(fruits));
        swap(fruits, 0, 3);
        System.out.println("  After swap(0, 3): " + formatArray(fruits));

        Integer[] numbers = {10, 20, 30, 40, 50};
        System.out.println("  Before: " + formatArray(numbers));
        swap(numbers, 1, 3);
        System.out.println("  After swap(1, 3): " + formatArray(numbers));
        System.out.println();

        // -------------------------------------------------------
        // Generic method with bounded type: max
        // -------------------------------------------------------
        System.out.println("--- Generic method with bounded type: max ---");

        System.out.println("  max(10, 20)           = " + max(10, 20));
        System.out.println("  max(\"ana\", \"zeus\")    = " + max("ana", "zeus"));
        System.out.println("  max(3.14, 2.71)       = " + max(3.14, 2.71));
        System.out.println();

        // -------------------------------------------------------
        // Type safety: the compiler detects type errors
        // -------------------------------------------------------
        System.out.println("--- Type safety at compile time ---");
        System.out.println("  Box<String> does not accept Integer:");
        System.out.println("  // textBox.store(123); -> COMPILE ERROR");
        System.out.println("  Without generics, you would need a cast and the error would come at runtime.");
        System.out.println("  With generics, the error is detected before running the program.");
    }
}

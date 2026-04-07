package block1_base._01_memory._06_autoboxing_unboxing;

import java.util.ArrayList;
import java.util.List;

public class Exercise {

    public static void main(String[] args) {

        System.out.println("=== Autoboxing and Unboxing ===");
        System.out.println();

        // -------------------------------------------------------
        // Autoboxing: int -> Integer automatically
        // -------------------------------------------------------
        System.out.println("--- Autoboxing: primitive -> wrapper ---");

        int primitive = 42;
        Integer wrapper = primitive; // autoboxing: compiler inserts Integer.valueOf(42)

        System.out.println("int primitive = " + primitive);
        System.out.println("Integer wrapper = primitive  => " + wrapper);
        System.out.println("(the compiler generates: Integer.valueOf(primitive))");
        System.out.println();

        // -------------------------------------------------------
        // Unboxing: Integer -> int automatically
        // -------------------------------------------------------
        System.out.println("--- Unboxing: wrapper -> primitive ---");

        Integer w = 100;
        int p = w; // unboxing: compiler inserts w.intValue()

        System.out.println("Integer w = 100");
        System.out.println("int p = w  => " + p);
        System.out.println("(the compiler generates: w.intValue())");
        System.out.println();

        // -------------------------------------------------------
        // Autoboxing in collections
        // -------------------------------------------------------
        System.out.println("--- Autoboxing in collections ---");

        List<Integer> list = new ArrayList<>();
        list.add(1);  // autoboxing: int 1 -> Integer.valueOf(1)
        list.add(2);
        list.add(3);

        int first = list.get(0); // unboxing when assigning to int

        System.out.println("List: " + list);
        System.out.println("First element (int): " + first);
        System.out.println();

        // -------------------------------------------------------
        // Autoboxing in mixed arithmetic expressions
        // -------------------------------------------------------
        System.out.println("--- Autoboxing in arithmetic operations ---");

        Integer a = 10;
        Integer b = 20;
        int result = a + b; // unboxing of a and b, sum, result is int

        System.out.println("Integer a = 10, Integer b = 20");
        System.out.println("int result = a + b => " + result);
        System.out.println("(unboxing happens automatically in the operation)");
        System.out.println();

        // -------------------------------------------------------
        // PITFALL 1: NullPointerException when unboxing null
        // -------------------------------------------------------
        System.out.println("--- PITFALL: NullPointerException when unboxing null ---");

        Integer nullValue = null; // valid: wrapper can be null
        System.out.println("Integer nullValue = null");

        try {
            int attempt = nullValue; // unboxing: calls nullValue.intValue() => NPE!
            System.out.println("Result: " + attempt);
        } catch (NullPointerException e) {
            System.out.println("NullPointerException! nullValue.intValue() blew up.");
            System.out.println("Always check for null before unboxing.");
        }
        System.out.println();

        // -------------------------------------------------------
        // PITFALL 2: comparison with == can be surprising
        // -------------------------------------------------------
        System.out.println("--- PITFALL: == comparison with Integer outside the cache ---");

        Integer x = 1000; // outside the cache -128..127
        Integer y = 1000;
        System.out.println("Integer x = 1000, Integer y = 1000");
        System.out.println("x == y      : " + (x == y));       // false — different objects!
        System.out.println("x.equals(y) : " + x.equals(y));    // true
        System.out.println("Use equals() to compare numeric wrappers.");
        System.out.println();

        // -------------------------------------------------------
        // PITFALL 3: autoboxing in intensive loops (object creation cost)
        // -------------------------------------------------------
        System.out.println("--- Demo: autoboxing cost in a loop ---");

        long start = System.nanoTime();
        Long boxedSum = 0L;
        for (long i = 0; i < 100_000; i++) {
            boxedSum += i; // unboxing + sum + boxing ON EVERY ITERATION
        }
        long boxedTime = System.nanoTime() - start;

        start = System.nanoTime();
        long primitiveSum = 0L;
        for (long i = 0; i < 100_000; i++) {
            primitiveSum += i; // just a primitive operation
        }
        long primitiveTime = System.nanoTime() - start;

        System.out.println("Sum with Long (boxed)    : " + boxedSum     + " | time: " + boxedTime + " ns");
        System.out.println("Sum with long (primitive): " + primitiveSum + " | time: " + primitiveTime + " ns");
        System.out.println("(Primitives are faster in intensive numeric computations)");
    }
}

package block1_base._01_memory._05_wrapper_classes;

import java.util.ArrayList;
import java.util.List;

public class Exercise {

    public static void main(String[] args) {

        System.out.println("=== Wrapper Classes ===");
        System.out.println();

        // -------------------------------------------------------
        // Basic wrapper classes
        // -------------------------------------------------------
        System.out.println("--- Creating wrappers ---");

        Integer i = Integer.valueOf(42);   // explicit form (without autoboxing)
        Double  d = Double.valueOf(3.14);
        Boolean b = Boolean.valueOf(true);

        System.out.println("Integer  : " + i);
        System.out.println("Double   : " + d);
        System.out.println("Boolean  : " + b);
        System.out.println();

        // -------------------------------------------------------
        // Wrappers in collections (collections only accept objects)
        // -------------------------------------------------------
        System.out.println("--- Wrappers in collections ---");

        List<Integer> list = new ArrayList<>();
        list.add(10);  // autoboxing: int -> Integer
        list.add(20);
        list.add(30);

        System.out.println("Integer list: " + list);
        int sum = 0;
        for (Integer num : list) {
            sum += num; // unboxing: Integer -> int
        }
        System.out.println("Sum: " + sum);
        System.out.println();

        // -------------------------------------------------------
        // Integer Cache: values from -128 to 127 are cached
        // -------------------------------------------------------
        System.out.println("--- Integer Cache (-128 to 127) ---");

        Integer x1 = 100;  // inside the cache
        Integer x2 = 100;
        System.out.println("x1 = 100, x2 = 100  (inside the cache)");
        System.out.println("x1 == x2      : " + (x1 == x2));       // true — same object from the cache
        System.out.println("x1.equals(x2) : " + x1.equals(x2));    // true
        System.out.println();

        Integer y1 = 200;  // outside the cache
        Integer y2 = 200;
        System.out.println("y1 = 200, y2 = 200  (outside the cache)");
        System.out.println("y1 == y2      : " + (y1 == y2));       // false — different objects!
        System.out.println("y1.equals(y2) : " + y1.equals(y2));    // true — same content
        System.out.println("CONCLUSION: always use equals() to compare Integers!");
        System.out.println();

        // Exact cache boundaries
        Integer lowerBound1 = -128;
        Integer lowerBound2 = -128;
        Integer upperBound1 = 127;
        Integer upperBound2 = 127;
        System.out.println("Cache: -128 to 127");
        System.out.println("-128 == -128 (cache): " + (lowerBound1 == lowerBound2)); // true
        System.out.println(" 127 ==  127 (cache): " + (upperBound1 == upperBound2)); // true
        System.out.println();

        // -------------------------------------------------------
        // Useful Integer methods
        // -------------------------------------------------------
        System.out.println("--- Useful Integer methods ---");

        System.out.println("Integer.MAX_VALUE         : " + Integer.MAX_VALUE);
        System.out.println("Integer.MIN_VALUE         : " + Integer.MIN_VALUE);
        System.out.println("Integer.parseInt(\"255\")   : " + Integer.parseInt("255"));
        System.out.println("Integer.toBinaryString(10): " + Integer.toBinaryString(10));
        System.out.println("Integer.toHexString(255)  : " + Integer.toHexString(255));
        System.out.println("Integer.toOctalString(8)  : " + Integer.toOctalString(8));
        System.out.println("Integer.bitCount(7)       : " + Integer.bitCount(7));
        System.out.println("Integer.compare(5, 10)    : " + Integer.compare(5, 10));
        System.out.println("Integer.max(5, 10)        : " + Integer.max(5, 10));
        System.out.println("Integer.sum(5, 10)        : " + Integer.sum(5, 10));
        System.out.println();

        // -------------------------------------------------------
        // Representing absence of value: null
        // (primitives cannot be null)
        // -------------------------------------------------------
        System.out.println("--- null in a wrapper (impossible with a primitive) ---");

        Integer optionalValue = null; // represents "no value"
        if (optionalValue == null) {
            System.out.println("optionalValue is null — no value was assigned");
        }
        // int primitive = null; // COMPILE ERROR — primitives do not accept null
    }
}

package block1_base._04_generics._05_wildcard;

import java.util.ArrayList;
import java.util.List;

public class Exercise {

    // -------------------------------------------------------
    // Method with unbounded wildcard: accepts a list of any type
    // Can only read as Object; cannot add elements
    // -------------------------------------------------------
    static void printAll(List<?> list) {
        System.out.print("  [");
        for (int i = 0; i < list.size(); i++) {
            Object item = list.get(i); // reading as Object — the only guaranteed type
            System.out.print(item);
            if (i < list.size() - 1) System.out.print(", ");
        }
        System.out.println("]");
    }

    // Returns the size of any list
    static int size(List<?> list) {
        return list.size(); // operation that does not depend on the type
    }

    // Checks if a list contains null
    static boolean containsNull(List<?> list) {
        for (Object item : list) {
            if (item == null) return true;
        }
        return false;
    }

    // Counts non-null elements
    static int countNonNull(List<?> list) {
        int count = 0;
        for (Object item : list) {
            if (item != null) count++;
        }
        return count;
    }

    // Prints information about any list
    static void inspect(List<?> list) {
        System.out.println("  Size         : " + list.size());
        System.out.println("  Empty?       : " + list.isEmpty());
        System.out.println("  Has null?    : " + checkNull(list));
        System.out.println("  Non-null elements: " + countNonNull(list));
    }

    private static boolean checkNull(List<?> list) {
        return list.contains(null);
    }

    // -------------------------------------------------------
    // Difference between List<?> and List<Object>
    // -------------------------------------------------------
    static void acceptsListOfObject(List<Object> list) {
        System.out.println("  List of Object: " + list);
    }

    static void acceptsListOfAnyType(List<?> list) {
        System.out.println("  List of any type: " + list);
    }

    public static void main(String[] args) {

        System.out.println("=== Unbounded Wildcard: <?> ===");
        System.out.println();

        // -------------------------------------------------------
        // printAll accepts a list of any type
        // -------------------------------------------------------
        System.out.println("--- printAll(List<?>) accepts any list ---");

        List<String>  strings  = List.of("java", "generics", "wildcard");
        List<Integer> integers = List.of(1, 2, 3, 4, 5);
        List<Double>  doubles  = List.of(1.1, 2.2, 3.3);
        List<Boolean> booleans = List.of(true, false, true);

        System.out.print("  Strings  : ");
        printAll(strings);

        System.out.print("  Integers : ");
        printAll(integers);

        System.out.print("  Doubles  : ");
        printAll(doubles);

        System.out.print("  Booleans : ");
        printAll(booleans);
        System.out.println();

        // -------------------------------------------------------
        // Inspecting lists with methods that are independent of the type
        // -------------------------------------------------------
        System.out.println("--- Inspecting lists ---");

        System.out.println("  List of strings:");
        inspect(strings);

        List<Object> withNull = new ArrayList<>();
        withNull.add("one");
        withNull.add(null);
        withNull.add("three");
        System.out.println("  List with null:");
        inspect(withNull);
        System.out.println();

        // -------------------------------------------------------
        // Cannot add to List<?> (except null)
        // -------------------------------------------------------
        System.out.println("--- List<?> does not accept adding elements ---");
        System.out.println("  List<?> list = new ArrayList<>();");
        System.out.println("  // list.add(\"text\"); => COMPILE ERROR");
        System.out.println("  // list.add(42);      => COMPILE ERROR");
        System.out.println("  // list.add(null);    => the only exception (but rarely useful)");
        System.out.println("  The compiler does not know the real type — cannot guarantee safety.");
        System.out.println();

        // -------------------------------------------------------
        // Fundamental difference: List<?> vs List<Object>
        // -------------------------------------------------------
        System.out.println("--- List<?> vs List<Object> ---");

        List<Object> listOfObject = new ArrayList<>();
        listOfObject.add("text");
        listOfObject.add(42);
        listOfObject.add(true);

        // List<Object> only accepts List<Object> — does not accept List<String>
        acceptsListOfObject(listOfObject);
        // acceptsListOfObject(strings);  // ERROR — List<String> is not List<Object>

        // List<?> accepts any type of list
        acceptsListOfAnyType(listOfObject);
        acceptsListOfAnyType(strings);   // OK
        acceptsListOfAnyType(integers);  // OK
        acceptsListOfAnyType(doubles);   // OK
        System.out.println();

        // -------------------------------------------------------
        // Comparative summary of wildcards
        // -------------------------------------------------------
        System.out.println("--- Comparative summary ---");
        System.out.println("  List<?>              => any type; reading as Object; no adding");
        System.out.println("  List<? extends T>    => T or subclasses; reading as T; no adding");
        System.out.println("  List<? super T>      => T or superclasses; reading as Object; adding T");
        System.out.println("  List<T> / List<Str>  => exact type; fully typed reading and writing");
    }
}

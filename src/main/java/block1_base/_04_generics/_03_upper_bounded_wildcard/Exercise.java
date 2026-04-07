package block1_base._04_generics._03_upper_bounded_wildcard;

import java.util.ArrayList;
import java.util.List;

public class Exercise {

    // -------------------------------------------------------
    // Method with upper bounded wildcard: accepts Number and subclasses
    // Can READ as Number, but cannot add elements
    // -------------------------------------------------------
    static double sum(List<? extends Number> list) {
        double total = 0;
        for (Number n : list) { // reading as Number — always safe
            total += n.doubleValue();
        }
        return total;
    }

    // Finds the maximum value in a list of comparables
    static <T extends Comparable<T>> T maximum(List<? extends T> list) {
        if (list.isEmpty()) {
            throw new IllegalArgumentException("Empty list");
        }
        T max = list.get(0);
        for (T item : list) {
            if (item.compareTo(max) > 0) {
                max = item;
            }
        }
        return max;
    }

    // Prints all elements from any list of Number
    static void printNumbers(List<? extends Number> list) {
        System.out.print("  [");
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i));
            if (i < list.size() - 1) System.out.print(", ");
        }
        System.out.println("]  (type: " + list.getClass().getSimpleName() + ")");
    }

    // Copies elements from a source list to a destination list
    // source: produces T => extends
    // destination: consumes T => super
    static <T> void copy(List<? extends T> source, List<? super T> destination) {
        for (T item : source) {
            destination.add(item);
        }
    }

    public static void main(String[] args) {

        System.out.println("=== Upper Bounded Wildcard: ? extends T ===");
        System.out.println();

        // -------------------------------------------------------
        // Problem without wildcard: List<Integer> is not a subtype of List<Number>
        // -------------------------------------------------------
        System.out.println("--- Why do we need the wildcard? ---");

        List<Integer> listInt    = List.of(1, 2, 3, 4, 5);
        List<Double>  listDbl    = List.of(1.1, 2.2, 3.3);
        List<Long>    listLong   = List.of(100L, 200L, 300L);
        List<Number>  listNumber = List.of(1, 2.5, 3L);

        System.out.println("  // void sum(List<Number> list)");
        System.out.println("  // sum(listInt) => COMPILE ERROR!");
        System.out.println("  // List<Integer> is NOT a subtype of List<Number>.");
        System.out.println("  // Solution: void sum(List<? extends Number> list)");
        System.out.println();

        // -------------------------------------------------------
        // sum method accepts any List<? extends Number>
        // -------------------------------------------------------
        System.out.println("--- sum(List<? extends Number>) ---");

        System.out.print("  listInt    : ");
        printNumbers(listInt);
        System.out.println("  Sum: " + sum(listInt));
        System.out.println();

        System.out.print("  listDbl    : ");
        printNumbers(listDbl);
        System.out.println("  Sum: " + sum(listDbl));
        System.out.println();

        System.out.print("  listLong   : ");
        printNumbers(listLong);
        System.out.println("  Sum: " + sum(listLong));
        System.out.println();

        System.out.print("  listNumber : ");
        printNumbers(listNumber);
        System.out.println("  Sum: " + sum(listNumber));
        System.out.println();

        // -------------------------------------------------------
        // Demonstration: cannot add to List<? extends Number>
        // -------------------------------------------------------
        System.out.println("--- Reading YES, writing NO with ? extends ---");

        List<Integer> ints = new ArrayList<>(List.of(10, 20, 30));

        // We call a method with ? extends
        // Inside the method we cannot do list.add(something) — compiler would block it
        System.out.println("  Inside sum(), we read as Number. Adding is FORBIDDEN.");
        System.out.println("  // list.add(42) => COMPILE ERROR inside the method");
        System.out.println("  This guarantees the original list is not corrupted.");
        System.out.println();

        // -------------------------------------------------------
        // maximum method with <T extends Comparable<T>>
        // -------------------------------------------------------
        System.out.println("--- maximum() with upper bounded ---");

        List<Integer> grades = List.of(7, 9, 5, 8, 10, 6);
        List<String>  names  = List.of("Carlos", "Ana", "Bruno", "Zara");

        System.out.println("  Grades: " + grades);
        System.out.println("  Maximum grade: " + maximum(grades));
        System.out.println("  Names: " + names);
        System.out.println("  Maximum name (alpha): " + maximum(names));
        System.out.println();

        // -------------------------------------------------------
        // copy: PECS — Producer Extends, Consumer Super
        // -------------------------------------------------------
        System.out.println("--- copy: combines extends (source) and super (destination) ---");

        List<Integer> source      = List.of(1, 2, 3);
        List<Number>  destination = new ArrayList<>();

        copy(source, destination);
        System.out.println("  Source (List<Integer>): " + source);
        System.out.println("  Destination (List<Number>): " + destination);
    }
}

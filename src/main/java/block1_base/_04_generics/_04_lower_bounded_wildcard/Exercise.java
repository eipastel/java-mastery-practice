package block1_base._04_generics._04_lower_bounded_wildcard;

import java.util.ArrayList;
import java.util.List;

public class Exercise {

    // -------------------------------------------------------
    // Method with lower bounded wildcard: accepts Integer and superclasses
    // Can ADD Integer, but reading returns only Object
    // -------------------------------------------------------
    static void addNumbers(List<? super Integer> list, int count) {
        for (int i = 1; i <= count; i++) {
            list.add(i); // OK — Integer is compatible with ? super Integer
        }
    }

    // Fills a list with consecutive values starting from start
    static void fillSequence(List<? super Integer> list, int start, int end) {
        for (int i = start; i <= end; i++) {
            list.add(i);
        }
    }

    // -------------------------------------------------------
    // PECS demonstration: Producer Extends, Consumer Super
    // source produces => extends; destination consumes => super
    // -------------------------------------------------------
    static <T> void copy(List<? extends T> source, List<? super T> destination) {
        for (T item : source) {
            destination.add(item);
        }
    }

    // Method that accepts a list to add Strings to
    static void addStrings(List<? super String> list) {
        list.add("alpha");
        list.add("beta");
        list.add("gamma");
    }

    public static void main(String[] args) {

        System.out.println("=== Lower Bounded Wildcard: ? super T ===");
        System.out.println();

        // -------------------------------------------------------
        // Motivation: we want to add Integer to different types of list
        // -------------------------------------------------------
        System.out.println("--- addNumbers(List<? super Integer>) ---");
        System.out.println("  Accepts: List<Integer>, List<Number>, List<Object>");
        System.out.println();

        // Integer list
        List<Integer> intList = new ArrayList<>();
        addNumbers(intList, 3);
        System.out.println("  List<Integer>: " + intList);

        // Number list (superclass of Integer)
        List<Number> numList = new ArrayList<>();
        addNumbers(numList, 3);
        System.out.println("  List<Number>:  " + numList);

        // Object list (superclass of everything)
        List<Object> objList = new ArrayList<>();
        addNumbers(objList, 3);
        System.out.println("  List<Object>:  " + objList);
        System.out.println();

        // -------------------------------------------------------
        // Demonstration: reading with ? super returns only Object
        // -------------------------------------------------------
        System.out.println("--- Reading as Object YES, writing with known type YES ---");

        List<Number> numbers = new ArrayList<>();
        fillSequence(numbers, 10, 15);
        System.out.println("  Filled list: " + numbers);

        // Reading returns Object — we need a cast for a specific type
        // (the compiler does not know if it is List<Integer>, List<Number> or List<Object>)
        System.out.println("  Reading returns Object:");
        for (Object obj : numbers) {
            System.out.print("    " + obj + " (" + obj.getClass().getSimpleName() + ")");
            // If we know the real type, we can check and cast:
            if (obj instanceof Number n) {
                System.out.print(" => doubleValue=" + n.doubleValue());
            }
            System.out.println();
        }
        System.out.println();

        // -------------------------------------------------------
        // PECS in practice: copy elements between lists
        // -------------------------------------------------------
        System.out.println("--- PECS: copy(source extends, destination super) ---");

        List<Integer> source       = List.of(100, 200, 300);
        List<Number>  destination1 = new ArrayList<>();
        List<Object>  destination2 = new ArrayList<>();

        copy(source, destination1); // Integer -> Number
        copy(source, destination2); // Integer -> Object

        System.out.println("  Source (List<Integer>):      " + source);
        System.out.println("  Destination (List<Number>):  " + destination1);
        System.out.println("  Destination (List<Object>):  " + destination2);
        System.out.println();

        // -------------------------------------------------------
        // addStrings accepts List<String> and List<Object>
        // -------------------------------------------------------
        System.out.println("--- addStrings(List<? super String>) ---");

        List<String> strings = new ArrayList<>();
        addStrings(strings);
        System.out.println("  List<String>: " + strings);

        List<Object> objects = new ArrayList<>();
        addStrings(objects);
        System.out.println("  List<Object>: " + objects);
        System.out.println();

        // -------------------------------------------------------
        // PECS summary
        // -------------------------------------------------------
        System.out.println("--- PECS Summary ---");
        System.out.println("  Producer Extends: the list PROVIDES data to you    => ? extends T");
        System.out.println("  Consumer Super:   the list RECEIVES data from you  => ? super T");
        System.out.println("  Both reading and writing: use concrete type <T>");
    }
}

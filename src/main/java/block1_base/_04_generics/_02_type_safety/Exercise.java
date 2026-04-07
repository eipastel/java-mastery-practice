package block1_base._04_generics._02_type_safety;

import java.util.ArrayList;
import java.util.List;

public class Exercise {

    public static void main(String[] args) {

        System.out.println("=== Type Safety with Generics ===");
        System.out.println();

        // -------------------------------------------------------
        // WITH generics: type safety at compile time
        // -------------------------------------------------------
        System.out.println("--- WITH generics: List<String> ---");

        List<String> typedList = new ArrayList<>();
        typedList.add("Java");
        typedList.add("Generics");
        typedList.add("TypeSafety");

        // No cast needed — the compiler knows the type
        for (String s : typedList) {
            System.out.println("  " + s.toUpperCase()); // String method without cast
        }

        // The compiler prevents adding the wrong type:
        // typedList.add(42);  // COMPILE ERROR — detected immediately
        System.out.println("  Attempting add(42) would be a COMPILE ERROR — detected before running.");
        System.out.println();

        // -------------------------------------------------------
        // WITHOUT generics (raw type): no compile-time safety
        // -------------------------------------------------------
        System.out.println("--- WITHOUT generics: List (raw type) ---");

        @SuppressWarnings("rawtypes")
        List rawList = new ArrayList(); // raw type — compiler only emits a warning

        rawList.add("text");
        rawList.add(42);        // no compile error!
        rawList.add(3.14);      // no compile error!
        rawList.add(true);      // no compile error!

        System.out.println("  Raw list accepts any type without complaining.");
        System.out.println("  Contents: " + rawList);
        System.out.println();

        // -------------------------------------------------------
        // ClassCastException with raw type at runtime
        // -------------------------------------------------------
        System.out.println("--- ClassCastException with raw type (error at RUNTIME) ---");

        @SuppressWarnings({"rawtypes", "unchecked"})
        List rawMixed = new ArrayList();
        rawMixed.add("hello");
        rawMixed.add(100); // Integer instead of String

        try {
            // Iterating and treating everything as String — will blow up on Integer
            for (Object obj : rawMixed) {
                @SuppressWarnings("unchecked")
                String val = (String) obj; // explicit cast required with raw type
                System.out.println("  Value: " + val);
            }
        } catch (ClassCastException e) {
            System.out.println("  ClassCastException at RUNTIME: "
                    + "cannot convert Integer to String!");
            System.out.println("  This error only appears at execution — not at compile time.");
        }
        System.out.println();

        // -------------------------------------------------------
        // Mixing raw type with generic: unchecked warning
        // -------------------------------------------------------
        System.out.println("--- Mixing raw type with generic (unchecked warning) ---");

        @SuppressWarnings("rawtypes")
        List rawListSafe = new ArrayList();
        rawListSafe.add("safe");
        rawListSafe.add("also safe");

        @SuppressWarnings("unchecked")
        List<String> typed = rawListSafe; // unchecked warning from the compiler

        System.out.println("  Typed list from raw: " + typed);

        // If the raw had an Integer, the line below would fail at runtime
        rawListSafe.add(999); // adding Integer to raw after typed assignment
        try {
            for (String s : typed) { // ClassCastException when hitting the Integer
                System.out.println("  " + s);
            }
        } catch (ClassCastException e) {
            System.out.println("  ClassCastException: raw type corrupted the typed list!");
        }
        System.out.println();

        // -------------------------------------------------------
        // Summary: generics vs raw
        // -------------------------------------------------------
        System.out.println("--- Summary ---");
        System.out.println("  List (raw)       => no type checking => ClassCastException at runtime");
        System.out.println("  List<String>     => compiler checks  => error detected at compile time");
        System.out.println("  Always use type parameters in new code!");
    }
}

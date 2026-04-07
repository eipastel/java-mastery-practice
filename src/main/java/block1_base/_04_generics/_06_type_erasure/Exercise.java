package block1_base._04_generics._06_type_erasure;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Exercise {

    // -------------------------------------------------------
    // Type token: technique to preserve type information at runtime
    // Used by Jackson, Spring, Gson for generic deserialization
    // -------------------------------------------------------
    static abstract class TypeToken<T> {
        private final Type type;

        TypeToken() {
            // ParameterizedType captures the concrete generic type of the anonymous subclass
            Type superclass = getClass().getGenericSuperclass();
            if (superclass instanceof ParameterizedType pt) {
                this.type = pt.getActualTypeArguments()[0];
            } else {
                this.type = Object.class;
            }
        }

        Type getType() { return type; }

        @Override
        public String toString() {
            return "TypeToken<" + type.getTypeName() + ">";
        }
    }

    public static void main(String[] args) {

        System.out.println("=== Type Erasure ===");
        System.out.println();

        // -------------------------------------------------------
        // 1. getClass() returns the same type for different lists
        //    Proves that the generic type was erased at runtime
        // -------------------------------------------------------
        System.out.println("--- 1. getClass() returns the same type ---");

        List<String>  listStr = new ArrayList<>();
        List<Integer> listInt = new ArrayList<>();
        List<Double>  listDbl = new ArrayList<>();

        System.out.println("listStr.getClass().getName(): " + listStr.getClass().getName());
        System.out.println("listInt.getClass().getName(): " + listInt.getClass().getName());
        System.out.println("listDbl.getClass().getName(): " + listDbl.getClass().getName());
        System.out.println("listStr.getClass() == listInt.getClass(): "
                + (listStr.getClass() == listInt.getClass())); // true!
        System.out.println("At runtime, List<String> and List<Integer> are the same class.");
        System.out.println();

        // -------------------------------------------------------
        // 2. instanceof does not work with parameterized generic type
        // -------------------------------------------------------
        System.out.println("--- 2. instanceof does not work with generic type ---");

        // if (listStr instanceof List<String>) { } // COMPILE ERROR

        // Only without the type parameter works:
        System.out.println("listStr instanceof List    : " + (listStr instanceof List));   // OK
        System.out.println("listStr instanceof List<?> : " + (listStr instanceof List<?>)); // OK
        System.out.println("Not possible: instanceof List<String> — the compiler rejects it.");
        System.out.println();

        // -------------------------------------------------------
        // 3. The compiler inserts casts automatically (type erasure in action)
        //    Without generics, you would do this manually and risk errors
        // -------------------------------------------------------
        System.out.println("--- 3. Cast inserted automatically by the compiler ---");

        List<String> list = new ArrayList<>();
        list.add("first");
        list.add("second");

        // No cast needed in generic code:
        String s = list.get(0);
        System.out.println("list.get(0): " + s);
        System.out.println("The compiler generated: String s = (String) list.get(0); under the hood.");
        System.out.println("If there were a wrong type, ClassCastException would occur here.");
        System.out.println();

        // -------------------------------------------------------
        // 4. Consequence: cannot create an array of a generic type
        // -------------------------------------------------------
        System.out.println("--- 4. Cannot create array of a generic type ---");
        System.out.println("  // T[] array = new T[10]; => COMPILE ERROR");
        System.out.println("  // List<String>[] arr = new List<String>[5]; => ERROR");
        System.out.println("  Reason: at runtime T has already been erased — JVM does not know the type.");
        System.out.println("  Alternative: use List<List<String>> or Object[] with cast.");
        System.out.println();

        // -------------------------------------------------------
        // 5. Heap pollution: mixing raw and generic types
        //    Result of type erasure + raw types
        // -------------------------------------------------------
        System.out.println("--- 5. Heap pollution (raw type + generics) ---");

        @SuppressWarnings({"rawtypes", "unchecked"})
        List rawList = new ArrayList();
        rawList.add(42); // Integer added via raw type

        @SuppressWarnings("unchecked")
        List<String> typed = rawList; // unchecked warning — heap pollution!

        System.out.println("  Raw list with Integer, assigned to List<String>...");
        try {
            String value = typed.get(0); // ClassCastException here — cast was inserted by the compiler
            System.out.println("  Value: " + value);
        } catch (ClassCastException e) {
            System.out.println("  ClassCastException: heap pollution detected at runtime!");
            System.out.println("  The compiler inserted a (String) cast that failed.");
        }
        System.out.println();

        // -------------------------------------------------------
        // 6. TypeToken: preserving type information at runtime
        // -------------------------------------------------------
        System.out.println("--- 6. TypeToken: preserving type at runtime ---");

        // Anonymous subclass captures the type in bytecode as ParameterizedType
        TypeToken<List<String>>  tokenListStr = new TypeToken<List<String>>()  {};
        TypeToken<List<Integer>> tokenListInt = new TypeToken<List<Integer>>() {};

        System.out.println("tokenListStr.getType(): " + tokenListStr.getType().getTypeName());
        System.out.println("tokenListInt.getType(): " + tokenListInt.getType().getTypeName());
        System.out.println("Now they are different types at runtime!");
        System.out.println("Libraries like Jackson use this technique to deserialize JSON generically.");
        System.out.println();

        // -------------------------------------------------------
        // 7. Summary of what type erasure implies
        // -------------------------------------------------------
        System.out.println("--- Type Erasure Summary ---");
        System.out.println("  1. List<String> and List<Integer> are the same class at runtime");
        System.out.println("  2. instanceof List<String> does not compile");
        System.out.println("  3. Cannot create T[] (array of generic type)");
        System.out.println("  4. Raw types + generics cause heap pollution");
        System.out.println("  5. TypeToken preserves type via ParameterizedType (workaround)");
    }
}

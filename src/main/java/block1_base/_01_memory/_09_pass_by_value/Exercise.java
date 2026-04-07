package block1_base._01_memory._09_pass_by_value;

import java.util.ArrayList;
import java.util.List;

public class Exercise {

    // Helper class with mutable state
    static class Box {
        int value;

        Box(int value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "Box{value=" + value + "}";
        }
    }

    public static void main(String[] args) {

        System.out.println("=== Pass by Value in Java ===");
        System.out.println();

        // -------------------------------------------------------
        // CASE 1: primitives
        // The value is copied. Modifying inside the method does not affect the caller.
        // -------------------------------------------------------
        System.out.println("--- Case 1: primitives ---");

        int number = 5;
        System.out.println("Before: number = " + number);
        tryToDouble(number);
        System.out.println("After: number = " + number); // still 5
        System.out.println("The method received a COPY of the value — the original did not change.");
        System.out.println();

        // -------------------------------------------------------
        // CASE 2: reassigning a reference does not affect the caller
        // The method receives a copy of the reference.
        // Reassigning the copy does not affect the caller's variable.
        // -------------------------------------------------------
        System.out.println("--- Case 2: reassigning a reference does not affect the caller ---");

        Box box = new Box(10);
        System.out.println("Before: box = " + box);
        tryToReplaceBox(box);
        System.out.println("After: box = " + box); // still Box{value=10}
        System.out.println("The method only changed its own COPY of the reference — the caller does not see it.");
        System.out.println();

        // -------------------------------------------------------
        // CASE 3: mutating the object affects the caller
        // The method receives a copy of the reference, but it points to the SAME object.
        // Modifying the object's state via that reference is visible to the caller.
        // -------------------------------------------------------
        System.out.println("--- Case 3: mutating the object affects the caller ---");

        Box mutableBox = new Box(10);
        System.out.println("Before: mutableBox = " + mutableBox);
        tripleBoxValue(mutableBox);
        System.out.println("After: mutableBox = " + mutableBox); // Box{value=30}
        System.out.println("The method mutated the SAME object on the heap — the caller sees the change.");
        System.out.println();

        // -------------------------------------------------------
        // CASE 4: list — clear() mutates the object (visible), reassignment does not (invisible)
        // -------------------------------------------------------
        System.out.println("--- Case 4: List — clear() vs reassignment ---");

        List<String> list = new ArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");

        System.out.println("Before clearing: list = " + list);
        clearList(list);
        System.out.println("After clearList(): list = " + list); // empty
        System.out.println();

        list.add("X");
        list.add("Y");
        System.out.println("Before replacing: list = " + list);
        replaceList(list);
        System.out.println("After replaceList(): list = " + list); // still [X, Y]
        System.out.println("Reassigning the reference inside the method does not affect the caller.");
    }

    // Primitive: receives a copy — doubling only changes the local variable
    static void tryToDouble(int x) {
        x = x * 2;
        System.out.println("  Inside tryToDouble(): x = " + x);
    }

    // Reference: receives a copy — reassigning only changes the local copy
    static void tryToReplaceBox(Box b) {
        b = new Box(999); // only the local copy points to the new object
        System.out.println("  Inside tryToReplaceBox(): b = " + b);
    }

    // Reference: receives a copy — but mutates the object both point to
    static void tripleBoxValue(Box b) {
        b.value = b.value * 3; // mutates the object on the heap
        System.out.println("  Inside tripleBoxValue(): b = " + b);
    }

    // List: clear() mutates the object — caller sees it
    static void clearList(List<String> list) {
        list.clear(); // mutates the object
        System.out.println("  Inside clearList(): list = " + list);
    }

    // List: reassignment — caller does not see it
    static void replaceList(List<String> list) {
        list = new ArrayList<>(); // only the local copy changes
        list.add("Z");
        System.out.println("  Inside replaceList(): list = " + list);
    }
}

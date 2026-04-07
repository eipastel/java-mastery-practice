package block1_base._01_memory._02_reference_vs_object;

public class Exercise {

    // Helper class to represent an object with mutable state
    static class Dog {
        String name;
        int age;

        Dog(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return "Dog{name='" + name + "', age=" + age + "}";
        }
    }

    public static void main(String[] args) {

        System.out.println("=== Reference vs Object ===");
        System.out.println();

        // -------------------------------------------------------
        // An object is created on the heap.
        // 'a' is a REFERENCE that points to that object.
        // -------------------------------------------------------
        Dog a = new Dog("Rex", 3);
        System.out.println("--- One reference, one object ---");
        System.out.println("a = " + a);
        System.out.println();

        // -------------------------------------------------------
        // Two references to the SAME object.
        // 'b' does not create a new object — it just copies the address.
        // -------------------------------------------------------
        Dog b = a; // b points to the SAME object as a

        System.out.println("--- Two references, same object ---");
        System.out.println("a = " + a);
        System.out.println("b = " + b);
        System.out.println("a == b (same reference?): " + (a == b)); // true
        System.out.println();

        // -------------------------------------------------------
        // Modifying the object via 'b'.
        // Since 'a' and 'b' point to the same object,
        // the change is visible through any reference.
        // -------------------------------------------------------
        System.out.println("--- Modifying via 'b', observing via 'a' ---");
        b.name = "Bolt";    // mutates the object on the heap
        b.age = 5;

        System.out.println("After b.name = \"Bolt\" and b.age = 5:");
        System.out.println("a = " + a); // Dog{name='Bolt', age=5}
        System.out.println("b = " + b); // Dog{name='Bolt', age=5}
        System.out.println("a and b are still the same reference: " + (a == b));
        System.out.println();

        // -------------------------------------------------------
        // Reassigning 'b' to a NEW object.
        // Now 'b' points to another object; 'a' does not change.
        // -------------------------------------------------------
        System.out.println("--- Reassigning 'b' to a new object ---");
        b = new Dog("Toby", 2); // b now points to a different object

        System.out.println("After b = new Dog(\"Toby\", 2):");
        System.out.println("a = " + a); // still Bolt, age 5
        System.out.println("b = " + b); // Toby, age 2
        System.out.println("a == b (same reference?): " + (a == b)); // false
        System.out.println();

        // -------------------------------------------------------
        // Null reference: points to no object.
        // Trying to use a null reference causes NullPointerException.
        // -------------------------------------------------------
        System.out.println("--- Null reference ---");
        Dog c = null;
        System.out.println("c = " + c); // null
        System.out.println("c == null: " + (c == null));

        try {
            System.out.println(c.name); // NullPointerException!
        } catch (NullPointerException e) {
            System.out.println("NullPointerException when accessing c.name: " + e.getMessage());
        }
    }
}

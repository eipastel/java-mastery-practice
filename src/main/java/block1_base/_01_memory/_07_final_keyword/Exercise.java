package block1_base._01_memory._07_final_keyword;

import java.util.ArrayList;
import java.util.List;

public class Exercise {

    // -------------------------------------------------------
    // final on a static field: class constant
    // -------------------------------------------------------
    static final double PI = 3.14159265358979;
    static final int MAX_ATTEMPTS = 3;

    // -------------------------------------------------------
    // final on an instance field: must be assigned in the constructor
    // -------------------------------------------------------
    final String id;

    Exercise(String id) {
        this.id = id;
        // this.id = "other"; // ERROR — already assigned in the constructor
    }

    // -------------------------------------------------------
    // Base class with a final method (cannot be overridden)
    // -------------------------------------------------------
    static class Shape {
        String color;

        Shape(String color) {
            this.color = color;
        }

        // final method: subclasses cannot override
        final String describe() {
            return "Shape of color " + color;
        }

        // regular method: subclasses can override
        String type() {
            return "Generic shape";
        }
    }

    static class Circle extends Shape {
        double radius;

        Circle(String color, double radius) {
            super(color);
            this.radius = radius;
        }

        // describe() cannot be overridden — it is final in Shape
        // Uncommenting the line below would cause a compile error:
        // String describe() { return "Circle"; }

        @Override
        String type() {
            return "Circle with radius " + radius;
        }
    }

    // -------------------------------------------------------
    // final class: cannot be extended
    // -------------------------------------------------------
    static final class Password {
        private final String value;

        Password(String value) {
            this.value = value;
        }

        boolean validate(String attempt) {
            return value.equals(attempt);
        }
    }

    // Trying to extend a final class would cause a compile error:
    // static class WeakPassword extends Password { }  // ERROR

    public static void main(String[] args) {

        System.out.println("=== final keyword ===");
        System.out.println();

        // -------------------------------------------------------
        // 1. final on a local variable
        // -------------------------------------------------------
        System.out.println("--- 1. final on a local variable ---");

        final int size = 10;
        System.out.println("final int size = " + size);
        // size = 20; // COMPILE ERROR — cannot reassign

        // final with an object: the reference is final, not the object
        final List<String> names = new ArrayList<>();
        names.add("Ana");     // OK — mutating the object
        names.add("Bruno");   // OK
        // names = new ArrayList<>(); // ERROR — reassigning the final reference

        System.out.println("Final list (reference cannot change, object can): " + names);
        System.out.println();

        // -------------------------------------------------------
        // 2. final on a class constant
        // -------------------------------------------------------
        System.out.println("--- 2. final on a class constant ---");
        System.out.println("PI             = " + PI);
        System.out.println("MAX_ATTEMPTS   = " + MAX_ATTEMPTS);
        System.out.println();

        // -------------------------------------------------------
        // 3. final on an instance field
        // -------------------------------------------------------
        System.out.println("--- 3. final on an instance field ---");
        Exercise obj = new Exercise("ID-001");
        System.out.println("obj.id = " + obj.id);
        // obj.id = "other"; // ERROR — id is final
        System.out.println();

        // -------------------------------------------------------
        // 4. final on a method (cannot be overridden)
        // -------------------------------------------------------
        System.out.println("--- 4. final on a method ---");
        Shape shape = new Shape("blue");
        Circle circle = new Circle("red", 5.0);

        System.out.println("shape.describe()   = " + shape.describe());   // final method
        System.out.println("circle.describe()  = " + circle.describe()); // inherits the final
        System.out.println("shape.type()       = " + shape.type());
        System.out.println("circle.type()      = " + circle.type());      // overridden
        System.out.println();

        // -------------------------------------------------------
        // 5. final on a class (cannot be extended)
        // -------------------------------------------------------
        System.out.println("--- 5. final on a class ---");
        Password password = new Password("s3cr3t");
        System.out.println("password.validate(\"wrong\") = " + password.validate("wrong"));
        System.out.println("password.validate(\"s3cr3t\") = " + password.validate("s3cr3t"));
        System.out.println("The Password class is final — no subclass can be created.");
        System.out.println();

        // -------------------------------------------------------
        // 6. final on a method parameter
        // -------------------------------------------------------
        System.out.println("--- 6. final on a method parameter ---");
        print("immutable text in parameter");
    }

    // Final parameter: the compiler prevents reassignment inside the method
    static void print(final String message) {
        System.out.println("Message: " + message);
        // message = "other"; // ERROR — final parameter cannot be reassigned
    }
}

package block1_base._01_memory._03_equals_vs_operator;

import java.util.Objects;

public class Exercise {

    // Class with equals() based on content (name + code)
    static class Product {
        String name;
        int code;

        Product(String name, int code) {
            this.name = name;
            this.code = code;
        }

        // Without overriding equals(), == and equals() behave the same
        // (both compare references). Below is the correct version.
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Product)) return false;
            Product p = (Product) o;
            return code == p.code && Objects.equals(name, p.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, code);
        }

        @Override
        public String toString() {
            return "Product{name='" + name + "', code=" + code + "}";
        }
    }

    // Class WITHOUT overriding equals() — uses the Object default (compares references)
    static class ProductWithoutEquals {
        String name;
        int code;

        ProductWithoutEquals(String name, int code) {
            this.name = name;
            this.code = code;
        }
    }

    public static void main(String[] args) {

        System.out.println("=== == vs equals() ===");
        System.out.println();

        // -------------------------------------------------------
        // Strings with == vs equals()
        // -------------------------------------------------------
        System.out.println("--- Strings: new String() vs literal ---");

        String s1 = new String("hello"); // object on the heap, outside the pool
        String s2 = new String("hello"); // another object on the heap

        System.out.println("s1 = \"" + s1 + "\"  (new String)");
        System.out.println("s2 = \"" + s2 + "\"  (new String)");
        System.out.println("s1 == s2      : " + (s1 == s2));       // false — different references
        System.out.println("s1.equals(s2) : " + s1.equals(s2));    // true  — same content
        System.out.println();

        // -------------------------------------------------------
        // String literals may share a reference (String Pool)
        // but never rely on == to compare content
        // -------------------------------------------------------
        System.out.println("--- String literals (String Pool) ---");

        String lit1 = "world";
        String lit2 = "world";

        System.out.println("lit1 = \"world\"  (literal)");
        System.out.println("lit2 = \"world\"  (literal)");
        System.out.println("lit1 == lit2       : " + (lit1 == lit2));    // true — same object from the pool
        System.out.println("lit1.equals(lit2)  : " + lit1.equals(lit2)); // true — same content
        System.out.println("WARNING: do not rely on == for Strings, even if it returns true by coincidence.");
        System.out.println();

        // -------------------------------------------------------
        // == with primitives: compares value (correct)
        // -------------------------------------------------------
        System.out.println("--- Primitives: == compares value ---");
        int n1 = 42;
        int n2 = 42;
        System.out.println("n1 == n2 : " + (n1 == n2)); // true — correct for primitives
        System.out.println();

        // -------------------------------------------------------
        // Object WITH overridden equals()
        // -------------------------------------------------------
        System.out.println("--- Object WITH overridden equals() ---");

        Product p1 = new Product("Pen", 101);
        Product p2 = new Product("Pen", 101);
        Product p3 = p1; // same reference

        System.out.println("p1 = " + p1);
        System.out.println("p2 = " + p2 + " (new object, same content)");
        System.out.println("p3 = p1 (same reference)");
        System.out.println();
        System.out.println("p1 == p2         : " + (p1 == p2));        // false — different objects
        System.out.println("p1.equals(p2)    : " + p1.equals(p2));     // true  — same content
        System.out.println("p1 == p3         : " + (p1 == p3));        // true  — same reference
        System.out.println("p1.equals(p3)    : " + p1.equals(p3));     // true  — same reference and content
        System.out.println();

        // -------------------------------------------------------
        // Object WITHOUT overridden equals() — equals() == reference comparison
        // -------------------------------------------------------
        System.out.println("--- Object WITHOUT overridden equals() ---");

        ProductWithoutEquals ps1 = new ProductWithoutEquals("Pen", 101);
        ProductWithoutEquals ps2 = new ProductWithoutEquals("Pen", 101);

        System.out.println("ps1 == ps2      : " + (ps1 == ps2));    // false
        System.out.println("ps1.equals(ps2) : " + ps1.equals(ps2)); // false — default compares references
        System.out.println("Without overriding equals(), equals() behaves like ==.");
    }
}

package block1_base._01_memory._04_string_pool;

public class Exercise {

    public static void main(String[] args) {

        System.out.println("=== String Pool ===");
        System.out.println();

        // -------------------------------------------------------
        // String literals: share the same object in the pool
        // -------------------------------------------------------
        System.out.println("--- String Literals (String Pool) ---");

        String a = "java";
        String b = "java";

        System.out.println("a = \"java\"  (literal)");
        System.out.println("b = \"java\"  (literal)");
        System.out.println("a == b        : " + (a == b));       // true — same object in the pool
        System.out.println("a.equals(b)   : " + a.equals(b));   // true — same content
        System.out.println("Equal literals share the same object in the String Pool.");
        System.out.println();

        // -------------------------------------------------------
        // new String(): ALWAYS creates a new object outside the pool
        // -------------------------------------------------------
        System.out.println("--- new String() — outside the pool ---");

        String c = new String("java");

        System.out.println("c = new String(\"java\")");
        System.out.println("a == c        : " + (a == c));     // false — c is outside the pool
        System.out.println("a.equals(c)   : " + a.equals(c)); // true  — same content
        System.out.println("new String() always creates a new object on the heap, outside the pool.");
        System.out.println();

        // -------------------------------------------------------
        // intern(): moves a String to the pool (or returns the existing reference)
        // -------------------------------------------------------
        System.out.println("--- intern() — forcing use of the pool ---");

        String d = new String("java").intern(); // looks up or inserts in the pool

        System.out.println("d = new String(\"java\").intern()");
        System.out.println("a == d        : " + (a == d));     // true — now d points to the pool
        System.out.println("a.equals(d)   : " + a.equals(d)); // true
        System.out.println("intern() returns the pool reference for the same character sequence.");
        System.out.println();

        // -------------------------------------------------------
        // Compile-time concatenation: optimized to a literal
        // -------------------------------------------------------
        System.out.println("--- Compile-time concatenation ---");

        String e = "ja" + "va"; // compiler resolves this as "java" at compile time
        System.out.println("e = \"ja\" + \"va\"  (compile-time constant)");
        System.out.println("a == e        : " + (a == e));     // true — compiler optimization
        System.out.println("The compiler resolves \"ja\" + \"va\" as the literal \"java\" at compile time.");
        System.out.println();

        // -------------------------------------------------------
        // Runtime concatenation: creates a new object
        // -------------------------------------------------------
        System.out.println("--- Runtime concatenation ---");

        String part = "ja";
        String f = part + "va"; // variable: computed at runtime, does not use the pool directly

        System.out.println("part = \"ja\"");
        System.out.println("f = part + \"va\"  (runtime concatenation)");
        System.out.println("a == f        : " + (a == f));     // false — new object created at runtime
        System.out.println("a.equals(f)   : " + a.equals(f)); // true  — same content
        System.out.println("Concatenation with variables at runtime creates a new object outside the pool.");
        System.out.println();

        // -------------------------------------------------------
        // Visual summary
        // -------------------------------------------------------
        System.out.println("--- Summary ---");
        System.out.println("String literal       => uses the pool (shared)");
        System.out.println("new String()         => outside the pool (exclusive object)");
        System.out.println("literal.intern()     => looks up / inserts in the pool");
        System.out.println("Always use equals() to compare String content!");
    }
}

package block1_base._03_exceptions._02_throw_vs_throws;

import java.io.IOException;

public class Exercise {

    // -------------------------------------------------------
    // throws in the signature: declares that the method may propagate
    // The responsibility to handle is passed to the caller
    // -------------------------------------------------------
    static String readContent(String path) throws IOException {
        if (path == null || path.isEmpty()) {
            // throw inside the body: concretely throws the exception
            throw new IOException("File path cannot be null or empty");
        }
        if (path.equals("nonexistent.txt")) {
            throw new IOException("File not found: " + path);
        }
        return "simulated content of " + path;
    }

    // -------------------------------------------------------
    // throws with multiple exceptions
    // -------------------------------------------------------
    static void processData(String input) throws IllegalArgumentException, ArithmeticException {
        if (input == null) {
            throw new IllegalArgumentException("Input cannot be null");
        }
        if (input.equals("0")) {
            throw new ArithmeticException("Division by zero detected");
        }
        int value = Integer.parseInt(input); // may throw NumberFormatException (unchecked)
        System.out.println("  Processed value: " + (100 / value));
    }

    // -------------------------------------------------------
    // Method that catches and re-throws with more context
    // -------------------------------------------------------
    static void executePipeline(String path) throws RuntimeException {
        try {
            String content = readContent(path);
            System.out.println("  Content: " + content);
        } catch (IOException e) {
            // Catches the checked IOException and converts to unchecked
            // Preserves the original cause in the constructor
            throw new RuntimeException("Pipeline failed to read: " + path, e);
        }
    }

    // -------------------------------------------------------
    // throw in a conditional block
    // -------------------------------------------------------
    static void validateAge(int age) {
        if (age < 0) {
            throw new IllegalArgumentException("Invalid negative age: " + age);
        }
        if (age > 150) {
            throw new IllegalArgumentException("Implausible age: " + age);
        }
        System.out.println("  Valid age: " + age);
    }

    public static void main(String[] args) {

        System.out.println("=== throw vs throws ===");
        System.out.println();

        // -------------------------------------------------------
        // throws: the caller is required to handle (checked)
        // -------------------------------------------------------
        System.out.println("--- throws: declaration in the signature ---");
        System.out.println("The compiler requires the caller to handle or re-declare throws.");
        System.out.println();

        // Success
        try {
            String result = readContent("data.txt");
            System.out.println("Success: " + result);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // Failure: invalid path
        try {
            String result = readContent("");
            System.out.println("Result: " + result);
        } catch (IOException e) {
            System.out.println("IOException caught: " + e.getMessage());
        }

        // Failure: file not found
        try {
            String result = readContent("nonexistent.txt");
            System.out.println("Result: " + result);
        } catch (IOException e) {
            System.out.println("IOException caught: " + e.getMessage());
        }
        System.out.println();

        // -------------------------------------------------------
        // throw: concrete throwing inside the method
        // -------------------------------------------------------
        System.out.println("--- throw: validation with IllegalArgumentException ---");

        validateAge(25);

        try {
            validateAge(-5);
        } catch (IllegalArgumentException e) {
            System.out.println("  Caught: " + e.getMessage());
        }

        try {
            validateAge(200);
        } catch (IllegalArgumentException e) {
            System.out.println("  Caught: " + e.getMessage());
        }
        System.out.println();

        // -------------------------------------------------------
        // throws with multiple exceptions
        // -------------------------------------------------------
        System.out.println("--- throws with multiple exceptions ---");

        String[] inputs = {"10", null, "0", "abc"};
        for (String input : inputs) {
            try {
                processData(input);
            } catch (IllegalArgumentException e) {
                System.out.println("  IllegalArgumentException: " + e.getMessage());
            } catch (ArithmeticException e) {
                System.out.println("  ArithmeticException: " + e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("  NumberFormatException: not a valid number: " + input);
            }
        }
        System.out.println();

        // -------------------------------------------------------
        // Re-throw: converts checked to unchecked
        // -------------------------------------------------------
        System.out.println("--- Re-throw: IOException -> RuntimeException ---");

        try {
            executePipeline("data.txt"); // success
        } catch (RuntimeException e) {
            System.out.println("  RuntimeException: " + e.getMessage());
        }

        try {
            executePipeline("nonexistent.txt"); // failure
        } catch (RuntimeException e) {
            System.out.println("  RuntimeException: " + e.getMessage());
            System.out.println("  Original cause: " + e.getCause().getMessage());
        }
    }
}

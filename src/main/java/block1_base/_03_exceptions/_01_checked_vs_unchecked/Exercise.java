package block1_base._03_exceptions._01_checked_vs_unchecked;

import java.io.IOException;
import java.io.StringReader;

public class Exercise {

    // -------------------------------------------------------
    // Simulated checked exception: extends Exception
    // The compiler requires try-catch or throws in the signature
    // -------------------------------------------------------
    static class FileNotFoundException extends Exception {
        FileNotFoundException(String path) {
            super("File not found: " + path);
        }
    }

    // Method that throws a checked exception: compiler REQUIRES handling
    static String readFile(String path) throws FileNotFoundException {
        if (!path.endsWith(".txt")) {
            throw new FileNotFoundException(path);
        }
        return "file content";
    }

    // -------------------------------------------------------
    // Unchecked exception: extends RuntimeException
    // The compiler does NOT require handling
    // -------------------------------------------------------
    static int divide(int a, int b) {
        // ArithmeticException is unchecked — we do not need to declare throws
        if (b == 0) {
            throw new ArithmeticException("Division by zero: b cannot be 0");
        }
        return a / b;
    }

    static String firstChar(String text) {
        // NullPointerException is unchecked — we do not need to declare throws
        return String.valueOf(text.charAt(0));
    }

    public static void main(String[] args) {

        System.out.println("=== Checked vs Unchecked Exceptions ===");
        System.out.println();

        // -------------------------------------------------------
        // CHECKED: compiler requires handling
        // Without try-catch or throws, the code does not compile
        // -------------------------------------------------------
        System.out.println("--- Checked Exception (FileNotFoundException) ---");
        System.out.println("The compiler REQUIRES you to handle or declare the exception.");
        System.out.println();

        // Success case
        try {
            String content = readFile("data.txt");
            System.out.println("File read successfully: " + content);
        } catch (FileNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // Failure case
        try {
            String content = readFile("data.pdf"); // wrong extension
            System.out.println("File read: " + content);
        } catch (FileNotFoundException e) {
            System.out.println("Error (caught): " + e.getMessage());
        }
        System.out.println();

        // -------------------------------------------------------
        // Checked from JDK: IOException
        // -------------------------------------------------------
        System.out.println("--- JDK Checked: IOException ---");

        try {
            StringReader reader = new StringReader("test");
            reader.close();
            reader.read(); // IOException after closing
        } catch (IOException e) {
            System.out.println("IOException caught: Stream closed");
        }
        System.out.println();

        // -------------------------------------------------------
        // UNCHECKED: NullPointerException
        // Compiler does not warn — blows up at runtime
        // -------------------------------------------------------
        System.out.println("--- Unchecked: NullPointerException ---");
        System.out.println("Compiler does not require handling — programming error.");

        String s = null;
        try {
            int len = s.length(); // NullPointerException!
            System.out.println("Length: " + len);
        } catch (NullPointerException e) {
            System.out.println("NullPointerException: attempted to use a null reference");
        }
        System.out.println();

        // -------------------------------------------------------
        // UNCHECKED: ArithmeticException
        // -------------------------------------------------------
        System.out.println("--- Unchecked: ArithmeticException ---");

        System.out.println("divide(10, 2) = " + divide(10, 2));

        try {
            int result = divide(10, 0);
            System.out.println("Result: " + result);
        } catch (ArithmeticException e) {
            System.out.println("ArithmeticException: " + e.getMessage());
        }
        System.out.println();

        // -------------------------------------------------------
        // UNCHECKED: ArrayIndexOutOfBoundsException
        // -------------------------------------------------------
        System.out.println("--- Unchecked: ArrayIndexOutOfBoundsException ---");

        int[] array = {1, 2, 3};
        try {
            int value = array[10]; // invalid index
            System.out.println("Value: " + value);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("ArrayIndexOutOfBoundsException: index 10 invalid for array of size 3");
        }
        System.out.println();

        // -------------------------------------------------------
        // UNCHECKED: ClassCastException
        // -------------------------------------------------------
        System.out.println("--- Unchecked: ClassCastException ---");

        Object obj = "text";
        try {
            Integer num = (Integer) obj; // invalid cast
        } catch (ClassCastException e) {
            System.out.println("ClassCastException: cannot convert String to Integer");
        }

        System.out.println();
        System.out.println("--- Summary ---");
        System.out.println("Checked   => compiler requires handling => predictable external failures");
        System.out.println("Unchecked => compiler does not require   => programming errors");
    }
}

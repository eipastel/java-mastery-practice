package block1_base._03_exceptions._06_best_practices;

import java.io.IOException;

public class Exercise {

    // -------------------------------------------------------
    // Custom AutoCloseable for try-with-resources
    // -------------------------------------------------------
    static class DBConnection implements AutoCloseable {
        String name;
        boolean open;

        DBConnection(String name) throws IOException {
            if (name.equals("fail")) {
                throw new IOException("Could not connect to database: " + name);
            }
            this.name = name;
            this.open = true;
            System.out.println("  [DBConnection] Connection opened: " + name);
        }

        String query(String sql) {
            if (!open) {
                throw new IllegalStateException("Connection is already closed");
            }
            return "result of: " + sql;
        }

        @Override
        public void close() {
            open = false;
            System.out.println("  [DBConnection] Connection automatically closed: " + name);
        }
    }

    // Second resource to show multiple resources in try-with-resources
    static class FileReader implements AutoCloseable {
        String file;

        FileReader(String file) {
            this.file = file;
            System.out.println("  [FileReader] Reader opened: " + file);
        }

        String read() {
            return "content of " + file;
        }

        @Override
        public void close() {
            System.out.println("  [FileReader] Reader automatically closed: " + file);
        }
    }

    // -------------------------------------------------------
    // Exceptions for multi-catch
    // -------------------------------------------------------
    static class BusinessError extends RuntimeException {
        BusinessError(String msg) { super(msg); }
    }

    static class ValidationError extends RuntimeException {
        ValidationError(String msg) { super(msg); }
    }

    static void complexOperation(int option) throws IOException {
        switch (option) {
            case 1 -> throw new BusinessError("Business rule violated");
            case 2 -> throw new ValidationError("Invalid data provided");
            case 3 -> throw new IOException("Input/output failure");
        }
        System.out.println("  Operation " + option + " completed successfully");
    }

    public static void main(String[] args) {

        System.out.println("=== Exception Best Practices ===");
        System.out.println();

        // -------------------------------------------------------
        // 1. try-with-resources: resource closed automatically
        // -------------------------------------------------------
        System.out.println("--- 1. try-with-resources ---");

        // Success case: connection closed automatically when leaving the block
        try (DBConnection conn = new DBConnection("production")) {
            String result = conn.query("SELECT * FROM users");
            System.out.println("  Result: " + result);
        } catch (IOException e) {
            System.out.println("  Connection error: " + e.getMessage());
        }
        System.out.println();

        // Multiple resources: closed in reverse order of declaration
        System.out.println("  Multiple resources (reverse closing order):");
        try (DBConnection conn      = new DBConnection("analytics");
             FileReader reader      = new FileReader("config.txt")) {
            System.out.println("  DB: " + conn.query("SELECT count(*) FROM logs"));
            System.out.println("  File: " + reader.read());
        } catch (IOException e) {
            System.out.println("  Error: " + e.getMessage());
        }
        System.out.println();

        // Exception during resource opening
        System.out.println("  Exception while opening connection:");
        try (DBConnection conn = new DBConnection("fail")) {
            System.out.println("  Never reaches here");
        } catch (IOException e) {
            System.out.println("  IOException: " + e.getMessage());
        }
        System.out.println();

        // -------------------------------------------------------
        // 2. Specific catch before general
        // -------------------------------------------------------
        System.out.println("--- 2. Specific catch before general ---");

        for (int i = 0; i <= 4; i++) {
            try {
                complexOperation(i);
            } catch (BusinessError e) {
                System.out.println("  [business] " + e.getMessage());
            } catch (ValidationError e) {
                System.out.println("  [validation] " + e.getMessage());
            } catch (IOException e) {
                System.out.println("  [io] " + e.getMessage());
            }
            // Unexpected RuntimeException propagates naturally — no swallowing
        }
        System.out.println();

        // -------------------------------------------------------
        // 3. Multi-catch: same handling for different types
        // -------------------------------------------------------
        System.out.println("--- 3. Multi-catch (Java 7+) ---");

        for (int i = 1; i <= 2; i++) {
            try {
                complexOperation(i);
            } catch (BusinessError | ValidationError e) {
                // both handled the same way
                System.out.println("  [multi-catch] " + e.getClass().getSimpleName()
                        + ": " + e.getMessage());
            } catch (IOException e) {
                System.out.println("  [io] " + e.getMessage());
            }
        }
        System.out.println();

        // -------------------------------------------------------
        // 4. Preserve cause when re-throwing
        // -------------------------------------------------------
        System.out.println("--- 4. Preserve cause when re-throwing ---");

        try {
            try {
                int[] arr = new int[-1]; // NegativeArraySizeException
            } catch (NegativeArraySizeException e) {
                // CORRECT: preserves the original cause
                throw new RuntimeException("Invalid size when creating buffer", e);
            }
        } catch (RuntimeException e) {
            System.out.println("  RuntimeException: " + e.getMessage());
            System.out.println("  Preserved cause: " + e.getCause().getClass().getSimpleName());
        }
        System.out.println();

        // -------------------------------------------------------
        // 5. Do not use exceptions for control flow
        // -------------------------------------------------------
        System.out.println("--- 5. Avoid exceptions as control flow ---");

        String input = "not_a_number";

        // BAD: exception as control flow
        int badValue;
        try {
            badValue = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            badValue = 0; // "normal" flow via exception — avoidable
        }
        System.out.println("  [bad]  Using exception as if: " + badValue);

        // GOOD: validate before parsing
        int goodValue = input.matches("-?\\d+") ? Integer.parseInt(input) : 0;
        System.out.println("  [good] Validating first: " + goodValue);
    }
}

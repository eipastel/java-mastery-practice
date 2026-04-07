package block1_base._03_exceptions._03_when_to_catch;

public class Exercise {

    // -------------------------------------------------------
    // Simulates integer parsing
    // -------------------------------------------------------
    static int safeParseInt(String input, int defaultValue) {
        // GOOD: catches to provide an alternative value
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("  [recovery] Invalid input '" + input
                    + "'. Using default: " + defaultValue);
            return defaultValue;
        }
    }

    // -------------------------------------------------------
    // Domain exception to demonstrate conversion
    // -------------------------------------------------------
    static class ConfigurationException extends RuntimeException {
        ConfigurationException(String msg, Throwable cause) {
            super(msg, cause);
        }
    }

    // Simulates reading a configuration
    static int readPort(String value) {
        // GOOD: catches and converts to domain exception with context
        try {
            int port = Integer.parseInt(value);
            if (port < 1 || port > 65535) {
                throw new IllegalArgumentException("Port out of range: " + port);
            }
            return port;
        } catch (NumberFormatException e) {
            throw new ConfigurationException("Invalid port value: '" + value + "'", e);
        }
    }

    // -------------------------------------------------------
    // BAD: swallow — silently ignores the exception
    // Never do this in real code!
    // -------------------------------------------------------
    static int silentParse(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            // ERROR: exception silently ignored
            // The caller receives 0 without knowing there was a failure
            // Bugs like this are extremely hard to trace
            return 0;
        }
    }

    // -------------------------------------------------------
    // BAD: unnecessarily catching generic Exception
    // -------------------------------------------------------
    static void genericOperation(String input) {
        try {
            int value = Integer.parseInt(input);
            int[] array = new int[value];
            array[0] = 10;
            System.out.println("  Success: array[0] = " + array[0]);
        } catch (Exception e) {
            // BAD: catches everything, including NegativeArraySizeException, NPE, etc.
            // We do not know what actually happened
            System.out.println("  [generic] Something failed: " + e.getClass().getSimpleName()
                    + " - " + e.getMessage());
        }
    }

    // -------------------------------------------------------
    // GOOD: catching specific types
    // -------------------------------------------------------
    static void specificOperation(String input) {
        try {
            int value = Integer.parseInt(input);
            int[] array = new int[value];
            array[0] = 10;
            System.out.println("  Success: array[0] = " + array[0]);
        } catch (NumberFormatException e) {
            System.out.println("  [specific] Input is not a number: '" + input + "'");
        } catch (NegativeArraySizeException e) {
            System.out.println("  [specific] Negative array size: " + input);
        }
        // Other unexpected errors propagate naturally (no swallowing)
    }

    public static void main(String[] args) {

        System.out.println("=== When to catch an exception? ===");
        System.out.println();

        // -------------------------------------------------------
        // GOOD: catches to recover with default value
        // -------------------------------------------------------
        System.out.println("--- GOOD: catch to recover ---");

        int a = safeParseInt("42", 0);
        int b = safeParseInt("abc", 0);
        int c = safeParseInt(null, -1);

        System.out.println("  safeParseInt(\"42\")  = " + a);
        System.out.println("  safeParseInt(\"abc\") = " + b);
        System.out.println("  safeParseInt(null)  = " + c);
        System.out.println();

        // -------------------------------------------------------
        // GOOD: catches to convert to domain exception
        // -------------------------------------------------------
        System.out.println("--- GOOD: catch to convert with context ---");

        String[] ports = {"8080", "abc", "99999"};
        for (String p : ports) {
            try {
                int port = readPort(p);
                System.out.println("  Valid port: " + port);
            } catch (ConfigurationException e) {
                System.out.println("  ConfigurationException: " + e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("  IllegalArgumentException: " + e.getMessage());
            }
        }
        System.out.println();

        // -------------------------------------------------------
        // BAD: swallow — never do this
        // -------------------------------------------------------
        System.out.println("--- BAD: swallow (silenced exception) ---");

        int result = silentParse("not_a_number");
        System.out.println("  silentParse(\"not_a_number\") = " + result);
        System.out.println("  Looks like it worked, but it actually failed silently!");
        System.out.println("  The caller received 0 and does not know there was an error.");
        System.out.println();

        // -------------------------------------------------------
        // BAD vs GOOD: generic vs specific catch
        // -------------------------------------------------------
        System.out.println("--- BAD: generic Exception catch ---");
        genericOperation("5");
        genericOperation("abc");
        genericOperation("-3");

        System.out.println();
        System.out.println("--- GOOD: catch by specific type ---");
        specificOperation("5");
        specificOperation("abc");
        specificOperation("-3");
    }
}

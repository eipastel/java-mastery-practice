package block1_base._03_exceptions._04_when_to_propagate;

import java.io.IOException;

public class Exercise {

    // -------------------------------------------------------
    // Domain exceptions
    // -------------------------------------------------------
    static class RepositoryException extends RuntimeException {
        RepositoryException(String msg, Throwable cause) {
            super(msg, cause);
        }
    }

    static class ServiceException extends RuntimeException {
        ServiceException(String msg, Throwable cause) {
            super(msg, cause);
        }
    }

    // -------------------------------------------------------
    // Infrastructure layer: accesses data
    // Does not know what to do with the failure — propagates
    // -------------------------------------------------------
    static class FileRepository {

        // Simulates file reading — throws IOException (checked)
        String read(String path) throws IOException {
            if (path.equals("corrupted.dat")) {
                throw new IOException("File is corrupted: " + path);
            }
            if (path.equals("nonexistent.dat")) {
                throw new IOException("File not found: " + path);
            }
            return "data from file " + path;
        }
    }

    // -------------------------------------------------------
    // Service layer: coordinates business logic
    // Converts the infrastructure exception to a domain exception
    // -------------------------------------------------------
    static class ReportService {

        private final FileRepository repository = new FileRepository();

        // Converts IOException to RepositoryException
        // The service does not know how to present the error to the user — propagates
        String generateReport(String file) {
            try {
                String data = repository.read(file); // may throw IOException
                return "REPORT: " + data.toUpperCase();
            } catch (IOException e) {
                // Converts the technical exception to a domain exception
                // Preserves the original cause
                throw new RepositoryException("Failed to read data for report: " + file, e);
            }
        }
    }

    // -------------------------------------------------------
    // Application layer (controller): knows how to respond to the user
    // This is the layer that CATCHES and definitively handles
    // -------------------------------------------------------
    static class ReportController {

        private final ReportService service = new ReportService();

        void displayReport(String file) {
            try {
                String report = service.generateReport(file);
                System.out.println("  Success: " + report);
            } catch (RepositoryException e) {
                // Here we know what to do: inform the user
                System.out.println("  [UI] Error generating report: " + e.getMessage());
                System.out.println("  [UI] Technical cause: " + e.getCause().getMessage());
                System.out.println("  [UI] Please try again later.");
            }
        }
    }

    // -------------------------------------------------------
    // Demonstration of propagation without conversion
    // -------------------------------------------------------
    static void level3() throws IOException {
        throw new IOException("Error at origin (level 3)");
    }

    static void level2() throws IOException {
        // Does not know what to do — propagates directly
        level3();
    }

    static void level1() {
        // This level knows how to handle — catches definitively
        try {
            level2();
        } catch (IOException e) {
            System.out.println("  [level1] Handling: " + e.getMessage());
        }
    }

    public static void main(String[] args) {

        System.out.println("=== When to propagate an exception? ===");
        System.out.println();

        // -------------------------------------------------------
        // Scenario 1: chain of layers
        // Repository -> Service -> Controller
        // -------------------------------------------------------
        System.out.println("--- Layer chain: propagation and conversion ---");

        ReportController controller = new ReportController();

        System.out.println("Report from valid file:");
        controller.displayReport("sales.dat");

        System.out.println("Report from corrupted file:");
        controller.displayReport("corrupted.dat");

        System.out.println("Report from nonexistent file:");
        controller.displayReport("nonexistent.dat");
        System.out.println();

        // -------------------------------------------------------
        // Scenario 2: direct propagation across layers
        // -------------------------------------------------------
        System.out.println("--- Direct propagation (level3 -> level2 -> level1) ---");
        System.out.println("level3 throws IOException, level2 propagates, level1 handles.");
        level1();
        System.out.println();

        // -------------------------------------------------------
        // Demonstrated principle
        // -------------------------------------------------------
        System.out.println("--- Propagation principle ---");
        System.out.println("Repository  : throws IOException (cannot handle it)");
        System.out.println("Service     : converts to RepositoryException (translates, does not handle)");
        System.out.println("Controller  : catches and responds to the user (knows what to do)");
        System.out.println();
        System.out.println("Each layer delegates what is not its responsibility to handle.");
    }
}

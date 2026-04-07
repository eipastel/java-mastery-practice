package block1_base._03_exceptions._05_custom_exceptions;

import java.util.HashMap;
import java.util.Map;

public class Exercise {

    // -------------------------------------------------------
    // Domain exception hierarchy
    //
    // AppException (base)
    //   ├── UserNotFoundException
    //   └── InsufficientBalanceException
    // -------------------------------------------------------

    // Base domain exception — unchecked (RuntimeException)
    static class AppException extends RuntimeException {
        AppException(String message) {
            super(message);
        }
        AppException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    // Specific exception: user does not exist
    // Carries the failed ID to facilitate logging and debugging
    static class UserNotFoundException extends AppException {
        private final int userId;

        UserNotFoundException(int userId) {
            super("User not found with ID: " + userId);
            this.userId = userId;
        }

        int getUserId() { return userId; }
    }

    // Specific exception: insufficient balance for a financial operation
    // Carries the current balance and the requested amount
    static class InsufficientBalanceException extends AppException {
        private final double currentBalance;
        private final double requestedAmount;

        InsufficientBalanceException(double currentBalance, double requestedAmount) {
            super(String.format(
                "Insufficient balance. Current balance: $%.2f, Requested amount: $%.2f",
                currentBalance, requestedAmount));
            this.currentBalance = currentBalance;
            this.requestedAmount = requestedAmount;
        }

        double getCurrentBalance()   { return currentBalance; }
        double getRequestedAmount()  { return requestedAmount; }
    }

    // -------------------------------------------------------
    // Domain entities
    // -------------------------------------------------------
    static class User {
        int id;
        String name;
        double balance;

        User(int id, String name, double balance) {
            this.id = id;
            this.name = name;
            this.balance = balance;
        }
    }

    // -------------------------------------------------------
    // Service that uses the custom exceptions
    // -------------------------------------------------------
    static class BankService {

        private final Map<Integer, User> users = new HashMap<>();

        BankService() {
            users.put(1, new User(1, "Ana Silva",   500.0));
            users.put(2, new User(2, "Bruno Costa", 150.0));
        }

        User findUser(int id) {
            User u = users.get(id);
            if (u == null) {
                throw new UserNotFoundException(id); // exception with domain information
            }
            return u;
        }

        void transfer(int fromId, int toId, double amount) {
            // May throw UserNotFoundException for invalid ids
            User from = findUser(fromId);
            User to   = findUser(toId);

            if (from.balance < amount) {
                throw new InsufficientBalanceException(from.balance, amount); // exception with context
            }

            from.balance -= amount;
            to.balance   += amount;
            System.out.println("  Transfer completed: $" + amount
                    + " from " + from.name + " to " + to.name);
        }

        void displayBalance(int id) {
            User u = findUser(id);
            System.out.printf("  Balance of %s: $%.2f%n", u.name, u.balance);
        }
    }

    public static void main(String[] args) {

        System.out.println("=== Custom Exceptions ===");
        System.out.println();

        BankService bank = new BankService();

        // -------------------------------------------------------
        // Scenario 1: successful operation
        // -------------------------------------------------------
        System.out.println("--- Successful transfer ---");
        try {
            bank.displayBalance(1);
            bank.displayBalance(2);
            bank.transfer(1, 2, 100.0);
            bank.displayBalance(1);
            bank.displayBalance(2);
        } catch (AppException e) {
            System.out.println("  Error: " + e.getMessage());
        }
        System.out.println();

        // -------------------------------------------------------
        // Scenario 2: InsufficientBalanceException
        // -------------------------------------------------------
        System.out.println("--- Insufficient balance ---");
        try {
            bank.transfer(2, 1, 500.0); // Bruno now has only $250
        } catch (InsufficientBalanceException e) {
            System.out.println("  InsufficientBalanceException: " + e.getMessage());
            System.out.printf("  Current balance: $%.2f%n", e.getCurrentBalance());
            System.out.printf("  Requested amount: $%.2f%n", e.getRequestedAmount());
        }
        System.out.println();

        // -------------------------------------------------------
        // Scenario 3: UserNotFoundException
        // -------------------------------------------------------
        System.out.println("--- User not found ---");
        try {
            bank.findUser(999);
        } catch (UserNotFoundException e) {
            System.out.println("  UserNotFoundException: " + e.getMessage());
            System.out.println("  Failed ID: " + e.getUserId());
        }
        System.out.println();

        // -------------------------------------------------------
        // Scenario 4: handling by base exception
        // -------------------------------------------------------
        System.out.println("--- Handling by base exception (AppException) ---");
        try {
            bank.transfer(1, 42, 10.0); // id 42 does not exist
        } catch (AppException e) {
            // Catches any domain exception
            System.out.println("  AppException: " + e.getMessage());
            System.out.println("  Actual type: " + e.getClass().getSimpleName());
        }
    }
}

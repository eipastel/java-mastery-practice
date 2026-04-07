# Custom Exceptions

## Why create your own exceptions?

Custom exceptions allow you to:

- Express **domain errors** using the business vocabulary.
- Distinguish different types of failure with precision.
- Add extra information beyond the message (e.g.: error code, resource ID).
- Facilitate specific handling per layer.

---

## How to create them

Extend `Exception` (checked) or `RuntimeException` (unchecked):

```java
// Base domain exception
public class AppException extends RuntimeException {
    public AppException(String message) {
        super(message);
    }
    public AppException(String message, Throwable cause) {
        super(message, cause);
    }
}

// Specific exception
public class UserNotFoundException extends AppException {
    private final int userId;

    public UserNotFoundException(int userId) {
        super("User not found: " + userId);
        this.userId = userId;
    }

    public int getUserId() { return userId; }
}
```

---

## Recommended hierarchy

```
AppException (domain base, unchecked)
├── UserNotFoundException
├── InsufficientBalanceException
├── ProductOutOfStockException
└── ValidationException
```

---

## Best practices

- Always include a constructor that accepts `Throwable cause` to preserve the original stack trace.
- Add extra fields when contextual information is important.
- Prefer **unchecked** for domain errors (does not pollute signatures).
- Use **checked** only for situations the caller **must** be forced to handle.
- Name clearly: `UserNotFoundException` is much better than `AppException`.

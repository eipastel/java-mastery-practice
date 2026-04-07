# When to catch an exception?

## The golden rule

Catch an exception **only if you can do something useful with it**:

- Recover from the error (retry, use a default value).
- Convert to a more appropriate exception for the context.
- Log the error and re-throw.
- Present a meaningful message to the user.

---

## Good: catching to recover

```java
try {
    int value = Integer.parseInt(input);
    process(value);
} catch (NumberFormatException e) {
    System.out.println("Invalid input. Using default value: 0");
    process(0); // recovery with default value
}
```

---

## Good: catching to convert

```java
try {
    file.read();
} catch (IOException e) {
    throw new ProcessingException("Failed to read the input file", e);
    // the original cause is preserved in the constructor
}
```

---

## Bad: swallowing the exception

```java
try {
    file.read();
} catch (IOException e) {
    // does nothing — NEVER do this!
    // The error disappears silently and the bug becomes impossible to trace
}
```

---

## Bad: unnecessarily catching generic Exception

```java
try {
    operation();
} catch (Exception e) {
    // catches EVERYTHING — including errors you did not expect
    // Prefer catching the specific types you know how to handle
}
```

---

## Summary of best practices

1. Only catch if you **know what to do** with the error.
2. **Never** silence an exception without at least logging it.
3. Catch the **most specific** type possible.
4. Preserve the original exception as `cause` when re-throwing.
5. Do not use exceptions for normal control flow.

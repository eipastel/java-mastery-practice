# Exception Best Practices

## 1. try-with-resources

For any resource that implements `AutoCloseable` (streams, connections, etc.), use
`try-with-resources`. The resource is automatically closed at the end of the block, even if an exception occurs.

```java
try (FileReader reader = new FileReader("file.txt");
     BufferedReader br = new BufferedReader(reader)) {
    String line = br.readLine();
} // reader and br are automatically closed here
// No need for finally { reader.close(); }
```

---

## 2. Catch the most specific first

```java
try {
    operation();
} catch (FileNotFoundException e) {  // most specific first
    // handles file not found
} catch (IOException e) {            // more general after
    // handles any other IO
}
// NEVER reverse the order — FileNotFoundException would never be reached
```

---

## 3. Never silence an exception

```java
// WRONG
catch (Exception e) { }  // swallow — invisible bug

// CORRECT
catch (Exception e) {
    logger.error("Operation failed", e);  // at least log it
    throw e; // or re-throw
}
```

---

## 4. Do not use exceptions for control flow

```java
// WRONG — exception as control flow
try {
    int result = Integer.parseInt(input);
} catch (NumberFormatException e) {
    result = 0; // avoidable with prior validation
}

// CORRECT — validate first
if (input.matches("\\d+")) {
    int result = Integer.parseInt(input);
}
```

---

## 5. Preserve the cause when re-throwing

```java
// WRONG — loses the original stack trace
catch (IOException e) {
    throw new ProcessingException("Failure"); // cause lost!
}

// CORRECT — preserves the cause
catch (IOException e) {
    throw new ProcessingException("Processing failure", e); // cause preserved
}
```

---

## 6. Multi-catch (Java 7+)

```java
try {
    operation();
} catch (IOException | SQLException e) {
    // handles both the same way
    logger.error("IO or database failure", e);
}
```

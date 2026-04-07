# throw vs throws

## throw — throws the exception

`throw` is a **statement** that actually throws an exception at runtime.
It must be followed by an instance of `Throwable`.

```java
if (age < 0) {
    throw new IllegalArgumentException("Age cannot be negative: " + age);
}
```

- Only **one** exception per `throw` statement.
- Can appear anywhere in the method body.
- After `throw`, method execution is interrupted.

---

## throws — declares that the method may throw

`throws` is a **declaration in the method signature**, informing that it may propagate
one or more checked exceptions to the caller.

```java
public void readFile(String path) throws IOException, FileNotFoundException {
    // method body
}
```

- Can list **multiple** exceptions separated by commas.
- Required for checked exceptions that are not caught within the method itself.
- For unchecked exceptions, `throws` is optional (but may be used for documentation).

---

## Joint usage

```java
public int divide(int a, int b) throws ArithmeticException {
    if (b == 0) {
        throw new ArithmeticException("Division by zero");  // throw launches it
    }
    return a / b;
}
```

---

## Summary

| Keyword  | What it does                              | Where it appears       |
|----------|-------------------------------------------|------------------------|
| `throw`  | Throws an exception instance              | Method body            |
| `throws` | Declares that the method may propagate    | Method signature       |

# Autoboxing and Unboxing

## What is it?

**Autoboxing** is the automatic conversion by the Java compiler from a **primitive** type to its
corresponding **wrapper class**.
**Unboxing** is the reverse process: from wrapper to primitive.

Before Java 5, this conversion had to be done manually. Today it is automatic.

---

## Autoboxing

```java
// Without autoboxing (before Java 5):
Integer x = Integer.valueOf(42);

// With autoboxing (Java 5+):
Integer x = 42;  // the compiler generates Integer.valueOf(42) under the hood
```

---

## Unboxing

```java
Integer x = 42;

// Without unboxing (before Java 5):
int y = x.intValue();

// With unboxing (Java 5+):
int y = x;  // the compiler generates x.intValue() under the hood
```

---

## Where autoboxing happens automatically

- Assignment: `Integer i = 5;`
- Method parameters when the signature expects a wrapper
- Collections: `list.add(10)` on `List<Integer>`
- Mixed arithmetic expressions with wrappers

---

## Pitfall: NullPointerException on unboxing

This is the most dangerous pitfall of autoboxing:

```java
Integer value = null;      // wrapper can be null
int result = value;        // unboxing: calls value.intValue()
                           // throws NullPointerException at runtime!
```

The compiler accepts it without complaint, but it blows up at runtime.

---

## Pitfall: performance in loops

```java
Long sum = 0L;
for (long i = 0; i < 1_000_000; i++) {
    sum += i;  // unboxing + operation + boxing ON EVERY ITERATION
}
```

Use primitives in intensive computations to avoid excessive object creation.

---

## Pitfall: comparison with ==

```java
Integer a = 1000;
Integer b = 1000;
System.out.println(a == b);      // false! (outside the cache -128..127)
System.out.println(a.equals(b)); // true
```

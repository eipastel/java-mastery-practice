# Wrapper Classes

## What are they?

**Wrapper classes** are classes that "wrap" primitive types into objects.
Each primitive type has its corresponding wrapper:

| Primitive | Wrapper     |
|-----------|-------------|
| `int`     | `Integer`   |
| `long`    | `Long`      |
| `double`  | `Double`    |
| `float`   | `Float`     |
| `boolean` | `Boolean`   |
| `char`    | `Character` |
| `byte`    | `Byte`      |
| `short`   | `Short`     |

---

## Why use wrappers?

- Collections (`List`, `Map`, etc.) only accept objects, not primitives: `List<Integer>` works,
  `List<int>` does not.
- Utility methods: `Integer.parseInt("42")`, `Integer.MAX_VALUE`, `Integer.toBinaryString(10)`.
- Represent the absence of a value with `null` (primitives can never be `null`).

---

## Integer Cache: special == behavior

The JVM maintains an **internal cache** of `Integer` objects for values between **-128 and 127**.
This means that for this range, `Integer.valueOf(n)` (and autoboxing) always returns
the **same object**.

```java
Integer a = 100;  // autoboxing — uses the cache
Integer b = 100;
System.out.println(a == b); // true — same object from the cache

Integer c = 200;  // outside the cache
Integer d = 200;
System.out.println(c == d); // false — different objects!
```

**Conclusion**: never use `==` to compare Integers — use `equals()`.

---

## Useful Integer methods

```java
Integer.parseInt("42")       // String -> int
Integer.valueOf(42)          // int -> Integer (uses cache when possible)
Integer.MAX_VALUE            // 2147483647
Integer.MIN_VALUE            // -2147483648
Integer.toBinaryString(10)   // "1010"
Integer.toHexString(255)     // "ff"
Integer.compare(a, b)        // safe comparison without overflow risk
Integer.bitCount(7)          // number of 1 bits in binary representation
```

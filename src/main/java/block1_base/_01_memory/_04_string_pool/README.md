# String Pool

## What is the String Pool?

The **String Pool** (or String Intern Pool) is a special area within the **Heap** where the JVM
stores unique instances of String literals.

When you write a String literal in code, the JVM first checks if it already exists in the pool:
- If it exists: returns the existing reference (without creating a new object).
- If it does not exist: creates the object in the pool and returns the reference.

```java
String a = "java";  // creates "java" in the pool
String b = "java";  // reuses the same object from the pool
System.out.println(a == b); // true — same reference
```

---

## new String() does not use the pool

When you use `new String("java")`, the JVM **always creates a new object on the heap**, outside the pool.

```java
String c = new String("java"); // new object on the heap
System.out.println(a == c);    // false — different objects
```

---

## intern()

The `intern()` method forces the String to be added to the pool (or returns the pool reference
if it already exists).

```java
String d = new String("java").intern();
System.out.println(a == d); // true — now d points to the pool
```

---

## Diagram

```
String Pool (inside the Heap)
+------------------+
|    "java"   <----+---- a, b, d
+------------------+

Heap (outside the pool)
+------------------+
|    "java"   <----+---- c  (created with new String())
+------------------+
```

---

## Why does this matter?

- **Performance**: equal literals share memory.
- **Comparison**: never use `==` to compare Strings — use `equals()`.
- **Immutability**: Strings are immutable; that is why the pool is safe to use.

# The final keyword

## What is final?

`final` is a Java keyword that indicates **binding immutability** — you cannot
reassign, override, or extend what has been marked as `final`.

---

## 1. final on a variable

The variable can only receive a value **once**. After the assignment, it cannot be reassigned.

```java
final int MAX = 100;
MAX = 200; // compile error!
```

**Note**: for objects, `final` prevents reassignment of the **reference**, but does not prevent
**mutation of the object**:

```java
final List<String> list = new ArrayList<>();
list.add("ok");           // allowed — mutating the object
list = new ArrayList<>(); // ERROR — reassigning the reference
```

---

## 2. final on a method

A `final` method **cannot be overridden** in subclasses.

```java
class Animal {
    final void breathe() { System.out.println("breathing"); }
}
class Dog extends Animal {
    // void breathe() { } // compile error if uncommented
}
```

Use it when the behavior is critical and must not be altered by subclasses.

---

## 3. final on a class

A `final` class **cannot be extended** (subclassed).

```java
final class Immutable { }
// class Attempt extends Immutable { } // compile error
```

Examples in the JDK: `String`, `Integer`, `Math` — all are `final`.

---

## Best practices

- Prefer `final` on local variables that do not need to change — communicates intent and prevents bugs.
- Use it on class constants together with `static`: `static final double PI = 3.14159;`
- Mark method parameters as `final` to indicate they should not be reassigned
  inside the method.

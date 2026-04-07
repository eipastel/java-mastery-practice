# Type Safety

## Raw Types — the problem

A **raw type** is a generic class used without the type parameter:

```java
List list = new ArrayList(); // raw type — without <T>
list.add("text");
list.add(42);       // compiler does not complain!
String s = (String) list.get(1); // ClassCastException at runtime!
```

Raw types exist only for compatibility with code written before Java 5.
**Avoid them in new code.**

---

## With generics — type safety

```java
List<String> list = new ArrayList<>();
list.add("text");
// list.add(42); // COMPILE ERROR — detected before running!
String s = list.get(0); // no cast needed
```

---

## Unchecked warnings

The compiler emits an `unchecked warning` when you mix raw types with generic types:

```java
List rawList = new ArrayList();
rawList.add("abc");

List<String> typed = rawList; // unchecked warning
String s = typed.get(0);     // may fail at runtime if the raw type had wrong types
```

---

## Why does this matter?

- Errors with raw types appear **at runtime** as `ClassCastException` — hard to trace.
- Errors with generics appear **at compile time** — easy to fix immediately.

---

## Practical rule

Always use the full type parameter:

```java
// BAD
List list = new ArrayList();
Map  map  = new HashMap();

// GOOD
List<String>         list = new ArrayList<>();
Map<String, Integer> map  = new HashMap<>();
```

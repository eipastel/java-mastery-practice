# Type Erasure

## What is it?

**Type Erasure** is the mechanism by which the Java compiler **removes** generic type parameters
during compilation. At runtime, `List<String>` and `List<Integer>` are the **same
class**: just `List`.

This was done to maintain backward compatibility with code written before Java 5.

---

## Consequences

### 1. getClass() returns the same type

```java
List<String>  strs = new ArrayList<>();
List<Integer> ints = new ArrayList<>();

System.out.println(strs.getClass() == ints.getClass()); // true!
System.out.println(strs.getClass().getName()); // java.util.ArrayList
```

### 2. instanceof does not work with generic type

```java
List<String> list = new ArrayList<>();
// if (list instanceof List<String>) { ... }  // COMPILE ERROR
if (list instanceof List) { ... }             // OK — without type parameter
if (list instanceof List<?>) { ... }          // OK — wildcard accepted
```

### 3. Cannot create arrays of generic types

```java
// T[] array = new T[10]; // COMPILE ERROR
```

### 4. Cannot use generic types in new, instanceof, catch expressions

---

## Type tokens (workaround)

To obtain the type at runtime, the **type token** technique is used:

```java
Class<String> token = String.class;
// Allows passing the type as a parameter
```

Libraries like Jackson and Spring use `TypeReference` or `ParameterizedType` to
work with generic types at runtime (JSON deserialization, etc.).

---

## What the compiler does with type erasure

```java
// Code you write:
List<String> list = new ArrayList<>();
list.add("hello");
String s = list.get(0);

// What the compiler generates (approximately):
List list = new ArrayList();
list.add("hello");
String s = (String) list.get(0); // cast inserted automatically
```

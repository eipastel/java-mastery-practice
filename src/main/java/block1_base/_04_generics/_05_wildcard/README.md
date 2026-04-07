# Unbounded Wildcard: <?>

## What is it?

`<?>` (unbounded wildcard) represents a list of **any unknown type**.
It is equivalent to `<? extends Object>`.

---

## When to use?

Use `<?>` when:
- You only need operations that do not depend on the type (e.g.: `size()`, `clear()`, iteration).
- The method behaves identically for any type.
- You want to accept `List<String>`, `List<Integer>`, `List<Anything>` with the same parameter.

---

## Limitations

With `<?>`, you can only:
- Read elements as `Object`.
- Call methods that do not depend on the type (`size()`, `isEmpty()`, `clear()`).

You **cannot add** anything (except `null`):

```java
void example(List<?> list) {
    Object obj = list.get(0); // OK — reading as Object
    // list.add("text");       // ERROR — type unknown
    // list.add(null);         // the only allowed exception
}
```

---

## Difference from `List<Object>`

```java
List<Object> listObj = new ArrayList<>();
List<String> listStr = new ArrayList<>();

// List<Object> does not accept List<String>:
// processObj(listStr);  // ERROR

// List<?> accepts any list:
void print(List<?> list) { ... }
print(listStr); // OK
print(listObj); // OK
```

---

## Summary of wildcards

| Wildcard          | Reading      | Writing     | Accepts                 |
|-------------------|--------------|-------------|-------------------------|
| `<?>`             | As Object    | No          | Any type                |
| `<? extends T>`   | As T         | No          | T and subclasses        |
| `<? super T>`     | As Object    | Yes (T)     | T and superclasses      |

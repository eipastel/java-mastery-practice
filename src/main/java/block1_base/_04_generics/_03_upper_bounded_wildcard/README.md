# Upper Bounded Wildcard: ? extends T

## What is it?

`<? extends T>` accepts `T` **or any subclass of T**.
You use it when you want to **read** data from a structure, regardless of the exact subtype.

---

## Motivation

Without a wildcard, a method that accepts `List<Number>` **does not accept** `List<Integer>`:

```java
void sum(List<Number> list) { ... }

List<Integer> ints = List.of(1, 2, 3);
sum(ints); // COMPILE ERROR! List<Integer> is not List<Number>
```

With upper bounded wildcard:

```java
void sum(List<? extends Number> list) { ... }

sum(ints);         // OK — Integer extends Number
sum(List.of(1.5)); // OK — Double extends Number
```

---

## What you can and cannot do

```java
void example(List<? extends Number> list) {
    Number n = list.get(0); // OK — reading as Number
    list.add(42);           // ERROR! Compiler does not know the exact type parameter
                            // (could be List<Double>, List<Integer>, etc.)
}
```

**PECS rule: Producer Extends** — if the list **produces** (provides) elements for you, use `extends`.

---

## Example hierarchy

```
Number
├── Integer
├── Double
└── Long
```

`List<? extends Number>` accepts: `List<Number>`, `List<Integer>`, `List<Double>`, `List<Long>`, etc.

---

## Summary

| Wildcard          | Accepts                   | Can add? | Can read as? |
|-------------------|---------------------------|----------|--------------|
| `? extends T`     | T and subclasses          | No       | T            |

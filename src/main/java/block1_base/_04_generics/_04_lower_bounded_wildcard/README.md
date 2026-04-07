# Lower Bounded Wildcard: ? super T

## What is it?

`<? super T>` accepts `T` **or any superclass of T**.
You use it when you want to **write** (add) data to a structure.

---

## Motivation

If you want to add `Integer` to a list, the list can be of `Integer`, `Number` or `Object`:

```java
void addNumbers(List<? super Integer> list) {
    list.add(1);
    list.add(2);
    list.add(3);
}

List<Integer> ints = new ArrayList<>();
List<Number>  nums = new ArrayList<>();
List<Object>  objs = new ArrayList<>();

addNumbers(ints);  // OK
addNumbers(nums);  // OK — Number is a superclass of Integer
addNumbers(objs);  // OK — Object is a superclass of Integer
```

---

## What you can and cannot do

```java
void example(List<? super Integer> list) {
    list.add(42);      // OK — Integer is compatible
    list.add(100);     // OK

    Object obj = list.get(0); // OK only as Object — exact type unknown
    Integer i  = list.get(0); // ERROR! Could be List<Number> or List<Object>
}
```

**PECS rule: Consumer Super** — if the list **consumes** (receives) elements from you, use `super`.

---

## PECS: Producer Extends, Consumer Super

```
Reading from the list  => use extends (producer extends)
Writing to the list    => use super   (consumer super)
Doing both             => use concrete type <T>
```

---

## Summary

| Wildcard      | Accepts                   | Can add T? | Can read as?   |
|---------------|---------------------------|------------|----------------|
| `? super T`   | T and superclasses        | Yes        | Object only    |

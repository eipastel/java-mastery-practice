# Pass by Value

## Java is ALWAYS pass-by-value

In Java, **all arguments are passed by value**. The called method receives a **copy**
of the value passed.

The confusion arises because, for objects, the "value" that is copied is the **reference** (the address
of the object on the heap) — not the object itself.

---

## Case 1: primitives

A copy of the primitive value is passed. Modifying the parameter inside the method **does not affect**
the original variable.

```java
void doubleIt(int x) {
    x = x * 2; // modifies only the local copy
}

int n = 5;
doubleIt(n);
System.out.println(n); // still 5
```

---

## Case 2: references to objects

A copy of the **reference** is passed. The method receives an address that points to the
**same object**. Therefore:

- **Mutating the object** (calling methods that change its state) **DOES AFFECT** the caller.
- **Reassigning the reference** (making the parameter point to another object) **DOES NOT AFFECT** the caller.

```java
void clear(List<String> list) {
    list.clear(); // mutates the object — caller sees the change
}

void replace(List<String> list) {
    list = new ArrayList<>(); // reassigns the local copy — caller DOES NOT see it
}
```

---

## Diagram

```
Caller                 Method receives a copy of the reference
+---------+            +----------+
| ref A   +----------> |  copy A  +----------> [Object on the Heap]
+---------+            +----------+

Mutate via copy => object changes => caller sees it
copy = other   => only the copy changes => caller does not see it
```

---

## Summary

| What was passed   | Modify object state        | Reassign the variable  |
|-------------------|---------------------------|------------------------|
| Primitive         | N/A                       | Does not affect caller |
| Reference         | Affects caller            | Does not affect caller |

# Reference vs Object

## Object

An **object** is the actual data structure that lives on the **Heap**. It contains the fields and the state.

```java
new Dog("Rex")  // this creates the object on the heap
```

---

## Reference

A **reference** is a variable that holds the **memory address** where the object is on the heap.
The reference itself lives on the **stack** (or as a field of another object on the heap).

```java
Dog d = new Dog("Rex");
//   ^                    reference 'd' lives on the stack
//                        Dog object lives on the heap
```

---

## Multiple references, same object

You can have multiple references pointing to the **same object**:

```java
Dog a = new Dog("Rex");
Dog b = a;   // 'b' points to the SAME object as 'a'

b.setName("Bolt");
System.out.println(a.getName()); // prints "Bolt"
```

Modifying the object via `b` affects what `a` sees, because both point to the same place on the heap.

---

## Diagram

```
Stack          Heap
+-----+        +-----------------------+
|  a  +------> |  Dog                  |
+-----+        |    name = "Bolt"      |
|  b  +------> |                       |
+-----+        +-----------------------+
```

---

## Watch out for null

A reference may not point to any object: that is `null`.
Trying to use a method or field on a null reference causes `NullPointerException`.

# Stack vs Heap

## What are the two memory regions?

The JVM divides memory into two main regions: **Stack** and **Heap**.

---

## Stack

- Stores **local primitive variables** and **references** (the address of the object, not the object itself).
- Each method call creates a **frame** on the stack; when the method returns, the frame is automatically destroyed.
- Extremely fast access (LIFO - Last In, First Out).
- Limited size; overflow causes `StackOverflowError`.
- **Lifecycle**: exists while the method is executing.

```
frame of the executing method
  +--------------------------+
  | int x = 10              |  <-- primitive lives here
  | ref p  -----------------> |  <-- reference lives here (points to the heap)
  +--------------------------+
```

---

## Heap

- Stores **objects** created with `new` and **arrays**.
- Managed by the **Garbage Collector**: objects without a reference are automatically collected.
- Slower access than the stack, but much larger.
- Shared among all threads of the application.
- **Lifecycle**: exists as long as there is at least one reference pointing to the object.

---

## Quick summary

| Feature            | Stack                        | Heap                        |
|--------------------|------------------------------|-----------------------------|
| What it stores     | Primitives, references       | Objects, arrays             |
| Lifecycle          | Lasts the method's scope     | Until GC collects           |
| Speed              | Very fast                    | Slower                      |
| Size               | Small and fixed              | Large and dynamic           |
| Management         | Automatic (scope)            | Garbage Collector           |

---

## Practical rule

> If you declare `int x = 5` inside a method, `x` goes to the **stack**.
> If you write `new Person()`, the `Person` object goes to the **heap**; the variable holding
> the reference goes to the **stack**.

# Garbage Collector (GC)

## What is it?

The **Garbage Collector** is the JVM's automatic mechanism that frees **Heap** memory occupied
by objects that are no longer reachable (no longer have any active reference pointing to them).

In Java, you **do not free memory manually** (unlike C/C++). The GC does it for you.

---

## When does an object become eligible for collection?

An object becomes eligible for GC when it is no longer **reachable** from any active reference
root (local variables, static fields, etc.).

```java
Person p = new Person("Ana");  // object is reachable
p = null;                      // object now has no reference — eligible for GC
```

---

## The GC is not deterministic

You **do not know exactly when** the GC will run. `System.gc()` is just a **hint** —
the JVM may ignore it.

```java
System.gc(); // hint, not a guarantee
```

Never write critical logic that depends on the GC running at a specific moment.

---

## WeakReference

`java.lang.ref.WeakReference<T>` allows you to reference an object without preventing the GC from collecting it.

```java
WeakReference<Person> ref = new WeakReference<>(new Person("Bob"));
// The object can be collected at any time
Person p = ref.get(); // returns null if already collected
```

Useful for caches: the data is kept while memory is available, but discarded under
memory pressure.

---

## Reference types

| Type           | Class                  | GC collects?                       |
|----------------|------------------------|------------------------------------|
| Strong (normal)| (regular reference)    | No, while there is a reference     |
| Weak           | `WeakReference<T>`     | Yes, on the next collection        |
| Soft           | `SoftReference<T>`     | Yes, only under memory pressure    |
| Phantom        | `PhantomReference<T>`  | After finalization                 |

---

## finalize() — deprecated

The `finalize()` method used to be called by the GC before collecting the object. It was **deprecated in Java 9**
because it is unpredictable and slow. Use `try-with-resources` and `AutoCloseable` to manage resources.

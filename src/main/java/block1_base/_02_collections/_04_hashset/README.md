# HashSet

## What is it?

`HashSet<E>` is the most common implementation of the `Set` interface. Internally it is a `HashMap`
where the elements are the keys (the value is always a fixed sentinel object).

---

## Main characteristics

- **No duplicates**: if you try to add an element that already exists (according to `equals()`
  and `hashCode()`), `add()` returns `false` and the set does not change.
- **No guaranteed order**: the iteration order may change between runs.
- **O(1) amortized** for `add`, `remove`, `contains`.
- Allows **one `null` element**.
- **Not thread-safe**.

---

## Essential methods

```java
set.add(e)          // adds; returns false if already existed
set.remove(e)       // removes; returns false if not present
set.contains(e)     // checks existence in O(1)
set.size()          // number of elements
set.isEmpty()       // true if empty
set.clear()         // removes everything

// Set operations
set.addAll(otherCollection)       // union
set.retainAll(otherCollection)    // intersection
set.removeAll(otherCollection)    // difference
```

---

## Why do equals() and hashCode() matter?

The HashSet uses `hashCode()` to find the bucket and `equals()` to confirm equality.

If two "equal" objects have a different `hashCode()`, the HashSet treats them as distinct
and allows duplicates — breaking the Set contract.

```java
// If Product does not override equals/hashCode:
Set<Product> set = new HashSet<>();
set.add(new Product("Pen", 1));
set.add(new Product("Pen", 1)); // treated as duplicate ONLY if equals/hashCode is correct
```

---

## Variants

| Implementation  | Order                  | Performance           |
|-----------------|------------------------|-----------------------|
| `HashSet`       | No order               | Fastest               |
| `LinkedHashSet` | Insertion order        | Slightly slower       |
| `TreeSet`       | Natural/Comparator     | O(log n) operations   |

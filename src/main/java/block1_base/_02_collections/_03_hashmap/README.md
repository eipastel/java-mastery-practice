# HashMap

## What is it?

`HashMap<K, V>` is the most common implementation of the `Map` interface. It stores **key-value** pairs
and uses a **hash table** to locate values by key.

---

## Complexities

| Operation          | Average complexity | Worst case (many collisions) |
|--------------------|--------------------|------------------------------|
| `put(k, v)`        | O(1)               | O(n)                         |
| `get(k)`           | O(1)               | O(n)                         |
| `remove(k)`        | O(1)               | O(n)                         |
| `containsKey(k)`   | O(1)               | O(n)                         |

---

## Important characteristics

- **Unique keys**: inserting with the same key **replaces** the previous value.
- **Allows one `null` key** and **`null` values**.
- **No guaranteed iteration order** (use `LinkedHashMap` for insertion order or `TreeMap` for natural order).
- **Not thread-safe** — use `ConcurrentHashMap` in concurrent environments.

---

## Essential methods

```java
map.put(k, v)                           // inserts or updates
map.get(k)                              // returns the value or null
map.getOrDefault(k, defaultValue)       // avoids null
map.containsKey(k)                      // checks existence
map.remove(k)                           // removes the entry
map.putIfAbsent(k, v)                   // inserts only if key does not exist
map.computeIfAbsent(k, f)              // inserts by computing the value if absent
map.computeIfPresent(k, f)             // updates if the key exists
map.merge(k, v, function)               // combines existing value with new one
map.forEach((k, v) -> ...)              // iterates key-value
map.entrySet()                          // Set of Map.Entry for efficient iteration
```

---

## How HashMap works internally

1. Computes `hashCode()` of the key.
2. Determines the bucket (index in the internal array) from the hash.
3. Stores the pair in the bucket.
4. If two distinct objects land in the same bucket (**collision**), they are stored in a linked list (or a tree if the list grows large, Java 8+).

**That is why `equals()` and `hashCode()` must be consistent**: equal objects (by equals) must have the same hashCode.

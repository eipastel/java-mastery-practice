# List, Set and Map

## Overview of Java collections

Java offers three main interfaces in the Collections Framework:

---

## List — Ordered sequence with duplicates

- Maintains the **insertion order**.
- Allows **duplicate elements**.
- Access by **index** (position).
- Main implementations: `ArrayList`, `LinkedList`.

```java
List<String> list = new ArrayList<>();
list.add("a");
list.add("b");
list.add("a"); // duplicate allowed
// result: ["a", "b", "a"]
```

---

## Set — Collection without duplicates

- **Does not allow duplicate elements** (based on `equals()` + `hashCode()`).
- **Does not guarantee order** (HashSet); natural order (TreeSet); insertion order (LinkedHashSet).
- Main implementations: `HashSet`, `LinkedHashSet`, `TreeSet`.

```java
Set<String> set = new HashSet<>();
set.add("a");
set.add("b");
set.add("a"); // ignored — duplicate
// result: {"a", "b"} (order not guaranteed)
```

---

## Map — Key -> value mapping

- Stores **key-value** pairs.
- Each **key is unique**; values may repeat.
- No guaranteed order (HashMap); insertion order (LinkedHashMap); natural order (TreeMap).
- Main implementations: `HashMap`, `LinkedHashMap`, `TreeMap`.

```java
Map<String, Integer> map = new HashMap<>();
map.put("ana", 30);
map.put("bob", 25);
map.put("ana", 31); // updates the value of the existing key
```

---

## When to use each?

| Need                                    | Recommended collection  |
|-----------------------------------------|-------------------------|
| Sequence with order and duplicates      | `List` (ArrayList)      |
| Guarantee element uniqueness            | `Set` (HashSet)         |
| Fast lookup by key                      | `Map` (HashMap)         |
| Naturally sorted order                  | `TreeSet`, `TreeMap`    |
| Iterate in insertion order              | `LinkedHashSet`, `LinkedHashMap` |

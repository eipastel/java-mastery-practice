# Operation Costs in Collections

## ArrayList

| Operation          | Complexity      | Reason                                       |
|--------------------|-----------------|----------------------------------------------|
| `get(i)`           | O(1)            | Direct array access by index                 |
| `set(i, v)`        | O(1)            | Direct array access                          |
| `add(v)` (end)     | O(1) amortized  | May need resize (array copy)                 |
| `add(i, v)` (mid)  | O(n)            | Shifts all elements ahead                    |
| `remove(i)`        | O(n)            | Shifts all elements ahead                    |
| `contains(v)`      | O(n)            | Linear search                                |

---

## LinkedList

| Operation                        | Complexity  | Reason                                |
|----------------------------------|-------------|---------------------------------------|
| `get(i)` / `set(i, v)`           | O(n)        | Traverses nodes until the index       |
| `addFirst()` / `removeFirst()`   | O(1)        | Adjusts head pointer                  |
| `addLast()` / `removeLast()`     | O(1)        | Adjusts tail pointer                  |
| `add(i, v)` (middle)             | O(n)        | Needs to reach the node at position i |
| `contains(v)`                    | O(n)        | Linear search                         |

---

## HashSet

| Operation      | Average | Worst case         |
|----------------|---------|--------------------|
| `add(e)`       | O(1)    | O(n) collisions    |
| `remove(e)`    | O(1)    | O(n) collisions    |
| `contains(e)`  | O(1)    | O(n) collisions    |

---

## HashMap

| Operation          | Average | Worst case         |
|--------------------|---------|--------------------|
| `put(k, v)`        | O(1)    | O(n) collisions    |
| `get(k)`           | O(1)    | O(n) collisions    |
| `remove(k)`        | O(1)    | O(n) collisions    |
| `containsKey(k)`   | O(1)    | O(n) collisions    |

---

## TreeSet / TreeMap

Every operation is **O(log n)** — balanced Red-Black tree.

---

## Rule of thumb

- Prefer **ArrayList** for access by index.
- Prefer **HashSet / HashMap** to check existence or look up by key.
- Use **TreeSet / TreeMap** when natural/comparable order is needed.
- Avoid **LinkedList** for random access — O(n) get is very expensive.

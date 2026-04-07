# ArrayList vs LinkedList

## ArrayList

Internally implemented as a **dynamic array**.

| Operation                    | Complexity  | Detail                                         |
|------------------------------|-------------|------------------------------------------------|
| `get(i)` / `set(i, v)`       | O(1)        | Direct access by index                         |
| `add(v)` (at the end)        | O(1)*       | Amortized; may need array resize               |
| `add(i, v)` (in the middle)  | O(n)        | Shifts all elements after the position         |
| `remove(i)` (in the middle)  | O(n)        | Shifts all elements after the position         |
| `contains(v)`                | O(n)        | Linear search                                  |

**Use ArrayList when**: you frequently access elements by index and insertions/removals
happen mainly at the end of the list.

---

## LinkedList

Implemented as a **doubly linked list**.

| Operation                        | Complexity  | Detail                                         |
|----------------------------------|-------------|------------------------------------------------|
| `get(i)` / `set(i, v)`           | O(n)        | Traverses nodes until the index                |
| `add(v)` (at the end)            | O(1)        | Adjusts only the last node's pointer           |
| `addFirst(v)` / `addLast(v)`     | O(1)        | Direct access to the ends                      |
| `removeFirst()` / `removeLast()` | O(1)        | Direct access to the ends                      |
| `add(i, v)` (in the middle)      | O(n)        | Needs to reach the node at position i          |
| `contains(v)`                    | O(n)        | Linear search                                  |

**Use LinkedList when**: you make many insertions/removals at the **ends** of the list
(FIFO queue, LIFO stack) and do not need fast access by index.

---

## Practical summary

```
Access by index         => ArrayList  (O(1) get)
Insert/remove at end    => ArrayList  (O(1) amortized)
Insert/remove in middle => LinkedList is not better (still O(n) to find the spot)
Queue / Deque           => LinkedList or ArrayDeque
```

> In practice, `ArrayList` is the default choice in most cases.
> Prefer `ArrayDeque` over `LinkedList` for queues and stacks.

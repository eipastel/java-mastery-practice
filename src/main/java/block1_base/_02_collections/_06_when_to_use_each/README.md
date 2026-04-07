# When to use each collection?

## Quick decision guide

```
Need a list with order and possible duplicates?
  => List
     Fast access by index?  => ArrayList
     Many insertions/removals at the ends?  => LinkedList / ArrayDeque

Need to guarantee uniqueness?
  => Set
     Order does not matter?  => HashSet
     Want to iterate in insertion order?  => LinkedHashSet
     Want natural/alphabetical order?  => TreeSet

Need to look up by key?
  => Map
     Order does not matter?  => HashMap
     Want to iterate in insertion order?  => LinkedHashMap
     Want keys in natural order?  => TreeMap

Need a queue (FIFO) or stack (LIFO)?
  => ArrayDeque (preferred for both)
  => LinkedList (implements Deque, but ArrayDeque is more efficient)

Need a priority queue?
  => PriorityQueue
```

---

## Summary table

| Collection       | Interface  | Order              | Duplicates | null | Thread-safe |
|------------------|------------|--------------------|------------|------|-------------|
| `ArrayList`      | List       | Insertion          | Yes        | Yes  | No          |
| `LinkedList`     | List/Deque | Insertion          | Yes        | Yes  | No          |
| `ArrayDeque`     | Deque      | Insertion          | Yes        | No   | No          |
| `HashSet`        | Set        | None               | No         | 1x   | No          |
| `LinkedHashSet`  | Set        | Insertion          | No         | 1x   | No          |
| `TreeSet`        | SortedSet  | Natural/Comparator | No         | No   | No          |
| `HashMap`        | Map        | None               | No (keys)  | 1 null key | No   |
| `LinkedHashMap`  | Map        | Insertion          | No (keys)  | 1 null key | No   |
| `TreeMap`        | SortedMap  | Natural/Comparator | No (keys)  | No   | No          |
| `PriorityQueue`  | Queue      | Priority           | Yes        | No   | No          |

---

## Thread-safe alternatives

For concurrent use, use the implementations from the `java.util.concurrent` package:
- `CopyOnWriteArrayList` — thread-safe List (optimized for frequent reads)
- `ConcurrentHashMap` — efficient thread-safe Map
- `ConcurrentLinkedQueue` — lock-free thread-safe Queue
- `BlockingQueue` (ArrayBlockingQueue, LinkedBlockingQueue) — for producer-consumer

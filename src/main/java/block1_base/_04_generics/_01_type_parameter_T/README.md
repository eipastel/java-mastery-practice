# Type Parameter T

## What are generics?

**Generics** allow classes, interfaces and methods to work with **any type**
of data without losing compile-time type checking.

Without generics, you would use `Object` and need manual casts — with the risk of
`ClassCastException` at runtime. With generics, the compiler checks everything.

---

## Generic class

```java
class Box<T> {      // T is the type parameter
    private T content;

    public void store(T item) { this.content = item; }
    public T get() { return content; }
}

Box<String>  bx1 = new Box<>();
bx1.store("hello");
String s = bx1.get(); // no cast!

Box<Integer> bx2 = new Box<>();
bx2.store(42);
int n = bx2.get();    // no cast!
```

---

## Generic method

A method can have its own type parameter, independent of the class:

```java
public static <T> void swap(T[] array, int i, int j) {
    T temp = array[i];
    array[i] = array[j];
    array[j] = temp;
}

Integer[] nums = {1, 2, 3};
swap(nums, 0, 2); // nums = [3, 2, 1]
```

---

## Naming conventions for type parameters

| Letter | Typical meaning                    |
|--------|------------------------------------|
| `T`    | Type (generic type)                |
| `E`    | Element (collection element)       |
| `K`    | Key (map key)                      |
| `V`    | Value (map value)                  |
| `N`    | Number                             |
| `R`    | Result (function result)           |

---

## Benefits

1. **Type safety at compile time**: the compiler detects type errors before running.
2. **Eliminates casts**: no need for `(String) list.get(0)`.
3. **Reusability**: a single class/method works with any type.

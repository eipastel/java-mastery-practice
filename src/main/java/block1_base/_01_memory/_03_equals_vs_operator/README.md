# == vs equals()

## The == operator

Compares **references** — checks if two variables point to the **same object** in memory
(same address on the heap).

For primitive types (`int`, `double`, etc.) `==` compares the **values** directly, since
primitives are not objects.

```java
String a = new String("hello");
String b = new String("hello");
System.out.println(a == b); // false — different objects on the heap
```

---

## The equals() method

Compares the **content** (semantic meaning) of the objects.
The default implementation in `Object` does the same thing as `==`, but classes like `String`,
`Integer` and `List` override `equals()` to compare the content.

```java
String a = new String("hello");
String b = new String("hello");
System.out.println(a.equals(b)); // true — same content
```

---

## Golden rule

| Situation                              | Use          |
|----------------------------------------|--------------|
| Check if it is the same object         | `==`         |
| Check if the content is equal          | `equals()`   |
| Compare primitives                     | `==`         |
| Check for null                         | `== null`    |

---

## Beware of String literals

String literals (`"hello"`) are stored in the **String Pool** and may be the same object,
making `==` return `true` by coincidence. Never rely on this to compare content —
always use `equals()`.

---

## equals() contract

To override correctly, `equals()` must be:
- **Reflexive**: `x.equals(x)` is always `true`
- **Symmetric**: `x.equals(y)` implies `y.equals(x)`
- **Transitive**: if `x.equals(y)` and `y.equals(z)`, then `x.equals(z)`
- **Consistent**: identical results for multiple calls without state change
- `x.equals(null)` must return `false`

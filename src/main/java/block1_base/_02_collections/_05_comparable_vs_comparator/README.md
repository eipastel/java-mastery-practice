# Comparable vs Comparator

## Comparable — natural order defined within the class

The `Comparable<T>` interface defines the **natural order** of a class. The class itself
implements the `compareTo()` method.

```java
public class Product implements Comparable<Product> {
    private String name;
    private double price;

    @Override
    public int compareTo(Product other) {
        return Double.compare(this.price, other.price); // sorts by price ascending
    }
}
```

- An object implements `Comparable` to say "this is my default sort order".
- Used automatically by `Collections.sort()`, `Arrays.sort()`, `TreeSet`, `TreeMap`.
- Limitation: only **one** natural order per class.

---

## Comparator — external and customizable order

The `Comparator<T>` interface defines a sorting rule **outside the class**, and you can have
as many variations as you want.

```java
Comparator<Product> byName  = Comparator.comparing(Product::getName);
Comparator<Product> byPrice = Comparator.comparingDouble(Product::getPrice).reversed();
```

- Does not modify the original class.
- Can be created with a lambda or method reference.
- Can be **chained**: `byName.thenComparingDouble(Product::getPrice)`.

---

## When to use each?

| Scenario                                              | Use           |
|-------------------------------------------------------|---------------|
| There is a logical "natural" order for the class      | `Comparable`  |
| The class is third-party (cannot modify it)           | `Comparator`  |
| Need multiple different sort orders                   | `Comparator`  |
| Ad-hoc one-off sorting with a lambda                  | `Comparator`  |

---

## compareTo / compare contract

- Returns **negative** if `this` < `other`
- Returns **zero** if `this` == `other`
- Returns **positive** if `this` > `other`

Must be consistent with `equals()` for correct use in `TreeSet`/`TreeMap`.

# Checked vs Unchecked Exceptions

## Exception hierarchy in Java

```
Throwable
├── Error            (serious JVM errors — do not catch)
└── Exception
    ├── RuntimeException    (unchecked)
    │   ├── NullPointerException
    │   ├── IllegalArgumentException
    │   ├── IndexOutOfBoundsException
    │   └── ClassCastException ...
    └── IOException         (checked)
        ├── FileNotFoundException
        └── SQLException ...
```

---

## Checked Exceptions

- Subclasses of `Exception` that do **not** extend `RuntimeException`.
- The **compiler requires** you to handle them with `try-catch` or declare them with `throws`.
- Represent **predictable and recoverable** situations outside the program's control:
  file not found, network connection failed, etc.

```java
// The compiler REQUIRES handling — does not compile without try-catch or throws
FileReader reader = new FileReader("file.txt"); // throws FileNotFoundException (checked)
```

---

## Unchecked Exceptions (RuntimeException)

- Subclasses of `RuntimeException`.
- The compiler **does not require** handling — you may ignore them.
- Represent **programming errors** that should be prevented before they occur:
  accessing a null object, index out of array bounds, division by zero, etc.

```java
String s = null;
s.length(); // NullPointerException — unchecked, compiler does not warn
```

---

## Practical rule

| Type       | When to use                                          | Does the compiler require handling? |
|------------|------------------------------------------------------|-------------------------------------|
| Checked    | Predictable external failure (IO, network, parsing)  | Yes                                 |
| Unchecked  | Programming error (null, index, invalid state)       | No                                  |

---

## Industry debate

Many modern frameworks (Spring, Hibernate) prefer **unchecked exceptions** for business/domain errors,
because checked exceptions "pollute" method signatures. The trend is to use checked only for IO/network
errors and unchecked for everything else.

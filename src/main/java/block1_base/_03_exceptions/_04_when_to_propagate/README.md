# When to propagate an exception?

## Propagate when the current layer does not know what to do

If the current method does not have enough context to handle the error, **let the exception bubble up**
to the layer that knows how to act.

```java
// Repository: accesses the database — does not know what to do with an IO failure
public User find(int id) throws SQLException {
    // simply propagates — it is not the repository's responsibility to decide
    return db.query("SELECT * FROM users WHERE id = " + id);
}

// Service: converts to a domain exception
public User getUser(int id) {
    try {
        return repository.find(id);
    } catch (SQLException e) {
        throw new RuntimeException("Failed to find user: " + id, e);
    }
}
```

---

## Chain of responsibilities

```
RepositoryDB      -> throws IOException (does not know how to handle)
    ^
UserService       -> converts to DomainException (translates the error)
    ^
Controller        -> catches and returns HTTP 500 response (knows what to do)
```

---

## Converting exceptions

When propagating across layers, it is good practice to **convert** the exception to a more appropriate
type for the context, preserving the original cause:

```java
catch (SQLException e) {
    throw new DataException("Database access failure", e); // preserves the cause
}
```

---

## When to use throws vs try-catch

| Situation                                             | Decision                             |
|-------------------------------------------------------|--------------------------------------|
| Current layer has no context to handle                | Propagate (`throws`)                 |
| Current layer can recover/provide an alternative      | Catch and handle                     |
| Border layer (controller, main)                       | Catch, log, and respond              |
| Converting checked to unchecked                       | Catch and re-throw as unchecked      |

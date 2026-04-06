---
name: generate-challenge
description: Generates a variation of an existing exercise with a different domain and context, same structure and difficulty. Pass the topic path as argument.
argument-hint: <block/topic/subtopic>
disable-model-invocation: true
allowed-tools: Read Write Glob
---

## Goal

Read the original exercise and generate a variation with a different domain — same structure, same level of challenge — so you can practice the same concept without memorizing the original solution.

## Step 1 — Resolve paths

The topic path is `$ARGUMENTS` (e.g. `block1-base/01-memory/01-stack-vs-heap`).

Read both files:
- `$ARGUMENTS/README.md` — understand the concept, context, and what is being asked
- `$ARGUMENTS/Exercise.java` — understand the format (class name, method signatures, TODO structure)

If either file is missing, stop and tell the user which file is absent.

## Step 2 — Check difficulty parameter

The user may append a difficulty hint after the path: `same` (default), `harder`, or `easier`.

Parse `$ARGUMENTS`:
- If it ends with `same`, `harder`, or `easier`, extract it as `DIFFICULTY` and use the rest as the path.
- Otherwise, default `DIFFICULTY` to `same`.

**same** — identical structure and complexity, new domain only.
**harder** — add one extra constraint (edge case, additional method, deeper reasoning required).
**easier** — simplify one part (fewer methods, more scaffolding provided, simpler data).

## Step 3 — Choose a new domain

Pick a domain that is:
- Clearly **different** from the original exercise.
- Realistic and relatable (e-commerce, healthcare, gaming, logistics, social media, IoT, food delivery, HR systems — not Foo/Bar/Animal).
- Well-suited to the same Java concept being practiced.

State the domain swap before writing, e.g.:
```
Domain swap: bank account transactions → hospital patient records
```

## Step 4 — Generate the variation

Write a new `Exercise.java` to stdout (do NOT overwrite the original file). The variation must:

- Keep the same class name `Exercise`
- Keep the same method signatures and return types (adjust parameter names/types only when the domain demands it)
- Replace all domain-specific names (variables, comments, scenario) with the new domain
- Keep the TODO comments at the same spots — do NOT implement anything
- Adjust the difficulty as specified in Step 2

Format:
```java
// Generated variation — Domain: <new domain>
// Original topic: $ARGUMENTS
// Difficulty: <same|harder|easier>

public class Exercise {
    // ... variation code with TODOs ...
}
```

## Step 5 — Confirm

Tell the user:
```
Variation generated for: $ARGUMENTS
Domain swap: <old> → <new>
Difficulty: <same|harder|easier>

Copy the code above into a new file to use it.
Original Exercise.java was not modified.
```

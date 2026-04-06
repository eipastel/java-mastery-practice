# Java Mastery Practice

A study repository for practicing core Java concepts through structured exercises, organized by topic and difficulty progression.

Each topic lives in its own folder with a `README.md` (concept + exercise description) and an `Exercise.java` (scaffold with TODOs to fill in).

## Structure

```
src/main/java/
├── block1-base/          — Java fundamentals
│   ├── 01-memory/        — Stack, heap, references, GC
│   ├── 02-collections/   — List, Set, Map, costs
│   ├── 03-exceptions/    — Checked, unchecked, propagation
│   └── 04-generics/      — Type parameters, wildcards, erasure
│
├── block2-modern/        — Modern Java
│   ├── 05-java-modern/   — Lambdas, records, switch expressions
│   ├── 06-functional/    — Stream API, map/filter/reduce
│   ├── 07-testing/       — JUnit, Mockito, unit vs integration
│   └── 08-clean-code/    — Cohesion, coupling, SRP, refactoring
│
├── block3-backend/       — Backend development
│   ├── 09-database/      — JDBC, JPA, transactions, N+1
│   ├── 10-spring/        — DI, beans, REST, validation
│   └── 11-architecture/  — Layers, DDD, hexagonal, clean arch
│
└── block4-advanced/      — Advanced topics
    ├── 12-concurrency/   — Threads, executors, futures, locks
    ├── 13-jvm/           — Bytecode, classloader, JIT, GC
    ├── 14-performance/   — Profiling, bottlenecks, benchmarks
    ├── 15-security/      — Auth, JWT, OAuth2, CORS, injection
    └── 16-advanced-topics/ — Reflection, annotations, AOP, virtual threads
```

Each exercise follows this layout:

```
<block>/<topic>/<subtopic>/
├── README.md      ← concept explanation and what to implement
└── Exercise.java  ← scaffold with TODOs
```

## Study workflow

This repository uses [Claude Code](https://claude.ai/code) skills to guide the full study cycle of each exercise. Three skills cover the entire flow:

### 1. `/generate-challenge <path>` — Generate a variation

Once you've solved an exercise, use this skill to get a fresh variation with a different domain — same structure, same concept, so you can't just copy the previous solution.

Accepts an optional difficulty hint: `same` (default), `harder`, or `easier`.

```
/generate-challenge block1-base/01-memory/01-stack-vs-heap
/generate-challenge block1-base/01-memory/01-stack-vs-heap harder
/generate-challenge block2-modern/06-functional/01-stream-api easier
```

### 2. `/review-submission <path>` — Get feedback on your solution

After filling in the `Exercise.java`, run this skill to get structured feedback across three dimensions:

- **Conceptual correctness** — are you applying the right Java concept?
- **Functional correctness** — does it actually work? edge cases covered?
- **Code quality** — is there a more idiomatic or cleaner approach?

Points out what's good before pointing out what to improve. Never rewrites your code — only guides.

```
/review-submission block1-base/01-memory/01-stack-vs-heap
/review-submission block3-backend/09-database/05-n-plus-1
```

### 3. `/tutor-mode <path>` — Get guided without being given the answer

Stuck on an exercise? This skill acts as a Socratic tutor. It reads your current state and:

- If you haven't started: asks one or two questions to find where the conceptual block is.
- If you have partial code: identifies what you got right, where you stopped, and suggests only the next single step.

Never gives the full solution. Guides one step at a time.

```
/tutor-mode block1-base/03-exceptions/01-checked-vs-unchecked
/tutor-mode block4-advanced/12-concurrency/09-race-condition
```

---

## Typical session

```
# 1. Pick an exercise and read the README
# 2. Fill in Exercise.java — try without any help first

# 3. Stuck? Ask for a hint without spoiling the solution
/tutor-mode block1-base/02-collections/03-hashmap

# 4. Done? Get feedback on your solution
/review-submission block1-base/02-collections/03-hashmap

# 5. Want to practice the same concept with a fresh scenario?
/generate-challenge block1-base/02-collections/03-hashmap harder
```

## How to run

```bash
# Compile
mvn compile

# Run
mvn exec:java -Dexec.mainClass="Main"

# Compile and run
mvn compile exec:java -Dexec.mainClass="Main"
```

> Requires Java 21+ (uses `void main()` without a wrapping class — JEP 463, preview feature).

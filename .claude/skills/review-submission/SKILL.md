---
name: review-submission
description: Reviews the user's solution to an exercise. Analyzes correctness, functionality, and quality without rewriting the code. Pass the topic path as argument.
argument-hint: <block/topic/subtopic>
disable-model-invocation: true
allowed-tools: Read Glob
---

## Goal

Review the user's current solution to an exercise across three dimensions — in order. Point out what is good before pointing out what to improve. Never rewrite the code.

## Step 1 — Resolve paths

The topic path is `$ARGUMENTS` (e.g. `block1-base/01-memory/01-stack-vs-heap`).

Read both files:
- `$ARGUMENTS/README.md` — understand what was asked and what concept is being tested
- `$ARGUMENTS/Exercise.java` — read both the original scaffold AND the user's written code

If `Exercise.java` has no user code (only TODOs remaining), stop and tell the user: "No solution found yet. Complete at least part of the exercise before requesting a review."

## Step 2 — Analyze in three dimensions

Evaluate in this order:

### Dimension 1 — Conceptual correctness
Is the user applying the right Java concept for this topic?
- Is the core concept (e.g. stack vs heap, equals contract, stream pipeline) understood and applied?
- Common mistakes to check: using `==` when `equals` is needed, mixing checked/unchecked exception semantics, ignoring immutability, etc.

### Dimension 2 — Functional correctness
Does the code actually work?
- Are there compile errors, logic bugs, or missing return statements?
- Are edge cases handled (null, empty collections, division by zero, etc.) when the README implies they should be?

### Dimension 3 — Code quality
Is there a cleaner or more idiomatic Java way?
- Only raise this if there is a meaningfully better approach, not cosmetic preferences.
- Examples: using `Map.getOrDefault` instead of null check + put, using `Optional` instead of null return, using enhanced for instead of index loop where applicable.

## Step 3 — Write the review

Structure the output:

```
## Review: <topic name>

### What's working
<1–3 bullet points on what the user got right — be specific, not generic praise>

### Dimension 1 — Conceptual correctness
<Finding or ✓ if correct>

### Dimension 2 — Functional correctness
<Finding or ✓ if correct>

### Dimension 3 — Code quality
<Finding or ✓ if no significant improvement needed>

### Next step
<One concrete, actionable suggestion — only if there is something to fix>
```

Rules:
- Never paste a corrected version of the code.
- Never say "here's how it should be done" followed by a full implementation.
- You may show a single line or expression as a hint if a quality improvement is genuinely non-obvious.
- Keep the tone balanced — a junior dev should not feel crushed by this review.

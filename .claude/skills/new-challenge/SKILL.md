---
name: new-challenge
description: Generates a new simple, logic-focused coding challenge and creates README.md + Challenge.java skeleton in src/main/java/challenges/.
argument-hint: normal-challenge
disable-model-invocation: true
allowed-tools: Read Write Glob
---

## Goal

Generate a new simple, self-contained coding challenge — focused on logic and implemented as methods, not as a console application. Write the files to `src/main/java/challenges/`. Never implement the solution.

## Step 1 — Determine the next challenge number

List all existing directories inside `src/main/java/challenges/` using Glob pattern `src/main/java/challenges/_*/`.

Count how many exist to determine the next sequential number (e.g., if there are 3, the next is `_04`).

## Step 2 — Invent a challenge

Pick a scenario that is:
- **Simple and focused** — one clear goal, completable in 15–45 minutes
- **Logic or math based** — the fun is in figuring out the logic, not in wiring up infrastructure
- **No console interaction** — no Scanner, no interactive prompts; input comes from method parameters
- **Not CRUD** — avoid create/read/update/delete over a data store
- **Different from all existing challenges** — read the existing `README.md` files in `src/main/java/challenges/` to avoid repeating ideas

Good examples: soma de matrizes, triângulo de asteriscos, jokenpô (lógica pura), validador de CPF, calculadora de troco, conversor de temperatura, verificador de palíndromo, gerador de tabuada, placar de tênis, contador de vogais.

Bad examples: sistema de cadastro, CRUD de produtos, aplicação com menu interativo no console, busca binária pura.

## Step 3 — Derive the folder name

From the challenge title, create a lowercase slug with underscores. Examples:
- "Soma de matrizes" → `soma_de_matrizes`
- "Triângulo de asteriscos" → `triangulo_de_asteriscos`
- "Jokenpô simples" → `jokenpo_simples`

Full folder path: `src/main/java/challenges/_<NN>_<slug>/` where `<NN>` is the two-digit number from Step 1.

## Step 4 — Write the README.md

Create `src/main/java/challenges/_<NN>_<slug>/README.md` with this structure:

```markdown
# <Challenge title>

<1–2 sentences describing what to build, in plain Portuguese. Be direct.>

## O que implementar

- <Requirement 1 — what method/logic to write, concrete and specific>
- <Requirement 2>
- <Requirement 3 if needed>

## Exemplo

```
<Concrete example: method call → expected return or printed output>
```

## Bônus (opcional)

- <One optional extra — not required>
```

Rules for the README:
- Write in Portuguese
- Each requirement describes a method or behavior to implement, not a UI flow
- Do not hint at the implementation approach
- The example must show actual values, not pseudocode

## Step 5 — Write the Challenge.java

Create `src/main/java/challenges/_<NN>_<slug>/Challenge.java` with only the minimum boilerplate:

```java
package challenges._<NN>_<slug>;

public class Challenge {

    // TODO: implement

    public static void main(String[] args) {
        // use main to test your implementation with hardcoded values
    }
}
```

Rules:
- Do NOT implement any logic
- Do NOT add helper methods, fields, or classes beyond the comment placeholders
- Do NOT add Scanner or any console input
- The user decides how to structure their solution

## Step 6 — Confirm

Tell the user:

```
Challenge created: src/main/java/challenges/_<NN>_<slug>/

Files:
  README.md      — description and requirements
  Challenge.java — ready to implement

When done: /review-submission challenges/_<NN>_<slug>
```

---
name: new-challenge
description: Generates a new practical coding challenge (interview-style, not LeetCode) and creates README.md + Challenge.java skeleton in the challenges/ folder.
argument-hint: normal-challenge
disable-model-invocation: true
allowed-tools: Read Write Glob
---

## Goal

Generate a new practical coding challenge — something concrete and fun, like "create a rock-paper-scissors game" or "build a ticket queue system" — and write the files to the `challenges/` folder. Never implement the solution.

## Step 1 — Determine the next challenge number

List all existing directories inside `challenges/` using Glob pattern `challenges/_*/`.

Count how many exist to determine the next sequential number (e.g., if there are 3, the next is `_04`).

## Step 2 — Invent a challenge

Pick a domain and scenario that is:
- **Practical and concrete** — something a dev would actually build (game logic, small tools, simulations, queue systems, simple CLIs)
- **Not LeetCode** — no pure algorithm problems (sorting, graph traversal, DP). Focus on real-world logic.
- **Interview-appropriate** — completable in 30–90 minutes, tests object modeling and logic
- **Different from all existing challenges** — read the existing `README.md` files in `challenges/` to avoid repeating domains

Good examples: jokenpô, calculadora de gorjeta, sistema de senhas de banco, caixa registradora simples, gerador de senha aleatória, conversor de temperatura, estacionamento simples, placar de tênis, máquina de vending, validador de CPF.

Bad examples: binary search, merge sort, Fibonacci (nth), shortest path.

## Step 3 — Derive the folder name

From the challenge title, create a lowercase slug with underscores. Examples:
- "Jokenpô simples" → `jokenpo_simples`
- "Fila de atendimento" → `fila_de_atendimento`
- "Caixa registradora" → `caixa_registradora`

Full folder path: `challenges/_<NN>_<slug>/` where `<NN>` is the two-digit number from Step 1.

## Step 4 — Write the README.md

Create `challenges/_<NN>_<slug>/README.md` with this structure:

```markdown
# <Challenge title>

<1–2 sentences describing the scenario in plain Portuguese. Make it engaging.>

## O que implementar

- <Requirement 1 — concrete and specific>
- <Requirement 2>
- <Requirement 3>
- <Requirement 4 if needed>

## Exemplo de uso

```
<Short example showing expected input → output or interaction>
```

## Bônus (opcional)

- <One optional extra that makes it more interesting — not required>
- <Another bonus if applicable>
```

Rules for the README:
- Write in Portuguese
- Requirements should be unambiguous — the user should not have to guess what is expected
- Do not hint at the implementation approach or data structures to use
- The example must be concrete (actual values, not pseudocode)

## Step 5 — Write the Challenge.java

Create `challenges/_<NN>_<slug>/Challenge.java` with only the minimum boilerplate:

```java
public class Challenge {

    public static void main(String[] args) {
        // TODO: implement
    }
}
```

Rules:
- Do NOT implement any logic
- Do NOT add helper methods, fields, or classes
- Do NOT add comments explaining the challenge — the README does that
- The user decides how to structure the code

## Step 6 — Confirm

Tell the user:

```
Challenge created: challenges/_<NN>_<slug>/

Files:
  README.md    — description and requirements
  Challenge.java — ready to implement

When done: /review-submission challenges/_<NN>_<slug>
```

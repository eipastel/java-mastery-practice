# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Build and run

```bash
# Compilar
mvn compile

# Executar uma classe específica
mvn exec:java -Dexec.mainClass="block1_base._01_memory._01_stack_vs_heap.Exercise"

# Compilar e executar
mvn compile exec:java -Dexec.mainClass="block1_base._01_memory._01_stack_vs_heap.Exercise"
```

Requer Java 21+ com preview features habilitadas (`--enable-preview` já está configurado no `pom.xml`).

## Estrutura dos exercícios

Cada exercício vive em `src/main/java/<block>/<topic>/<subtopic>/` com dois arquivos:
- `README.md` — explicação do conceito e descrição do que implementar
- `Exercise.java` — scaffold com `TODO`s para preencher

O package Java espelha o path do diretório com underscores: `block1_base._01_memory._01_stack_vs_heap`.

### Blocos

| Diretório | Conteúdo |
|---|---|
| `block1_base/` | Fundamentos: memória, coleções, exceções, generics |
| `block2_modern/` | Java moderno: lambdas, streams, records, testes, clean code |
| `block3_backend/` | Backend: banco de dados, Spring, arquitetura |
| `block4_advanced/` | Avançado: concorrência, JVM, performance, segurança, reflexão |

## Skills disponíveis

Este repositório tem quatro skills customizadas que cobrem o ciclo de estudo:

```
/generate-challenge <path> [same|harder|easier]
```
Gera uma variação do exercício com domínio diferente — mesma estrutura, mesmo conceito. Escreve o código no stdout sem sobrescrever o arquivo original.

```
/review-submission <path>
```
Analisa a solução do usuário em três dimensões: correção conceitual, correção funcional, e qualidade de código. Nunca reescreve o código.

```
/tutor-mode <path>
```
Tutor socrático: lê o estado atual do exercício e guia com perguntas e dicas mínimas, sem entregar a resposta.

```
/new-challenge normal-challenge
```
Gera um desafio prático novo (estilo entrevista, não LeetCode) e cria os arquivos `README.md` + `Challenge.java` em `challenges/`. Nunca implementa a solução.

**Formato do path para as skills de exercício:** `block1-base/01-memory/01-stack-vs-heap` (com hífens, não underscores).

## Challenges

A pasta `challenges/` contém desafios práticos abertos — diferentes dos exercícios conceituais. Cada challenge tem:
- `README.md` — descrição, requisitos, exemplo e bônus
- `Challenge.java` — boilerplate mínimo para o usuário preencher

Para fazer review de um challenge: `/review-submission challenges/<nome-do-challenge>`

## Convenções do repositório

- Os `Exercise.java` com apenas `TODO`s ainda não foram resolvidos — não preencher sem pedido explícito do usuário.
- Ao gerar variações com `/generate-challenge`, o arquivo original **nunca** deve ser sobrescrito; o código gerado vai para o stdout.
- Ao fazer review com `/review-submission`, nunca colar uma versão corrigida do código — apenas indicar onde melhorar.
- O `Challenge.java` em `challenges/` nunca deve ser preenchido pela skill — apenas pelo usuário.

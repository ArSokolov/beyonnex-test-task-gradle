# beyonnex-test-task-gradle

This is a solution to the home test task for the Beyonnex company.

It implements simple anagram checking and finding functionality.

It uses: Kotlin, JUnit 5, Ktor.

## Task

The task was originally formulated as:

> Write a Java program that provides these 2 features:
>1. Checks if two texts are anagrams of each other.
>2. Makes available the list of anagrams found for a given String
>
>For feature #1: Please follow the definition described in the english wikipedia page for anagram.  
> For feature #2: There's no need for storage, nor interactivity, the results can be valid for a single run.
> Given these hypothetical invocations of the feature#1 function -> f1(A, B), f1(A, C), f1(A, D)-
> *IF* A, B and D are anagrams
>- f2(A) should return [B, D]
>- f2(B) should return [A, D]
>- f2(C) should return []
>
>Feel free to use your favorite:
>- IDE
>- Language: the solution has to be either Java 8+ or Kotlin
>- Code hosting: the solution must be publicly accessible
>- You can prioritize however you like (performance, readability, extensibility,…).
>
>P.S. Googling is a good thing :-)

## Comment on the solution

- I treated this task as a pet project so there are some experimentation leftovers that I didn't clean up.
- Currently, strings with the same letter order, but different letter cases or non-letter characters treated as
  anagrams, not the same words.
- Probably would be a good idea to extract into 1 place all logic of how we define and treat anagrams (non-letter
  characters filtering, normalization, anagrams equality). Currently, it is spread through different places.
package sokolov.beyonnex.testtask

import beyonnex.sokolov.plugins.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

/*
Write a Java/Kotlin program that provides these 2 features:

1. Checks if two texts are anagrams of each other.
2. Makes available the list of anagrams found for a given String

For feature #1: Please follow the definition described in the english wikipedia page for anagram.
For feature #2: There's no need for storage, nor interactivity, the results can be valid for a single run.
Given these hypothetical invocations of the feature#1 function -> f1(A, B), f1(A, C), f1(A, D)-
*IF* A, B and D are anagrams
- f2(A) should return [B, D]
- f2(B) should return [A, D]
- f2(C) should return []

Feel free to use your favorite:
- Code hosting: the solution must be publicly accessible
- You can prioritize however you like (performance, readability, extensibility,…).
*/

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    configureSerialization()
    configureHTTP()
    configureAdministration()
    configureRouting()
}



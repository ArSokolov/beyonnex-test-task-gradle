import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.collections.shouldContainExactly
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class CoreAppTest {

    // Given these hypothetical invocations of the feature#1 function -> f1(A, B), f1(A, C), f1(A, D)-
    // *IF* A, B and D are anagrams
    //  - f2(A) should return [B, D]
    //  - f2(B) should return [A, D]
    //  - f2(C) should return []
    @Test
    fun `scenario from the task description`() {
        val app = CoreApp.create()

        val a = "ABC"
        val b = "CBA"
        val c = "AAAAA"
        val d = "BCA"

        assertTrue(app.checkIsAnagram(a, b))
        assertFalse(app.checkIsAnagram(a, c))
        assertTrue(app.checkIsAnagram(a, d))

        app.findAnagramsFor(a).shouldContainExactly(setOf(b,d))
        app.findAnagramsFor(b).shouldContainExactly(setOf(a,d))
        app.findAnagramsFor(c).shouldBeEmpty()
    }
}
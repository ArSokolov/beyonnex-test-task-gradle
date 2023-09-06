import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.collections.shouldNotContain
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.assertDoesNotThrow
import java.lang.Exception

class AnagramRepoInMemoryTest {

    @Test
    fun `two anagrams are saved`() {
        val repo = AnagramRepoInMemory()

        val anagrams = listOf("C_B_A", "C_A_B")

        repo.save(anagrams)

        val first = anagrams.first()
        val others = anagrams.filter { it != first }

        repo.get(first).shouldContainExactly(others)
    }

    @Test
    fun `a few sequential saves of the same anagrams requested together`() {
        val repo = AnagramRepoInMemory()

        val anagrams1 = listOf("C_B_A", "C_A_B")
        val anagrams2 = listOf("A_B_C", "C_A_B")
        val anagrams3 = listOf("A_B_C", "B_A_C")

        repo.save(anagrams1)
        repo.save(anagrams2)
        repo.save(anagrams3)

        val first = anagrams1.first()
        val others = (anagrams1 + anagrams2 + anagrams3).filter { it != first }.toSet()

        repo.get(first).shouldContainExactly(others)
    }

    @Test
    fun `empty collection save is ignored`() {
        val repo = AnagramRepoInMemory()

        assertDoesNotThrow { repo.save(emptyList()) }
    }

    @Test
    fun `single element collection doesn't throw`() {
        val repo = AnagramRepoInMemory()

        assertDoesNotThrow { repo.save(listOf("abcd")) }
    }


    @Test
    fun `single element collection is not saved`() {
        val repo = AnagramRepoInMemory()

        val singleElementToSave = "A_B_C"
        val anagrams = listOf("C_B_A", "C_A_B")

        repo.save(anagrams)

        try {
            repo.save(listOf(singleElementToSave))
        } catch (_: Exception) {
        }

        repo.get(anagrams.first()).shouldNotContain(singleElementToSave)
        repo.get(singleElementToSave).shouldBeEmpty()
    }

    @Test
    fun `anagrams recognition is case insensitive and ignore non-letter characters`() {
        val repo = AnagramRepoInMemory()

        val anagrams1 = listOf("c_B_A", "C_A_B")
        val anagrams2 = listOf("A_B_C", "C_a   _B")
        val anagrams3 = listOf("A_B_C", "B_A_c")

        repo.save(anagrams1)
        repo.save(anagrams2)
        repo.save(anagrams3)

        val first = anagrams1.first()
        val others = (anagrams1 + anagrams2 + anagrams3).filter { it != first }.toSet()

        repo.get(first).shouldContainExactly(others)
    }
}
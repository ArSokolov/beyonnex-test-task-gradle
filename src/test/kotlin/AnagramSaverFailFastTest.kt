import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Nested

class AnagramSaverFailFastTest {

    private fun createTestObject() = AnagramSaverFailFast(AnagramRepoInMemory())

    @Nested
    inner class `verifyAnagramAndSave()` {
        @Test
        fun `returns true with anagrams`() {
            val anagramSaver = createTestObject()
            val first = "A_B_C"
            val second = "C_B_A"

            assertTrue(anagramSaver.verifyAnagramAndSave(first, second))
        }

        @Test
        fun `returns false with non-anagram texts`() {
            val anagramSaver = createTestObject()
            val first = "A_B_C"
            val second = "C_B_AA"

            assertFalse(anagramSaver.verifyAnagramAndSave(first, second))
        }

        @Test
        fun `returns true with anagrams case insensitive`() {
            val anagramSaver = createTestObject()
            val first = "A_B_C"
            val second = "C_b_a"

            assertTrue(anagramSaver.verifyAnagramAndSave(first, second))
        }

        @Test
        fun `returns true with anagrams ignore non-letter characters`() {
            val anagramSaver = createTestObject()
            val first = "A_B_C"
            val second = "C_b__  a"

            assertTrue(anagramSaver.verifyAnagramAndSave(first, second))
        }

        @Test
        fun `returns true for anagrams with same letter order but different non-letter characters`() {
            val anagramSaver = createTestObject()
            val first = "A_B_C"
            val second = "A_B__C"

            assertTrue(anagramSaver.verifyAnagramAndSave(first, second))
        }

        @Test
        fun `returns false for same texts`() {
            val anagramSaver = createTestObject()
            val first = "A_B_C"

            assertFalse(anagramSaver.verifyAnagramAndSave(first, first))
        }

    }
}
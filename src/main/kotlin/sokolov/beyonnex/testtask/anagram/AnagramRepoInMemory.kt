package sokolov.beyonnex.testtask.anagram

import kotlinx.collections.immutable.PersistentSet
import kotlinx.collections.immutable.persistentSetOf
import java.util.concurrent.ConcurrentHashMap


class AnagramRepoInMemory : AnagramRepo {

    private val store: MutableMap<String, PersistentSet<String>> = ConcurrentHashMap()

    // There is no additional validation because current scenario contains only 1 use case and we optimized for that.
    override fun save(anagrams: Collection<String>) {
        if (anagrams.size < 2) return

        val normalized = normalize(anagrams.first())
        store.compute(normalized) { _, currSet ->
            (currSet ?: persistentSetOf())
                .addAll(anagrams)
        }
    }

    override fun get(text: String): Collection<String> {
        val normalized = normalize(text)
        return store[normalized]
            ?.takeIf { it.contains(text) }
            ?.remove(text) ?: persistentSetOf()
    }

    private fun normalize(leftText: String): String {
        return leftText.filter { it.isLetter() }.map { it.lowercaseChar() }.sorted().joinToString()
    }

}
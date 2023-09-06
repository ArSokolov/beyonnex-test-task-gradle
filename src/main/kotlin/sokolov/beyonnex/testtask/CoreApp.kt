package sokolov.beyonnex.testtask

import sokolov.beyonnex.testtask.anagram.AnagramRepo
import sokolov.beyonnex.testtask.anagram.AnagramRepoInMemory
import sokolov.beyonnex.testtask.anagram.AnagramSaver
import sokolov.beyonnex.testtask.anagram.AnagramSaverFailFast

// TODO: fix anagram detection when letters order the same and non-letters are different.

class CoreApp(private val anagramSaver: AnagramSaver, private val anagramRepo: AnagramRepo) {

    companion object {
        fun create(): CoreApp {
            val repo = AnagramRepoInMemory()
            return CoreApp(AnagramSaverFailFast(repo), repo)
        }
    }

    // feature #1
    fun checkIsAnagram(leftText: String, rightText: String): Boolean =
        anagramSaver.verifyAnagramAndSave(leftText, rightText)

    // feature #2
    fun findAnagramsFor(str: String): Collection<String> =
        anagramRepo.get(str)
}


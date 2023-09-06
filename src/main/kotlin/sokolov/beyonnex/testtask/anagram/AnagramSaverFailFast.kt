package sokolov.beyonnex.testtask.anagram

import java.util.HashMap

class AnagramSaverFailFast(private val anagramRepo: AnagramRepo) : AnagramSaver {
    override fun verifyAnagramAndSave(leftText: String, rightText: String): Boolean {
        val countersMap = HashMap<Char, Int>()
        leftText.forEach { letter ->
            val lowercase = letter.lowercaseChar()
            if (lowercase.isLetter())
                countersMap.compute(lowercase) { _, curr ->
                    (curr ?: 0).inc()
                }
        }

        rightText.forEach { letter ->
            val lowercase = letter.lowercaseChar()
            if (lowercase.isLetter()) {
                val curr = countersMap.compute(lowercase) { _, curr ->
                    (curr ?: 0).dec()
                }
                if (curr == null || curr < 0)
                    return false
            }
        }
        val nonZeroCounter = countersMap.values.find { it != 0 }

        if (nonZeroCounter != null || leftText == rightText)
            return false


        anagramRepo.save(listOf(leftText, rightText))

        return true
    }
}
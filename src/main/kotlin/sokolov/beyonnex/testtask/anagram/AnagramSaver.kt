package sokolov.beyonnex.testtask.anagram

interface AnagramSaver {
    fun verifyAnagramAndSave(leftText: String, rightText: String): Boolean
}


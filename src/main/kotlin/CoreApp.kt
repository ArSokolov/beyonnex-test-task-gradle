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


interface AnagramRepo {
    fun save(anagrams: Collection<String>)
    fun get(text: String): Collection<String>
}
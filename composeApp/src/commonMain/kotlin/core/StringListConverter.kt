package core

expect class StringListConverter {
    fun fromString(value: String): List<String>
    fun fromList(list: List<String>): String
}
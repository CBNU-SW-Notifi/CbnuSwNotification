package core

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

actual class StringListConverter {
    actual fun fromString(value: String): List<String> {
        return Json.decodeFromString(value)
    }

    actual fun fromList(list: List<String>): String {
        return Json.encodeToString(list)
    }
}
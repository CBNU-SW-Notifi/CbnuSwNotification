package database.util

import androidx.room.TypeConverter
import data.model.job_hunt.AttachedFile
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class Converters {
    @TypeConverter
    fun fromString(value: String): List<String> {
        return Json.decodeFromString(value)
    }

    @TypeConverter
    fun fromList(list: List<String>): String {
        return Json.encodeToString(list)
    }

    @TypeConverter
    fun fromAttachedFileList(value: String): List<AttachedFile> {
        return Json.decodeFromString(value)
    }

    @TypeConverter
    fun attachedFileListToString(list: List<AttachedFile>): String {
        return Json.encodeToString(list)
    }
}
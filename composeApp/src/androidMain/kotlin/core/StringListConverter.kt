package core

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/*
actual class StringListConverter {
    @TypeConverter
    actual fun fromString(value: String): List<String> {
        val listType = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    actual fun fromList(list: List<String>): String {
        return Gson().toJson(list)
    }
}*/

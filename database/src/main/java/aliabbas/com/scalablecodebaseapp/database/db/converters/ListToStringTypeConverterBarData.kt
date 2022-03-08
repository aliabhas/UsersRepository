package aliabbas.com.scalablecodebaseapp.database.db.converters

import aliabbas.com.scalablecodebaseapp.model.repository_commit.BarData
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


/**
 * Created By Ali Abbas on on 29,December,2021
 * This Class is used for
 *
 */
class ListToStringTypeConverterBarData {
    @TypeConverter
    fun fromString(value: String): List<BarData> {
        val type = object : TypeToken<List<BarData>>() {}.type
        return Gson().fromJson(value, type)
    }

    @TypeConverter
    fun fromArrayList(list: List<BarData>): String {
        val type = object : TypeToken<List<BarData>>() {}.type
        return Gson().toJson(list, type)
    }
}
package aliabbas.com.scalablecodebaseapp.database.db.converters

import aliabbas.com.scalablecodebaseapp.model.user_repository.OwnerModel
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


/**
 * Created By Ali Abbas on on 29,December,2021
 * This Class is used for
 *
 */
class ObjectToStringTypeConverter {
    @TypeConverter
    fun fromString(value: String): OwnerModel {
        val type = object : TypeToken<OwnerModel>() {}.type
        return Gson().fromJson(value, type)
    }

    @TypeConverter
    fun fromArrayList(list: OwnerModel): String {
        val type = object : TypeToken<OwnerModel>() {}.type
        return Gson().toJson(list, type)
    }
}
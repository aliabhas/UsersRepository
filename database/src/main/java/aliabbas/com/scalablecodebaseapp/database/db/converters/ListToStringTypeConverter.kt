package aliabbas.com.scalablecodebaseapp.database.db.converters

import aliabbas.com.scalablecodebaseapp.model.user_repository.UserRepositoriesModel
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


/**
 * Created By Ali Abbas on on 29,December,2021
 * This Class is used for
 *
 */
class ListToStringTypeConverter {
    @TypeConverter
    fun fromString(value: String): List<UserRepositoriesModel> {
        val type = object : TypeToken<List<UserRepositoriesModel>>() {}.type
        return Gson().fromJson(value, type)
    }

    @TypeConverter
    fun fromArrayList(list: List<UserRepositoriesModel>): String {
        val type = object : TypeToken<List<UserRepositoriesModel>>() {}.type
        return Gson().toJson(list, type)
    }
}
package aliabbas.com.scalablecodebaseapp.database.db.tables

import aliabbas.com.scalablecodebaseapp.database.db.converters.ListToStringTypeConverter
import aliabbas.com.scalablecodebaseapp.model.user_repository.UserRepositoriesModel
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

/**
 * Created By Ali Abbas on on 29,December,2021
 * This Class is used for
 *
 */
@Entity
data class UserRepositories(
    @PrimaryKey
    val userName: String,
    @TypeConverters(ListToStringTypeConverter::class)
    val listOfRepositories: List<UserRepositoriesModel>?
)
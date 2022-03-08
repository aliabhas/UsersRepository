package aliabbas.com.scalablecodebaseapp.database.db.tables

import aliabbas.com.scalablecodebaseapp.database.db.converters.ObjectToStringTypeConverter
import aliabbas.com.scalablecodebaseapp.model.user_repository.OwnerModel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName

/**
 * Created By Ali Abbas on on 08,March,2022
 * This Class is used for
 */
@kotlinx.parcelize.Parcelize
@Entity
data class UserRepositoriesTable(
    @PrimaryKey
    @SerializedName("full_name") val fullName: String = "",
    val name: String? = null,
    @TypeConverters(ObjectToStringTypeConverter::class)
    val owner: @kotlinx.parcelize.RawValue OwnerModel? = null,
    val description: String? = null,
    var isFavourite: Int = 0,
    val hideUnHideRepository: Int = 0
) : Parcelable
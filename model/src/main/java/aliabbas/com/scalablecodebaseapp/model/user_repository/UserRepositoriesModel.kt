package aliabbas.com.scalablecodebaseapp.model.user_repository

import android.os.Parcelable
import com.google.gson.annotations.SerializedName

/**
 * Created By Ali Abbas
 * This Class is used for
 *
 */
@kotlinx.parcelize.Parcelize
data class UserRepositoriesModel(
    @SerializedName("full_name") val fullName: String? = null,
    val name: String? = null,
    val owner: @kotlinx.parcelize.RawValue OwnerModel? = null,
    val description: String? = null,
    val isFavourite: Int = 0
) : Parcelable

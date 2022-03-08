package aliabbas.com.scalablecodebaseapp.model.user_repository

import android.os.Parcelable
import com.google.gson.annotations.SerializedName

/**
 * Created By Ali Abbas
 * This Class is used for
 *
 */
@kotlinx.parcelize.Parcelize
data class OwnerModel(
    val url: String? = null,
    @SerializedName("avatar_url") val avatarUrl: String? = null,
) : Parcelable
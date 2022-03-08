package aliabbas.com.userrepositories.ui.compose_components.util

import androidx.compose.ui.graphics.Color

/**
 * Created By Ali Abbas on on 02,January,2022
 * This Class is used for
 *
 */
object HexToJetpackColor {
    fun getColor(colorString: String): Color {
        return Color(android.graphics.Color.parseColor("#$colorString"))
    }
}
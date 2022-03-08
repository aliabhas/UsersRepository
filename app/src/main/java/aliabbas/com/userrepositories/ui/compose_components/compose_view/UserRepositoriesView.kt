package aliabbas.com.userrepositories.ui.compose_components.compose_view

import aliabbas.com.userrepositories.ui.compose_components.util.DEFAULT_REPOSITORY_IMAGE
import aliabbas.com.userrepositories.ui.compose_components.util.HexToJetpackColor
import aliabbas.com.userrepositories.ui.compose_components.util.loadPicture
import aliabbas.com.scalablecodebaseapp.database.db.tables.UserRepositoriesTable
import aliabbas.com.userrepositories.R
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@Composable
fun UserRepositoriesView(
    userRepository: UserRepositoriesTable,
    onClick: () -> Unit,
    favouriteClick: () -> Unit,
    hideClicked: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .padding(
                bottom = 6.dp,
                top = 6.dp,
            )
            .fillMaxWidth()
            .clickable(onClick = onClick),
        elevation = 8.dp,
        backgroundColor = HexToJetpackColor.getColor("474747")
    ) {
        var isFavorite = remember { mutableStateOf(userRepository.isFavourite == 1) }
        Column {
            val image = loadPicture(
                url = userRepository.owner?.avatarUrl!!,
                defaultImage = DEFAULT_REPOSITORY_IMAGE
            ).value
            image?.let { img ->
                Log.i("image called", "image called: $userRepository.name!!")
                Image(
                    bitmap = img.asImageBitmap(),
                    contentDescription = "Repository Image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    contentScale = ContentScale.Crop,
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp, bottom = 12.dp, start = 8.dp, end = 8.dp)
            ) {
                Text(
                    text = userRepository.name!!,
                    modifier = Modifier
                        .fillMaxWidth(),
                    style = MaterialTheme.typography.h6,
                    color = Color.White,
                    maxLines = 1
                )
                val rank = userRepository.fullName!!
                Text(
                    text = rank,
                    modifier = Modifier
                        .fillMaxWidth(),
                    style = MaterialTheme.typography.caption,
                    color = Color.White,
                    maxLines = 1
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                var favoriteIcon = painterResource(id = R.drawable.ic_baseline_favorite_border_24)
                if (isFavorite.value) {
                    favoriteIcon = painterResource(id = R.drawable.ic_baseline_favorite_24)
                }
                Image(
                    painter = favoriteIcon,
                    contentDescription = "Favourite",
                    modifier = Modifier
                        .height(40.dp)
                        .width(40.dp)
                        .align(alignment = Alignment.CenterVertically)
                        .clickable(onClick = {
                            isFavorite.value = !isFavorite.value
                            favouriteClick()
                        })
                )
                if (userRepository.hideUnHideRepository == 0) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_baseline_remove_circle_24),
                        contentDescription = "Hide",
                        modifier = Modifier
                            .height(40.dp)
                            .width(40.dp)
                            .align(alignment = Alignment.CenterVertically)
                            .clickable(onClick = hideClicked)
                    )
                }
            }
        }
    }
}



package aliabbas.com.userrepositories.ui.compose_components.compose_view

import aliabbas.com.userrepositories.custom_views.BarChartView
import aliabbas.com.userrepositories.ui.compose_components.util.HexToJetpackColor
import aliabbas.com.scalablecodebaseapp.model.repository_commit.BarData
import aliabbas.com.userrepositories.shared.result.ApiResponse
import aliabbas.com.scalablecodebaseapp.database.db.tables.UserRepositoriesTable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView

/**
 * Created By Ali Abbas on on 04,January,2022
 * This Class is used for
 *
 */
@Composable
fun addViewToContent(
    barChartView: BarChartView,
    userRepositoriesModel: UserRepositoriesTable,
    listRepositoryCommits: ApiResponse
) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .weight(5F)
                .background(HexToJetpackColor.getColor("4f97a3"))
                .padding(16.dp)
        ) {
            Text(text = "Commits:")
            when (listRepositoryCommits) {
                is ApiResponse.ApiResponseSuccess -> {
                    val listOfCommitsByMonths =
                        listRepositoryCommits.responseData as ArrayList<*>
                    val listBarData: Array<BarData> =
                        listOfCommitsByMonths.toArray(arrayOfNulls(listOfCommitsByMonths.size))
                    barChartView.setBarDataToDisplay(
                        listBarData
                    )
                    AndroidView(factory = { barChartView })
                }
                else -> {}
            }
        }
        Text(
            text = userRepositoriesModel.name!!,
            modifier = Modifier
                .weight(1F)
                .align(Alignment.CenterHorizontally),
            style = MaterialTheme.typography.h6,
            color = Color.White,
            maxLines = 2
        )
        Text(
            text = userRepositoriesModel.fullName!!,
            modifier = Modifier
                .weight(1F),
            style = MaterialTheme.typography.caption,
            color = Color.White
        )
        val description =
            if (userRepositoriesModel.description != null) userRepositoriesModel.description else ""
        Text(
            text = description!!,
            modifier = Modifier
                .weight(1F),
            style = MaterialTheme.typography.caption,
            color = Color.White
        )
        Text(
            text = userRepositoriesModel.owner!!.url!!,
            modifier = Modifier
                .weight(1F),
            style = MaterialTheme.typography.caption,
            color = Color.White
        )
    }
}
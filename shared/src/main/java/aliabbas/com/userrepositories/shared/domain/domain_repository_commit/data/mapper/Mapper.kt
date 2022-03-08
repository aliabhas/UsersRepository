package aliabbas.com.scalablecodebaseapp.shared.domain.domain_repository_commit.data.mapper

import aliabbas.com.scalablecodebaseapp.database.db.tables.RepositoryCommits
import aliabbas.com.scalablecodebaseapp.model.repository_commit.BarData
import aliabbas.com.scalablecodebaseapp.model.repository_commit.RepositoryCommitsDetailModel
import android.annotation.SuppressLint
import java.util.*
import javax.inject.Inject

/**
 * Created By Ali Abbas on on 29,December,2021
 * This Class is used for
 *
 */
class Mapper @Inject constructor() {
    /**
     * This function is responsible for segregating user's repository commits by month's
     */
    @SuppressLint("SimpleDateFormat")
    fun sortDatesForCommits(
        listRepositoryCommitsDetailModel: List<RepositoryCommitsDetailModel>
    ): List<BarData> {
        //Assigning months to repository by getting the details from commit dates
        return listRepositoryCommitsDetailModel.map {
            val commitDate: Date = it.commit?.committer?.date!!
            val c: Calendar = Calendar.getInstance()
            c.timeInMillis = commitDate.time
            val month: String =
                c.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault())!!
            it.commit!!.committer!!.months = month
            it
        }.groupBy {
            it.commit!!.committer!!.months
        }.map { repositoryCommitsDetailModel ->
            BarData(
                repositoryCommitsDetailModel.key!!.substring(0, 3),
                repositoryCommitsDetailModel.value.size
            )
        }
    }

    fun toListOfCommitDetails(
        repositoryName: String,
        listOfCommitsByMonths: List<BarData>
    ): RepositoryCommits {
        return RepositoryCommits(
            repositoryName = repositoryName,
            listOfCommitsByMonths = listOfCommitsByMonths
        )
    }
}
package aliabbas.com.scalablecodebaseapp.shared.domain.domain_repository_commit.data.datasources.remote

import aliabbas.com.scalablecodebaseapp.model.repository_commit.BarData
import aliabbas.com.scalablecodebaseapp.model.repository_commit.RepositoryCommitsDetailModel
import aliabbas.com.scalablecodebaseapp.shared.app_service_calls.Api
import aliabbas.com.userrepositories.shared.result.ApiResponse
import aliabbas.com.userrepositories.shared.util.Constants.COMMITS
import aliabbas.com.userrepositories.shared.util.Constants.REPO_COMMITS_URL
import android.annotation.SuppressLint
import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

/**
 * DataSource class for getting all commit related details for user repository.
 *
 */
class RepositoryCommitsRemoteDataSourceImpl @Inject constructor(
    var api: Api
) : RepositoryCommitsRemoteDataSource {

    override fun getCommitDetailsRepository(repositoryName: String) =
        repositoryCommitDetailsByMonths(repositoryName)

    override suspend fun getCommitDetailsRepositoryNew(repositoryName: String): ApiResponse {
        val repositoryCommitDetailUrl = REPO_COMMITS_URL + repositoryName + COMMITS
        val repositoryCommitData =
            api.getRepositoryCommitDetailsCall(repositoryCommitDetailUrl)
        return ApiResponse.ApiResponseSuccess(repositoryCommitData)
    }

    /**
     * This function gets all commit related details for user repository and sort it by month.
     *
     */
    private fun repositoryCommitDetailsByMonths(
        repositoryName: String?
    ): Flow<ApiResponse> = flow {
        try {
            val repositoryCommitDetailUrl = REPO_COMMITS_URL + repositoryName + COMMITS
            emit(ApiResponse.ProgressLoadingState)
            val repositoryCommitData =
                api.getRepositoryCommitDetailsCall(repositoryCommitDetailUrl)
            val sortDatesForCommits: ArrayList<BarData> =
                sortDatesForCommits(repositoryCommitData)
            emit(ApiResponse.ApiResponseSuccess(sortDatesForCommits))
            Log.i("commit repo", "commit repo: start")
        } catch (ex: Exception) {
            emit(ApiResponse.ApiFailure("Exception occur: ${ex.message}"))
        }
    }

    /**
     * This function is responsible for segregating user's repository commits by month's
     */
    @SuppressLint("SimpleDateFormat")
    fun sortDatesForCommits(
        listRepositoryCommitsDetailModel: List<RepositoryCommitsDetailModel>
    ): ArrayList<BarData> {
        //Assigning months to repository by getting the details from commit dates
        for (repositoryCommitsDetailModel in listRepositoryCommitsDetailModel) {
            val commitDate: Date = repositoryCommitsDetailModel.commit!!.committer!!.date!!
            val c: Calendar = Calendar.getInstance()
            c.timeInMillis = commitDate.time
            val month: String =
                c.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault())!!
            repositoryCommitsDetailModel.commit!!.committer!!.months = month
        }

        // Always try to avoid non null assertions.

        //Applied group by based on the months, as I have assign in above code.
        val groupCommitByMonths =
            listRepositoryCommitsDetailModel.groupBy { it.commit!!.committer!!.months }
        val arrayList = ArrayList<BarData>()
        for (groupKeyValue in groupCommitByMonths) {
            val barData =
                groupKeyValue.key?.substring(0, 3)
                    ?.let { BarData(it, groupKeyValue.value.size) }
            barData?.let { arrayList.add(it) }
        }
        return arrayList
    }

}
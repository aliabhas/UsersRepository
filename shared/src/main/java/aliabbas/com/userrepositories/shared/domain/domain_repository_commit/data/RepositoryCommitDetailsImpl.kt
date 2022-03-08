package aliabbas.com.scalablecodebaseapp.shared.domain.domain_repository_commit.data

import aliabbas.com.scalablecodebaseapp.model.repository_commit.RepositoryCommitsDetailModel
import aliabbas.com.userrepositories.shared.domain.domain_repository_commit.TestInf
import aliabbas.com.scalablecodebaseapp.shared.domain.domain_repository_commit.data.datasources.local.RepositoriesCommitLocalDataSource
import aliabbas.com.scalablecodebaseapp.shared.domain.domain_repository_commit.data.datasources.remote.RepositoryCommitsRemoteDataSource
import aliabbas.com.scalablecodebaseapp.shared.domain.domain_repository_commit.data.mapper.Mapper
import aliabbas.com.userrepositories.shared.domain.domain_repository_commit.domain.repository.CommitRepositoryRepository
import aliabbas.com.userrepositories.shared.result.ApiResponse
import aliabbas.com.userrepositories.shared.util.Util
import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 * Repository class for getting all commit related details for user repository.
 *
 */
class RepositoryCommitDetailsImpl @Inject constructor(
    val context: Context,
    val repository: RepositoryCommitsRemoteDataSource,
    val repositoriesLocalDataSource: RepositoriesCommitLocalDataSource,
    val mapper: Mapper,
    val testInf: TestInf
) : CommitRepositoryRepository {

    override fun getCommitDetailsRepository(repositoryName: String): Flow<ApiResponse> =
        flow {
            var apiResponse: ApiResponse = ApiResponse.ProgressLoadingState
            emit(apiResponse)
            if (Util.isNetworkAvailable(context)) {
                apiResponse =
                    repository.getCommitDetailsRepositoryNew(repositoryName)
                when (apiResponse) {
                    is ApiResponse.ApiResponseSuccess -> {
                        val listRepositoryCommitsDetail =
                            apiResponse.responseData as MutableList<RepositoryCommitsDetailModel>
                        val listOfCommitsByMonth =
                            mapper.sortDatesForCommits(listRepositoryCommitsDetail)
                        emit(ApiResponse.ApiResponseSuccess(listOfCommitsByMonth))
                        repositoriesLocalDataSource
                            .insertRepositoriesCommitsDetails(
                                mapper.toListOfCommitDetails(
                                    repositoryName,
                                    listOfCommitsByMonth
                                )
                            )
                    }
                    else -> {}
                }
            } else {
                apiResponse =
                    repositoriesLocalDataSource.getRepositoriesCommits(repositoryName)?.run {
                        ApiResponse.ApiResponseSuccess(this.listOfCommitsByMonths!!)
                    } ?: ApiResponse.ApiFailure()
                emit(apiResponse)
            }

        }.flowOn(Dispatchers.IO)

    /*override fun getCommitDetailsRepository(repositoryName: String): Flow<ApiResponse> =
        flow {
            var apiResponse: ApiResponse = ApiResponse.ProgressLoadingState
            emit(apiResponse)
            if (Util.isNetworkAvailable(context)) {
                apiResponse =
                    repository.getCommitDetailsRepositoryNew(repositoryName)
                when (apiResponse) {
                    is ApiResponse.ApiResponseSuccess -> {
                        val listRepositoryCommitsDetail =
                            apiResponse.responseData as ArrayList<RepositoryCommitsDetailModel>
                        val listOfCommitsByMonth =
                            mapper.sortDatesForCommits(listRepositoryCommitsDetail)
                        emit(ApiResponse.ApiResponseSuccess(listOfCommitsByMonth))
                        repositoriesLocalDataSource
                            .insertRepositoriesCommitsDetails(
                                mapper.toListOfCommitDetails(
                                    repositoryName,
                                    listOfCommitsByMonth
                                )
                            )
                    }
                    else -> {}
                }
            } else {
                apiResponse =
                    repositoriesLocalDataSource.getRepositoriesCommits(repositoryName)?.run {
                        ApiResponse.ApiResponseSuccess(this.listOfCommitsByMonths!!)
                    } ?: ApiResponse.ApiFailure()
                emit(apiResponse)
            }

        }.flowOn(Dispatchers.IO)*/
}
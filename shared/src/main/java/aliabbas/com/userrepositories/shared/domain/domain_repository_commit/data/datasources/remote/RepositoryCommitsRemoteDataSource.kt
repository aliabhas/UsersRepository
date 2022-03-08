package aliabbas.com.scalablecodebaseapp.shared.domain.domain_repository_commit.data.datasources.remote

import aliabbas.com.userrepositories.shared.result.ApiResponse
import kotlinx.coroutines.flow.Flow

/**
 * Created By Ali Abbas on on 21,December,2021
 * This Class is used for
 *
 */
interface RepositoryCommitsRemoteDataSource {

    fun getCommitDetailsRepository(repositoryName: String): Flow<ApiResponse>
    suspend fun getCommitDetailsRepositoryNew(repositoryName: String): ApiResponse
}
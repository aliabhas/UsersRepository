package aliabbas.com.userrepositories.shared.domain.domain_repository_commit.domain.repository

import aliabbas.com.userrepositories.shared.result.ApiResponse
import kotlinx.coroutines.flow.Flow

/**
 * Created By Ali Abbas on on 20,December,2021
 * This Class is used for
 *
 */
interface CommitRepositoryRepository {
    fun getCommitDetailsRepository(repositoryName: String): Flow<ApiResponse>
}
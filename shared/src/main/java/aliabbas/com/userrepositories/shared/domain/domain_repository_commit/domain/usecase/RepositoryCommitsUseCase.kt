package aliabbas.com.userrepositories.shared.domain.domain_repository_commit.domain.usecase

import aliabbas.com.userrepositories.shared.result.ApiResponse
import kotlinx.coroutines.flow.Flow

/**
 * Created By Ali Abbas on on 30,December,2021
 * This Class is used for
 *
 */
interface RepositoryCommitsUseCase {
    suspend fun execute(repositoryName: String): Flow<ApiResponse>
}
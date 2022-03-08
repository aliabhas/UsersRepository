package aliabbas.com.userrepositories.shared.domain.domain_repository_commit.domain.usecase

import aliabbas.com.userrepositories.shared.domain.domain_repository_commit.domain.repository.CommitRepositoryRepository
import aliabbas.com.userrepositories.shared.result.ApiResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created By Ali Abbas on on 30,December,2021
 * This Class is used for
 *
 */
class RepositoryCommitsUseCaseImpl @Inject constructor(
    private val repositoryCommitDetails: CommitRepositoryRepository
) : RepositoryCommitsUseCase {
    override suspend fun execute(repositoryName: String): Flow<ApiResponse> {
        return repositoryCommitDetails.getCommitDetailsRepository(repositoryName)
    }
}
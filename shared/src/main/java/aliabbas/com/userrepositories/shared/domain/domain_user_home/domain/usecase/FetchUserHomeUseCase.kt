package aliabbas.com.userrepositories.shared.domain.domain_user_home.domain.usecase

import aliabbas.com.userrepositories.shared.result.ApiResponse
import kotlinx.coroutines.flow.Flow

interface FetchUserHomeUseCase {
    suspend fun execute(): Flow<ApiResponse>
}
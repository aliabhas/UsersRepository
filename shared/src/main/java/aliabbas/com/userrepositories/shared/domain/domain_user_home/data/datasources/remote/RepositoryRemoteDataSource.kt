package aliabbas.com.userrepositories.shared.domain.domain_user_home.data.datasources.remote

import aliabbas.com.userrepositories.shared.result.ApiResponse

/**
 * Created By Ali Abbas on on 21,December,2021
 * This Class is used for
 *
 */
interface RepositoryRemoteDataSource {

    suspend fun getListUserRepositories(): ApiResponse
}
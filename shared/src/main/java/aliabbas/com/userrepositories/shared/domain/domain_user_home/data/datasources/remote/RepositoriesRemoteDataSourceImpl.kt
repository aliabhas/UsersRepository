package aliabbas.com.userrepositories.shared.domain.domain_user_home.data.datasources.remote

import aliabbas.com.scalablecodebaseapp.shared.app_service_calls.Api
import aliabbas.com.userrepositories.shared.result.ApiResponse
import aliabbas.com.userrepositories.shared.util.Constants
import javax.inject.Inject

class RepositoriesRemoteDataSourceImpl @Inject constructor(
    var api: Api
) : RepositoryRemoteDataSource {

    override suspend fun getListUserRepositories(): ApiResponse {
        val listUserRepositories = api.getUserGithubRepositories(Constants.USER_REPO_LINK)
        return ApiResponse.ApiResponseSuccess(listUserRepositories)
    }
}
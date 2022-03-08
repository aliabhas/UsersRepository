package aliabbas.com.userrepositories.shared.domain.domain_user_home.data

import aliabbas.com.userrepositories.shared.domain.domain_user_home.data.datasources.local.RepositoriesLocalDataSource
import aliabbas.com.userrepositories.shared.domain.domain_user_home.data.datasources.remote.RepositoryRemoteDataSource
import aliabbas.com.userrepositories.shared.domain.domain_user_home.domain.mapper.UserRepositoriesMapper
import aliabbas.com.userrepositories.shared.domain.domain_user_home.domain.repository.UserRepository
import aliabbas.com.userrepositories.shared.result.ApiResponse
import aliabbas.com.userrepositories.shared.util.Util
import aliabbas.com.scalablecodebaseapp.database.db.tables.UserRepositoriesTable
import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject


// Repository can talk to two types of sources
// 1. local or 2. Remote
// here you have to define which data source repository is talking to
class UserRepositoriesRepositoryImpl @Inject constructor(
    private val remoteDataSource: RepositoryRemoteDataSource,
    private val localDataSource: RepositoriesLocalDataSource,
    @ApplicationContext val context: Context,
    val userRepositoriesMapper: UserRepositoriesMapper
) : UserRepository {

    @Suppress("UNCHECKED_CAST")
    override suspend fun getListUserRepositoriesLiveData(): ApiResponse {
        var apiResponse: ApiResponse
        if (Util.isNetworkAvailable(context)) {
            apiResponse = remoteDataSource.getListUserRepositories()
            when (apiResponse) {
                is ApiResponse.ApiResponseSuccess -> {
                    val list = apiResponse.responseData as List<UserRepositoriesTable>
                    localDataSource.insertUserRepositories(
                        list
                    )
                }
                else -> {}
            }
        }
        (localDataSource.getUserRepositories()?.run {
            ApiResponse.ApiResponseSuccess(this)
        } ?: ApiResponse.ApiFailure("No Data Found")).also { apiResponse = it }
        return apiResponse
    }

    override suspend fun makeRepositoryFavourite(userRepositoriesModel: UserRepositoriesTable): Int {
        return localDataSource.favouriteUserRepository(userRepositoriesModel)
    }

    override suspend fun hideUnHideRepository(userRepositoriesModel: UserRepositoriesTable): Int {
        return localDataSource.hideUnHideUserRepository(userRepositoriesModel)
    }
}
package aliabbas.com.userrepositories.shared.domain.domain_user_home.domain.repository

import aliabbas.com.userrepositories.shared.result.ApiResponse
import aliabbas.com.scalablecodebaseapp.database.db.tables.UserRepositoriesTable

/**
 * Created By Ali Abbas on on 20,December,2021
 * This Class is used for
 *
 */
interface UserRepository {
    suspend fun getListUserRepositoriesLiveData(): ApiResponse

    suspend fun makeRepositoryFavourite(userRepositoriesModel: UserRepositoriesTable): Int

    suspend fun hideUnHideRepository(userRepositoriesModel: UserRepositoriesTable): Int
}
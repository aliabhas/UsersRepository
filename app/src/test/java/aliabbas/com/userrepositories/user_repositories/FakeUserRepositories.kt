package aliabbas.com.userrepositories.user_repositories

import aliabbas.com.scalablecodebaseapp.database.db.tables.UserRepositoriesTable
import aliabbas.com.scalablecodebaseapp.model.user_repository.OwnerModel
import aliabbas.com.userrepositories.shared.domain.domain_user_home.domain.repository.UserRepository
import aliabbas.com.userrepositories.shared.result.ApiResponse
import kotlinx.coroutines.flow.flow

class FakeUserRepositories : UserRepository {
    var userRepositoriesDagger = UserRepositoriesTable(
        "AliAbbas", "Ali", OwnerModel("", ""),
        "", 0, 0
    )
    var userRepositoriesHilt = UserRepositoriesTable(
        "AliAbbas1", "Ali", OwnerModel("", ""),
        "", 0, 0
    )
    val listUserRepositories = listOf(
        userRepositoriesDagger, userRepositoriesHilt
    )

    override suspend fun getListUserRepositoriesLiveData() =
        flow { emit(ApiResponse.ApiResponseSuccess(listUserRepositories)) }


    override suspend fun makeRepositoryFavourite(userRepositoriesModel: UserRepositoriesTable): Int {
        userRepositoriesDagger.isFavourite = 1
        return userRepositoriesDagger.isFavourite
    }

    override suspend fun hideUnHideRepository(userRepositoriesModel: UserRepositoriesTable): Int {
        userRepositoriesDagger.hideUnHideRepository = 1
        return userRepositoriesDagger.hideUnHideRepository
    }
}
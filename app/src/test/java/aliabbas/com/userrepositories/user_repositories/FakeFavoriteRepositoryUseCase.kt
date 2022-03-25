package aliabbas.com.userrepositories.user_repositories

import aliabbas.com.scalablecodebaseapp.database.db.tables.UserRepositoriesTable
import aliabbas.com.userrepositories.shared.domain.domain_user_home.domain.usecase.FavouriteRepositoryUseCase

class FakeFavoriteRepositoryUseCase constructor(private val fakeUserRepositories: FakeUserRepositories) :
    FavouriteRepositoryUseCase {
    override suspend fun execute(userRepositoriesModel: UserRepositoriesTable): Int =
        fakeUserRepositories.makeRepositoryFavourite(userRepositoriesModel)

}
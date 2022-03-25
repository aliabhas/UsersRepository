package aliabbas.com.userrepositories.user_repositories

import aliabbas.com.scalablecodebaseapp.database.db.tables.UserRepositoriesTable
import aliabbas.com.userrepositories.shared.domain.domain_user_home.domain.usecase.HideRepositoryUseCase

class FakeHideRepositoryUseCase constructor(private val fakeUserRepositories: FakeUserRepositories) :
    HideRepositoryUseCase {
    override suspend fun execute(userRepositoriesModel: UserRepositoriesTable): Int =
        fakeUserRepositories.hideUnHideRepository(userRepositoriesModel)

}
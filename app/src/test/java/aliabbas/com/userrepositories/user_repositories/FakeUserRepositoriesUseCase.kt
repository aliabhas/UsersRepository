package aliabbas.com.userrepositories.user_repositories

import aliabbas.com.userrepositories.shared.domain.domain_user_home.domain.usecase.FetchUserHomeUseCase

class FakeUserRepositoriesUseCase constructor(private val fakeUserRepositories: FakeUserRepositories) :
    FetchUserHomeUseCase {
    override suspend fun execute() =
        fakeUserRepositories.getListUserRepositoriesLiveData()

}
package aliabbas.com.userrepositories.user_repositories

import aliabbas.com.scalablecodebaseapp.database.db.tables.UserRepositoriesTable
import aliabbas.com.scalablecodebaseapp.model.user_repository.OwnerModel
import aliabbas.com.userrepositories.shared.domain.domain_user_home.domain.usecase.FavouriteRepositoryUseCaseImpl
import aliabbas.com.userrepositories.shared.domain.domain_user_home.domain.usecase.FetchUserHomeUseCaseImpl
import aliabbas.com.userrepositories.shared.domain.domain_user_home.domain.usecase.HideRepositoryUseCaseImpl
import aliabbas.com.userrepositories.shared.result.ApiResponse
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class FakeUserRepositoriesUseCaseTest {
    private lateinit var userRepositoriesTable: UserRepositoriesTable
    private lateinit var fakeUserRepositories: FakeUserRepositories

    private lateinit var fakeUserRepositoriesUseCase: FetchUserHomeUseCaseImpl

    private lateinit var fakeFavoriteRepositoryUseCase: FavouriteRepositoryUseCaseImpl

    private lateinit var fakeHideRepositoryUseCase: HideRepositoryUseCaseImpl
    private val _listUserRepositories =
        MutableStateFlow<ApiResponse>(ApiResponse.ProgressLoadingState)


    @Before
    fun setUpUseCaseRepository() {
        userRepositoriesTable = UserRepositoriesTable(
            "AliAbbas", "Ali", OwnerModel("", ""),
            "", 0, 0
        )
        _listUserRepositories.value = ApiResponse.ApiResponseSuccess(listOf(userRepositoriesTable))
        fakeUserRepositories = FakeUserRepositories()
        fakeUserRepositoriesUseCase = FetchUserHomeUseCaseImpl(fakeUserRepositories)
        fakeFavoriteRepositoryUseCase = FavouriteRepositoryUseCaseImpl(fakeUserRepositories)
        fakeHideRepositoryUseCase = HideRepositoryUseCaseImpl(fakeUserRepositories)
    }

    @Test
    fun getUserRepositoryTest() = runBlocking {
        fakeUserRepositoriesUseCase().collect {
            assertThat(((it as ApiResponse.ApiResponseSuccess).responseData as List<*>).size == 2).isTrue()
        }

    }

    @Test
    fun makeRepositoryFavoriteTest() = runBlocking {
        val execute =
            fakeFavoriteRepositoryUseCase(userRepositoriesTable, _listUserRepositories)
        assertThat(execute == 0 || execute == 1).isNotNull()
    }

    @Test
    fun hideRepositoryTest() = runBlocking {
        fakeHideRepositoryUseCase(userRepositoriesTable)
        val filteredResult = fakeUserRepositories.listUserRepositories.firstOrNull {
            it.fullName.contentEquals(
                userRepositoriesTable.fullName
            ) && it.hideUnHideRepository == 1
        }
        assertThat(filteredResult).isNotNull()
    }

}
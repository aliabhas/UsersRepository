package aliabbas.com.userrepositories.user_repositories

import aliabbas.com.scalablecodebaseapp.database.db.tables.UserRepositoriesTable
import aliabbas.com.scalablecodebaseapp.model.user_repository.OwnerModel
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class FakeUserRepositoriesUseCaseTest {
    lateinit var userRepositoriesTable: UserRepositoriesTable
    private lateinit var fakeUserRepositories: FakeUserRepositories

    private lateinit var fakeUserRepositoriesUseCase: FakeUserRepositoriesUseCase

    private lateinit var fakeFavoriteRepositoryUseCase: FakeFavoriteRepositoryUseCase

    private lateinit var fakeHideRepositoryUseCase: FakeHideRepositoryUseCase


    @Before
    fun setUpUseCaseRepository() {
        userRepositoriesTable = UserRepositoriesTable(
            "AliAbbas", "Ali", OwnerModel("", ""),
            "", 0, 0
        )
        fakeUserRepositories = FakeUserRepositories()
        fakeUserRepositoriesUseCase = FakeUserRepositoriesUseCase(fakeUserRepositories)
        fakeFavoriteRepositoryUseCase = FakeFavoriteRepositoryUseCase(fakeUserRepositories)
        fakeHideRepositoryUseCase = FakeHideRepositoryUseCase(fakeUserRepositories)
    }

    @Test
    fun getUserRepositoryTest() = runBlocking {
        val count = fakeUserRepositoriesUseCase.execute().collect {
            assertThat((it.responseData as List<*>).size == 2).isTrue()
        }

    }

    @Test
    fun makeRepositoryFavoriteTest() = runBlocking {
        val execute = fakeFavoriteRepositoryUseCase.execute(userRepositoriesTable)
        val filteredResult = fakeUserRepositories.listUserRepositories.firstOrNull {
            it.fullName.contentEquals(
                userRepositoriesTable.fullName
            ) && it.isFavourite==1
        }
        assertThat(filteredResult).isNotNull()
    }

    @Test
    fun hideRepositoryTest() = runBlocking {
        val execute = fakeHideRepositoryUseCase.execute(userRepositoriesTable)
        val filteredResult = fakeUserRepositories.listUserRepositories.firstOrNull {
            it.fullName.contentEquals(
                userRepositoriesTable.fullName
            ) && it.hideUnHideRepository==1
        }
        assertThat(filteredResult).isNotNull()
    }

}
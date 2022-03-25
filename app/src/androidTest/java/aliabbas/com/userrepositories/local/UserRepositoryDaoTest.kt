package aliabbas.com.userrepositories.local

import aliabbas.com.scalablecodebaseapp.database.db.AppDatabase
import aliabbas.com.scalablecodebaseapp.database.db.dao.UserRepositoriesDao
import aliabbas.com.scalablecodebaseapp.database.db.tables.UserRepositoriesTable
import aliabbas.com.scalablecodebaseapp.model.user_repository.OwnerModel
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class UserRepositoryDaoTest {

    private lateinit var appDatabase: AppDatabase
    private lateinit var userRepositoriesDao: UserRepositoriesDao

    @Before
    fun setAppDataBase() {
        appDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries()
            .build()
        userRepositoriesDao = appDatabase.userRepositoriesDao()
    }

    @Test
    fun insertDataInUserRepositories()  {
        val userRepositoriesTable = UserRepositoriesTable(
            "AliAbbas", "Ali", OwnerModel("", ""),
            "", 0, 0
        )
        val listUserRepositories = listOf(
            userRepositoriesTable
        )
        userRepositoriesDao.insertDataInUserRepositories(listUserRepositories)
        val userRepositories = userRepositoriesDao.getUserRepositories()
        assertThat(userRepositories).contains(userRepositoriesTable)
    }

    @Test
    fun insertDataWithDuplicateRepository()  {
        val userRepositoriesDagger = UserRepositoriesTable(
            "Dagger", "Dagger", OwnerModel("", ""),
            "", 0, 0
        )
        val userRepositoriesHilt = UserRepositoriesTable(
            "Dagger", "Dagger", OwnerModel("", ""),
            "", 0, 0
        )
        val listUserRepositories = listOf(
            userRepositoriesDagger, userRepositoriesHilt
        )
        userRepositoriesDao.insertDataInUserRepositories(listUserRepositories)
        val userRepositories = userRepositoriesDao.getUserRepositories()
        assertThat(userRepositories?.size!! > 1).isFalse()
    }

    @Test
    fun getUserRepositories() {
        val userRepositories1 = UserRepositoriesTable(
            "Dagger", "Dagger", OwnerModel("", ""),
            "", 0, 0
        )
        val userRepositories2 = UserRepositoriesTable(
            "Hilt", "Hilt", OwnerModel("", ""),
            "", 0, 0
        )
        val listUserRepositories = listOf(
            userRepositories1, userRepositories2
        )
        userRepositoriesDao.insertDataInUserRepositories(listUserRepositories)
        val userRepositories = userRepositoriesDao.getUserRepositories()
        assertThat(userRepositories?.size!! > 1).isTrue()
    }

    @Test
    fun favouriteRepository() {
        val userRepository = UserRepositoriesTable(
            "Dagger", "Dagger", OwnerModel("", ""),
            "", 0, 0
        )
        val listUserRepositories = listOf(
            userRepository
        )
        userRepositoriesDao.insertDataInUserRepositories(listUserRepositories)
        val makeRepositoryFavorite =
            userRepositoriesDao.favouriteRepository(1, userRepository.fullName)
        assertThat(makeRepositoryFavorite == 1).isTrue()

        val removeFromFavorite =
            userRepositoriesDao.favouriteRepository(0, userRepository.fullName)
        assertThat(removeFromFavorite == 1).isTrue()

    }

    @Test
    fun hideUnHideRepository() {
        val userRepository = UserRepositoriesTable(
            "Dagger", "Dagger", OwnerModel("", ""),
            "", 0, 0
        )
        val listUserRepositories = listOf(
            userRepository
        )
        userRepositoriesDao.insertDataInUserRepositories(listUserRepositories)
        val hideRepository =
            userRepositoriesDao.hideUnHideRepository(1, userRepository.fullName)
        assertThat(hideRepository == 1).isTrue()

        val unHideRepository =
            userRepositoriesDao.hideUnHideRepository(0, userRepository.fullName)
        assertThat(unHideRepository == 1).isTrue()
    }


}
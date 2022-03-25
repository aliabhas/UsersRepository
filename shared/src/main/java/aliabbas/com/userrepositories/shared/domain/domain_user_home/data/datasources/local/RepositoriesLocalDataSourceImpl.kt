package aliabbas.com.userrepositories.shared.domain.domain_user_home.data.datasources.local

import aliabbas.com.scalablecodebaseapp.database.db.AppDatabase
import aliabbas.com.scalablecodebaseapp.database.db.tables.UserRepositoriesTable
import javax.inject.Inject

// this is just for an example to separate local and remote sources and combine them into repo
class RepositoriesLocalDataSourceImpl @Inject constructor(
    private val appDatabase: AppDatabase
) : RepositoriesLocalDataSource {
    override suspend fun getUserRepositories(): List<UserRepositoriesTable>? {
        return appDatabase.userRepositoriesDao().getUserRepositories()
    }

    override suspend fun insertUserRepositories(userRepositories: List<UserRepositoriesTable>) {
        appDatabase.userRepositoriesDao().insertDataInUserRepositories(userRepositories)
    }

    override suspend fun favouriteUserRepository(userRepositories: UserRepositoriesTable): Int {
        val isFavourite = if (userRepositories.isFavourite == 0) 1 else 0
         appDatabase.userRepositoriesDao()
            .favouriteRepository(isFavourite, userRepositories.fullName)
        return isFavourite
    }

    override suspend fun hideUnHideUserRepository(userRepositories: UserRepositoriesTable): Int {
        val isHide = if (userRepositories.hideUnHideRepository == 0) 1 else 0
        return appDatabase.userRepositoriesDao()
            .hideUnHideRepository(isHide, userRepositories.fullName)
    }
}
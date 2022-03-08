package aliabbas.com.userrepositories.shared.domain.domain_user_home.data.datasources.local

import aliabbas.com.scalablecodebaseapp.database.db.tables.UserRepositoriesTable

interface RepositoriesLocalDataSource {
    suspend fun getUserRepositories(): List<UserRepositoriesTable>?
    suspend fun insertUserRepositories(userRepositories: List<UserRepositoriesTable>)
    suspend fun favouriteUserRepository(userRepositories: UserRepositoriesTable): Int
    suspend fun hideUnHideUserRepository(userRepositories: UserRepositoriesTable): Int
}
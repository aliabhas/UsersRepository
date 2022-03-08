package aliabbas.com.userrepositories.shared.domain.domain_user_home.domain.usecase

import aliabbas.com.scalablecodebaseapp.database.db.tables.UserRepositoriesTable

interface HideRepositoryUseCase {
    suspend fun execute(userRepositoriesModel: UserRepositoriesTable): Int
}
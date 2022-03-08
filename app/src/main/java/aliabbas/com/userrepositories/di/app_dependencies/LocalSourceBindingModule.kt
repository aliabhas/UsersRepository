package aliabbas.com.userrepositories.di.app_dependencies

import aliabbas.com.scalablecodebaseapp.database.db.AppDatabase
import aliabbas.com.scalablecodebaseapp.shared.domain.domain_repository_commit.data.datasources.local.RepositoriesCommitLocalDataSource
import aliabbas.com.scalablecodebaseapp.shared.domain.domain_repository_commit.data.datasources.local.RepositoriesCommitLocalDataSourceImpl
import aliabbas.com.userrepositories.shared.domain.domain_user_home.data.datasources.local.RepositoriesLocalDataSource
import aliabbas.com.userrepositories.shared.domain.domain_user_home.data.datasources.local.RepositoriesLocalDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

/**
 * Created By Ali Abbas on on 30,December,2021
 * This Class is used for
 *
 */
@InstallIn(ViewModelComponent::class)
@Module
object LocalSourceBindingModule {
    @ViewModelScoped
    @Provides
    fun bindRepositoryDataSource(appDatabase: AppDatabase): RepositoriesLocalDataSource {
        return RepositoriesLocalDataSourceImpl(appDatabase)
    }

    @ViewModelScoped
    @Provides
    fun bindRepositoriesCommitLocalDataSource(appDatabase: AppDatabase): RepositoriesCommitLocalDataSource {
        return RepositoriesCommitLocalDataSourceImpl(appDatabase)
    }
}
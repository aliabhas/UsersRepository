package aliabbas.com.userrepositories.di.app_dependencies

import aliabbas.com.scalablecodebaseapp.shared.app_service_calls.Api
import aliabbas.com.scalablecodebaseapp.shared.domain.domain_repository_commit.data.datasources.remote.RepositoryCommitsRemoteDataSource
import aliabbas.com.scalablecodebaseapp.shared.domain.domain_repository_commit.data.datasources.remote.RepositoryCommitsRemoteDataSourceImpl
import aliabbas.com.userrepositories.shared.domain.domain_user_home.data.datasources.remote.RepositoriesRemoteDataSourceImpl
import aliabbas.com.userrepositories.shared.domain.domain_user_home.data.datasources.remote.RepositoryRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

/**
 * Created By Ali Abbas on on 21,December,2021
 * This Class is used for
 *
 */
@InstallIn(ViewModelComponent::class)
@Module
object DataSourceModule {

    @ViewModelScoped
    @Provides
    fun bindRepositoryDataSource(api: Api): RepositoryRemoteDataSource {
        return RepositoriesRemoteDataSourceImpl(api)
    }


    @ViewModelScoped
    @Provides
    fun bindRepositoryCommitDataSource(api: Api)
            : RepositoryCommitsRemoteDataSource {
        return RepositoryCommitsRemoteDataSourceImpl(api)
    }

}
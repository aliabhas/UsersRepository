package aliabbas.com.userrepositories.di.app_dependencies

import aliabbas.com.userrepositories.shared.domain.domain_repository_commit.TestInf
import aliabbas.com.scalablecodebaseapp.shared.domain.domain_repository_commit.data.RepositoryCommitDetailsImpl
import aliabbas.com.scalablecodebaseapp.shared.domain.domain_repository_commit.data.datasources.local.RepositoriesCommitLocalDataSource
import aliabbas.com.scalablecodebaseapp.shared.domain.domain_repository_commit.data.datasources.remote.RepositoryCommitsRemoteDataSource
import aliabbas.com.scalablecodebaseapp.shared.domain.domain_repository_commit.data.mapper.Mapper
import aliabbas.com.userrepositories.shared.domain.domain_repository_commit.domain.repository.CommitRepositoryRepository
import aliabbas.com.userrepositories.shared.domain.domain_user_home.data.UserRepositoriesRepositoryImpl
import aliabbas.com.userrepositories.shared.domain.domain_user_home.data.datasources.local.RepositoriesLocalDataSource
import aliabbas.com.userrepositories.shared.domain.domain_user_home.data.datasources.remote.RepositoryRemoteDataSource
import aliabbas.com.userrepositories.shared.domain.domain_user_home.domain.mapper.UserRepositoriesMapper
import aliabbas.com.userrepositories.shared.domain.domain_user_home.domain.repository.UserRepository
import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped

/**
 * Created By Ali Abbas
 * This Class is used for
 *
 */
@InstallIn(ViewModelComponent::class)
@Module
object RepositoryModule {
    @ViewModelScoped
    @Provides
    fun bindRepository(
        remoteDataSource: RepositoryRemoteDataSource,
        repositoriesLocalDataSource: RepositoriesLocalDataSource,
        @ApplicationContext context: Context,
        userRepositoriesMapper: UserRepositoriesMapper
    ): UserRepository {
        return UserRepositoriesRepositoryImpl(
            remoteDataSource,
            repositoriesLocalDataSource,
            context,
            userRepositoriesMapper
        )
    }

    @ViewModelScoped
    @Provides
    fun bindRepositoryCommits(
        @ApplicationContext context: Context, repository: RepositoryCommitsRemoteDataSource,
        repositoriesCommitLocalDataSource: RepositoriesCommitLocalDataSource,
        mapper: Mapper, testInf: TestInf
    ): CommitRepositoryRepository {
        return RepositoryCommitDetailsImpl(
            context, repository,
            repositoriesCommitLocalDataSource, mapper,
            testInf
        )
    }

}
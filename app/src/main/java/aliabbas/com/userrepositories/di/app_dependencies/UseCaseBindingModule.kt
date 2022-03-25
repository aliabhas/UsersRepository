package aliabbas.com.userrepositories.di.app_dependencies

import aliabbas.com.userrepositories.shared.domain.domain_repository_commit.domain.repository.CommitRepositoryRepository
import aliabbas.com.userrepositories.shared.domain.domain_repository_commit.domain.usecase.RepositoryCommitsUseCase
import aliabbas.com.userrepositories.shared.domain.domain_repository_commit.domain.usecase.RepositoryCommitsUseCaseImpl
import aliabbas.com.userrepositories.shared.domain.domain_user_home.domain.repository.UserRepository
import aliabbas.com.userrepositories.shared.domain.domain_user_home.domain.usecase.FavouriteRepositoryUseCaseImpl
import aliabbas.com.userrepositories.shared.domain.domain_user_home.domain.usecase.FetchUserHomeUseCaseImpl
import aliabbas.com.userrepositories.shared.domain.domain_user_home.domain.usecase.HideRepositoryUseCaseImpl
import aliabbas.com.userrepositories.shared.domain.domain_user_home.domain.usecase.UserRepositoriesUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

/**
 * Created By Ali Abbas
 * This Class is used for
 *
 */
@InstallIn(ViewModelComponent::class)
@Module
object UseCaseBindingModule {
    /*@ViewModelScoped
    @Provides
    fun bindRepository(userRepository: UserRepository)
            : FetchUserHomeUseCaseImpl {
        return FetchUserHomeUseCaseImpl(userRepository)
    }

    @ViewModelScoped
    @Provides
    fun bindFavouriteRepository(userRepository: UserRepository)
            : FavouriteRepositoryUseCaseImpl {
        return FavouriteRepositoryUseCaseImpl(userRepository)
    }

    @ViewModelScoped
    @Provides
    fun bindHideUnHideRepository(userRepository: UserRepository)
            : HideRepositoryUseCaseImpl {
        return HideRepositoryUseCaseImpl(userRepository)
    }*/

    @ViewModelScoped
    @Provides
    fun provideUserRepositoryUseCases(userRepository: UserRepository): UserRepositoriesUseCases {
        return UserRepositoriesUseCases(
            FetchUserHomeUseCaseImpl(userRepository),
            FavouriteRepositoryUseCaseImpl(userRepository),
            HideRepositoryUseCaseImpl(userRepository)
        )
    }

    @ViewModelScoped
    @Provides
    fun bindRepositoryCommits(repositoryCommitDetails: CommitRepositoryRepository)
            : RepositoryCommitsUseCase {
        return RepositoryCommitsUseCaseImpl(repositoryCommitDetails)
    }
}
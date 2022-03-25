package aliabbas.com.userrepositories.shared.domain.domain_user_home.domain.usecase

data class UserRepositoriesUseCases(
    var userRepository: FetchUserHomeUseCaseImpl,
    var favouriteRepositoryUseCase: FavouriteRepositoryUseCaseImpl,
    var hideRepositoryUseCase: HideRepositoryUseCaseImpl
)
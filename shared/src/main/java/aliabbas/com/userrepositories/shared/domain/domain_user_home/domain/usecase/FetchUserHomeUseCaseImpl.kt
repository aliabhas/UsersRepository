package aliabbas.com.userrepositories.shared.domain.domain_user_home.domain.usecase

import aliabbas.com.userrepositories.shared.domain.domain_user_home.domain.repository.UserRepository
import aliabbas.com.userrepositories.shared.result.ApiResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchUserHomeUseCaseImpl @Inject constructor(
    private var userRepository: UserRepository
) {
    suspend operator fun invoke(): Flow<ApiResponse> {
        return userRepository.getListUserRepositoriesLiveData()
    }

    // here call the specific repo and inject this use-case then into viewModel
    // use case can also talk to multiple repos
    // Sometime, for achieving one behaviour a use-case needs data from two different repos

    /*
    * According to Android documentation:
    * Classes in this layer(Domain layer) are commonly called use cases or interactors.
    * Each use case should have responsibility over a single functionality.
    * For example, your app could have a GetTimeZoneUseCase class if multiple
    * ViewModels rely on time zones to display the proper message on the screen.
    * */
}
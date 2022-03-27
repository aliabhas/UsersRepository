package aliabbas.com.userrepositories.shared.domain.domain_user_home.domain.usecase

import aliabbas.com.scalablecodebaseapp.database.db.tables.UserRepositoriesTable
import aliabbas.com.userrepositories.shared.domain.domain_user_home.domain.repository.UserRepository
import aliabbas.com.userrepositories.shared.result.ApiResponse
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@ExperimentalCoroutinesApi
class FavouriteRepositoryUseCaseImpl @Inject constructor(
    private var userRepository: UserRepository
) {
    suspend operator fun invoke(
        userRepositoriesModel: UserRepositoriesTable,
        _listUserRepositories: MutableStateFlow<ApiResponse>
    ): Int {
        val execute = userRepository.makeRepositoryFavourite(userRepositoriesModel)
        ((_listUserRepositories.value as ApiResponse.ApiResponseSuccess).responseData as List<UserRepositoriesTable>).first {
            it.fullName.contentEquals(userRepositoriesModel.fullName)
        }.isFavourite = execute
        return execute
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
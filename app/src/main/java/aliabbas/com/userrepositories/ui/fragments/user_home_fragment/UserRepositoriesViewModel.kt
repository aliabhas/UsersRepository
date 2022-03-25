package aliabbas.com.userrepositories.ui.fragments.user_home_fragment

import aliabbas.com.scalablecodebaseapp.database.db.tables.UserRepositoriesTable
import aliabbas.com.userrepositories.R
import aliabbas.com.userrepositories.shared.domain.domain_user_home.domain.usecase.FavouriteRepositoryUseCase
import aliabbas.com.userrepositories.shared.domain.domain_user_home.domain.usecase.FetchUserHomeUseCase
import aliabbas.com.userrepositories.shared.domain.domain_user_home.domain.usecase.HideRepositoryUseCase
import aliabbas.com.userrepositories.shared.result.ApiResponse
import android.graphics.drawable.Drawable
import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.Glide
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


/**
 * View Model to care of all the data for Github User Repository
 * 1- Getting this User's repositories : "https://api.github.com/users/mralexgray/repos"
 */

// do not inject repositories directly into viewModels
// create Use-cases and each Use-case responsible for one task
// Use-case will talk to repository


@HiltViewModel
class UserRepositoriesViewModel @Inject constructor(
    private var userRepository: FetchUserHomeUseCase,
    private var favouriteRepositoryUseCase: FavouriteRepositoryUseCase,
    private var hideRepositoryUseCase: HideRepositoryUseCase
) : ViewModel() {

    private val _listUserRepositories =
        MutableStateFlow<ApiResponse>(ApiResponse.ProgressLoadingState)
    var listUserRepositories: StateFlow<ApiResponse> = _listUserRepositories

    private val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        Log.i("Error Occurred", ": ${exception.message}")
    }

    init {
        getUserRepositories()
    }

    private fun getUserRepositories() {
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            userRepository.execute().collect {
                _listUserRepositories.emit(it)
            }
        }
    }

    fun hideUnHideRepository(userRepositoriesModel: UserRepositoriesTable) {
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            hideRepositoryUseCase.execute(userRepositoriesModel)
        }

    }

    fun favouriteRepository(userRepositoriesModel: UserRepositoriesTable) {
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            favouriteRepositoryUseCase.execute(userRepositoriesModel)
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }

    companion object {
        /**
         * This function is used to display the image of User repository
         * that I am displaying in RecyclerView with the help of Databinding
         */
        @JvmStatic
        @BindingAdapter("imageUrl", "errorImage")
        fun loadImage(view: ImageView, url: String, errorImage: Drawable) {
            Glide
                .with(view.context)
                .load(url)
                .placeholder(R.drawable.placeholder)
                .error(errorImage)
                .into(view)

        }
    }
}

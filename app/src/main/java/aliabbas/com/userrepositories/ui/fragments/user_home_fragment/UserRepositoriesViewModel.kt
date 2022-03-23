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
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.Glide
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
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
        MutableLiveData<ApiResponse>(ApiResponse.ProgressLoadingState)
    var listUserRepositories: MutableLiveData<ApiResponse> = _listUserRepositories

    private val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        Log.i("lolz", ": ${exception.message}")
        //_listUserRepositories.value = ApiResponse.ApiFailure(exception.message.toString())
    }

    init {
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            val userRepositoryResponse = userRepository.execute()
            withContext(Dispatchers.Main) {
                _listUserRepositories.value = userRepositoryResponse
            }
        }
    }

    public fun hideUnHideRepository(userRepositoriesModel: UserRepositoriesTable) {
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            hideRepositoryUseCase.execute(userRepositoriesModel)
        }

    }

    public fun favouriteRepository(userRepositoriesModel: UserRepositoriesTable) {
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            val userRepositoryIsFavorite = favouriteRepositoryUseCase.execute(userRepositoriesModel)
            val isFavourite = if (userRepositoriesModel.isFavourite == 0) 1 else 0
            userRepositoriesModel.isFavourite = isFavourite
            if (userRepositoryIsFavorite > 0) {
                val filterIndexed =
                    (_listUserRepositories.value as List<UserRepositoriesTable>).indexOfFirst{ userRepositoriesTable ->
                        userRepositoriesTable.fullName == userRepositoriesModel.fullName
                    }
                (_listUserRepositories.value as ArrayList<UserRepositoriesTable>)[filterIndexed] =
                    userRepositoriesModel
            }
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

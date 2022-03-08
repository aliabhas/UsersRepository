package aliabbas.com.userrepositories.ui.fragments.user_home_fragment

import aliabbas.com.userrepositories.R
import aliabbas.com.userrepositories.shared.result.ApiResponse
import aliabbas.com.userrepositories.ui.compose_components.compose_view.SearchView
import aliabbas.com.userrepositories.ui.compose_components.compose_view.UserRepositoriesList
import aliabbas.com.scalablecodebaseapp.database.db.tables.UserRepositoriesTable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.input.TextFieldValue
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserRepositoriesFragment : Fragment() {

    private val userRepositoriesViewModel: UserRepositoriesViewModel by viewModels()

    @Suppress("UNCHECKED_CAST")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return ComposeView(requireContext()).apply {
            setContent {
                var apiResponse = userRepositoriesViewModel.listUserRepositories
                    .observeAsState().value

                when (apiResponse) {
                    is ApiResponse.ApiResponseSuccess -> {
                        populateUserRepositoriesList(
                            isLoading = false,
                            listOfUserRepositories = apiResponse.responseData as List<UserRepositoriesTable>
                        )
                    }
                    is ApiResponse.ApiFailure -> {
                        populateUserRepositoriesList(
                            isLoading = false,
                            listOfUserRepositories = emptyList()
                        )
                    }
                    is ApiResponse.ProgressLoadingState -> {
                        populateUserRepositoriesList(
                            isLoading = true,
                            listOfUserRepositories = emptyList()
                        )
                    }
                    else -> {
                    }
                }
            }
        }
    }

    @Composable
    fun populateUserRepositoriesList(
        isLoading: Boolean,
        listOfUserRepositories: List<UserRepositoriesTable>
    ) {
        val textState = remember { mutableStateOf(TextFieldValue("")) }
        Column {
            SearchView(textState)
            UserRepositoriesList(
                loading = isLoading,
                listUserRepositories = listOfUserRepositories,
                onNavigateToRecipeDetailScreen = {
                    if (!it.name.contentEquals("")) {
                        val bundle = bundleOf("userRepository" to it)
                        //with the help of Navigation Component navigating Displaying Dialog
                        findNavController()
                            .navigate(R.id.navigateTodetailFragment, bundle)
                    }
                },
                onFavouriteClicked = {
                    userRepositoriesViewModel.favouriteRepository(it)
                },
                onHideRepositoryClicked = {
                    userRepositoriesViewModel.hideUnHideRepository(it)

                },
                state = textState
                )
        }
    }
}

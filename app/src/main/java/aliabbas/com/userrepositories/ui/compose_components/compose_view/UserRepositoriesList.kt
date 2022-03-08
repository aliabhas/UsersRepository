package aliabbas.com.userrepositories.ui.compose_components.compose_view

import aliabbas.com.scalablecodebaseapp.database.db.tables.UserRepositoriesTable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.ExperimentalCoroutinesApi
import java.util.*

/**
 * Created By Ali Abbas
 * This Class is used for
 *
 */
@ExperimentalCoroutinesApi
@Composable
fun UserRepositoriesList(
    loading: Boolean,
    listUserRepositories: List<UserRepositoriesTable>,
    onNavigateToRecipeDetailScreen: (UserRepositoriesTable) -> Unit,
    onFavouriteClicked: (UserRepositoriesTable) -> Unit,
    onHideRepositoryClicked: (UserRepositoriesTable) -> Unit,
    state: MutableState<TextFieldValue>
) {
    //This will remember the last values of list and will re-compose if there is any change to the list.
    val userRepositoryMutableList = remember {
        mutableStateListOf<UserRepositoriesTable>()
    }
    userRepositoryMutableList.clear()
    //This list will provide us with the filter option, after filtering the data from list we'll assign the same to "userRepositoryMutableList".
    userRepositoryMutableList.addAll(getUserRepositories(state, listUserRepositories))

    //Adding View through JetPack Compose logic starts here
    Box(
        modifier = Modifier
            .background(color = MaterialTheme.colors.surface)
            .padding(start = 15.dp, end = 15.dp, bottom = 15.dp)

    ) {
        if (loading && listUserRepositories.isEmpty()) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        } else if (listUserRepositories.isEmpty()) {
            DataNotFound()
        } else {
            //Adding items to list and populating them onscreen
            LazyColumn {
                itemsIndexed(
                    items = userRepositoryMutableList
                ) { index, recipe ->
                    UserRepositoriesView(
                        userRepository = recipe,
                        onClick = {
                            onNavigateToRecipeDetailScreen(listUserRepositories[index])
                        }, favouriteClick = {
                            onFavouriteClicked(listUserRepositories[index])
                        }, hideClicked = {
                            userRepositoryMutableList.removeAt(index)
                            onHideRepositoryClicked(listUserRepositories[index])
                        }
                    )
                }
            }
        }
    }

}

fun getUserRepositories(
    state: MutableState<TextFieldValue>,
    listUserRepositories: List<UserRepositoriesTable>
): List<UserRepositoriesTable> {
    var filteredCountries: List<UserRepositoriesTable>
    val searchedText = state.value.text
    //Adding some validation for filter list that we'll use for search.
    filteredCountries = if (searchedText.isEmpty()) {
        listUserRepositories
    } else {
        val resultList = ArrayList<UserRepositoriesTable>()
        for (country in listUserRepositories) {
            if (country.fullName.lowercase(Locale.getDefault())
                    .contains(searchedText.lowercase(Locale.getDefault()))
            ) {
                resultList.add(country)
            }
        }
        resultList
    }
    return filteredCountries
}

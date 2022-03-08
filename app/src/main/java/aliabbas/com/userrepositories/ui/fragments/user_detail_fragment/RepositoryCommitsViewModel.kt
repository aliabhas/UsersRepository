package aliabbas.com.userrepositories.ui.fragments.user_detail_fragment

import aliabbas.com.userrepositories.shared.domain.domain_repository_commit.domain.usecase.RepositoryCommitsUseCase
import aliabbas.com.userrepositories.shared.result.ApiResponse
import aliabbas.com.scalablecodebaseapp.database.db.tables.UserRepositoriesTable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created By Ali Abbas
 * This Class is used for
 *
 */
@HiltViewModel
class RepositoryCommitsViewModel @Inject
constructor(private val repositoryCommitsUseCase: RepositoryCommitsUseCase) : ViewModel() {

    var repositoryDetailModel =
        MutableLiveData<UserRepositoriesTable>()

    private var _listRepositoryCommitDetailsFlow = MutableSharedFlow<ApiResponse>()
    var listRepositoryCommitDetailsFlow = _listRepositoryCommitDetailsFlow

    fun setUserRepositoryDetails(repositoryDetailScope: UserRepositoriesTable) {
        if (repositoryDetailModel.value == repositoryDetailScope) {
            return
        }
        viewModelScope.launch {
            repositoryDetailModel.value = repositoryDetailScope
            repositoryCommitsUseCase.execute(repositoryDetailScope.name!!)
                .collect { listRepositoryCommitDetailsFlow.emit(it) }

        }
    }
}
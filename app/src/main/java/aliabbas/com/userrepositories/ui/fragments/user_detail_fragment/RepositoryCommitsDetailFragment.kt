package aliabbas.com.userrepositories.ui.fragments.user_detail_fragment

import aliabbas.com.userrepositories.R
import aliabbas.com.userrepositories.custom_views.BarChartView
import aliabbas.com.userrepositories.ui.compose_components.compose_view.addViewToContent
import aliabbas.com.userrepositories.shared.result.ApiResponse
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * This fragment is responsible for displaying user repository and commit details
 *
 */
@AndroidEntryPoint
class RepositoryCommitsDetailFragment @Inject constructor() : Fragment() {
    private val arguments: RepositoryCommitsDetailFragmentArgs by navArgs()
    private val viewModel: RepositoryCommitsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view =
            inflater.inflate(R.layout.user_repository_detail_screen_jetpack, container, false)
        viewModel.setUserRepositoryDetails(arguments.userRepository)

        view.findViewById<ComposeView>(R.id.compose_view).setContent {
            val repositoryDetailModel by viewModel.repositoryDetailModel.observeAsState()
            val listRepositoryCommitDetailsFlow = viewModel.listRepositoryCommitDetailsFlow
            val lifecycleOwner = LocalLifecycleOwner.current
            val listRepositoryCommitDetailsFlowLifecycleAware: Flow<ApiResponse> =
                remember(listRepositoryCommitDetailsFlow, lifecycleOwner) {
                    listRepositoryCommitDetailsFlow.flowWithLifecycle(
                        lifecycleOwner.lifecycle,
                        Lifecycle.State.STARTED
                    )
                }
            val listRepositoryCommits by listRepositoryCommitDetailsFlowLifecycleAware.collectAsState(
                ApiResponse.ProgressLoadingState
            )
            addViewToContent(
                barChartView = BarChartView(requireContext()),
                userRepositoriesModel = repositoryDetailModel!!,
                listRepositoryCommits = listRepositoryCommits
            )
        }
        return view
    }
}

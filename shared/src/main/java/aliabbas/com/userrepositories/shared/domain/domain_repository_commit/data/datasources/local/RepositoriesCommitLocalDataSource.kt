package aliabbas.com.scalablecodebaseapp.shared.domain.domain_repository_commit.data.datasources.local

import aliabbas.com.scalablecodebaseapp.database.db.tables.RepositoryCommits

/**
 * Created By Ali Abbas on on 29,December,2021
 * This Class is used for
 *
 */
interface RepositoriesCommitLocalDataSource {
    fun insertRepositoriesCommitsDetails(repositoryCommits: RepositoryCommits)
    fun getRepositoriesCommits(repositoryName: String): RepositoryCommits?
}
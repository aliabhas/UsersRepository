package aliabbas.com.scalablecodebaseapp.shared.domain.domain_repository_commit.data.datasources.local

import aliabbas.com.scalablecodebaseapp.database.db.AppDatabase
import aliabbas.com.scalablecodebaseapp.database.db.tables.RepositoryCommits
import javax.inject.Inject

/**
 * Created By Ali Abbas on on 29,December,2021
 * This Class is used for
 *
 */
class RepositoriesCommitLocalDataSourceImpl @Inject constructor(
    private val appDatabase: AppDatabase
) : RepositoriesCommitLocalDataSource {
    override fun getRepositoriesCommits(repositoryName: String): RepositoryCommits? {
        return appDatabase.repositoriesCommitsDao().getRepositoryCommitDetails(repositoryName)
    }

    override fun insertRepositoriesCommitsDetails(repositoryCommits: RepositoryCommits) {
        appDatabase.repositoriesCommitsDao().insertDataInRepositoryCommits(repositoryCommits)
    }
}
package aliabbas.com.scalablecodebaseapp.database.db.dao

import aliabbas.com.scalablecodebaseapp.database.db.tables.RepositoryCommits
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 * Created By Ali Abbas on on 29,December,2021
 * This Class is used for
 *
 */
@Dao
abstract class RepositoryCommitsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertDataInRepositoryCommits(userRepositories: RepositoryCommits): Long

    @Query("SELECT * FROM RepositoryCommits WHERE repositoryName=:repositoryName")
    abstract fun getRepositoryCommitDetails(repositoryName: String): RepositoryCommits?
}
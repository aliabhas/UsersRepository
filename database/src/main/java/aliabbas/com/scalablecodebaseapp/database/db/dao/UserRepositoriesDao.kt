package aliabbas.com.scalablecodebaseapp.database.db.dao

import aliabbas.com.scalablecodebaseapp.database.db.tables.UserRepositoriesTable
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
abstract class UserRepositoriesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract fun insertDataInUserRepositories(userRepositories: List<UserRepositoriesTable>): LongArray

    @Query("SELECT * FROM UserRepositoriesTable where hideUnHideRepository = 0")
    abstract fun getUserRepositories(): List<UserRepositoriesTable>?

    @Query("UPDATE UserRepositoriesTable SET isFavourite =:isFavourite where fullName=:repositoryName")
    abstract fun favouriteRepository(
        isFavourite: Int,
        repositoryName: String
    ): Int

    @Query("UPDATE UserRepositoriesTable SET hideUnHideRepository =:hideUnHide where fullName=:repositoryName")
    abstract fun hideUnHideRepository(
        hideUnHide: Int,
        repositoryName: String
    ): Int

}
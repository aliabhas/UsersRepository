package aliabbas.com.scalablecodebaseapp.database.db

import aliabbas.com.scalablecodebaseapp.database.db.converters.ListToStringTypeConverter
import aliabbas.com.scalablecodebaseapp.database.db.converters.ListToStringTypeConverterBarData
import aliabbas.com.scalablecodebaseapp.database.db.converters.ObjectToStringTypeConverter
import aliabbas.com.scalablecodebaseapp.database.db.dao.RepositoryCommitsDao
import aliabbas.com.scalablecodebaseapp.database.db.dao.UserRepositoriesDao
import aliabbas.com.scalablecodebaseapp.database.db.tables.RepositoryCommits
import aliabbas.com.scalablecodebaseapp.database.db.tables.UserRepositories
import aliabbas.com.scalablecodebaseapp.database.db.tables.UserRepositoriesTable
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

/**
 * Created By Ali Abbas on on 29,December,2021
 * This Class is used for
 *
 */
@Database(
    entities = [
        UserRepositories::class,
        RepositoryCommits::class,
        UserRepositoriesTable::class,
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(
    ListToStringTypeConverter::class, ListToStringTypeConverterBarData::class,
    ObjectToStringTypeConverter::class
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userRepositoriesDao(): UserRepositoriesDao
    abstract fun repositoriesCommitsDao(): RepositoryCommitsDao

    companion object {
        const val databaseName = "AppData.db"

    }
}
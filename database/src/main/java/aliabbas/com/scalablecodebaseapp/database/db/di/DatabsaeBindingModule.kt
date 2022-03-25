package aliabbas.com.scalablecodebaseapp.database.db.di

import aliabbas.com.scalablecodebaseapp.database.db.AppDatabase
import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created By Ali Abbas on on 31,December,2021
 * This Class is used for
 *
 */

@Module
@InstallIn(SingletonComponent::class)
class DatabaseBindingModule {

    @Singleton
    @Provides
    fun bindAppDataBaseReference(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, AppDatabase::class.java, AppDatabase.databaseName)
            .fallbackToDestructiveMigration()
            .build()
}
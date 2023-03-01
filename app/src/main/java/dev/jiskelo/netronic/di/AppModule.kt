package dev.jiskelo.netronic.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.jiskelo.netronic.data.AppDatabase
import dev.jiskelo.netronic.data.dao.UserDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun getDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getDatabase(context)
    }

    @Singleton
    @Provides
    fun getDao(appDatabase: AppDatabase): UserDao {
        return appDatabase.numberDao()
    }
}
package dev.jiskelo.netronic.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.jiskelo.netronic.repositories.UserRepository
import dev.jiskelo.netronic.repositories.UserRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
interface AppBindModule {

    @Suppress("FunctionName")
    @Binds
    fun bindUsersRepositoryImpl_to_UsersRepository(
        usersRepositoryImpl: UserRepositoryImpl
    ) :UserRepository
}
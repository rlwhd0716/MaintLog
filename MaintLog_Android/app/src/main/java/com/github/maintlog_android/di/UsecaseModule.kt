package com.github.maintlog_android.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
//    @Provides
//    fun provideLoginUseCase(repo: UserRepository) = LoginUseCase(repo)
}

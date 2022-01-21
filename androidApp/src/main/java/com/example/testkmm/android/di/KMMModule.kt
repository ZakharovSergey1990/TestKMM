package com.example.testkmm.android.di

import com.example.testkmm.di.MultiplatformSDK
import com.example.testkmm.di.userRepository
import com.example.testkmm.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object KMMModule {

    @Provides
    fun provideUserRepository(): UserRepository {
        return MultiplatformSDK.userRepository
    }
}
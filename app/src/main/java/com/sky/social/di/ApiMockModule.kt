package com.sky.social.di

import com.sky.social.data.ApiMock
import com.sky.social.data.ApiMockImpl
import com.sky.social.domain.HomePageUseCase
import com.sky.social.domain.HomePageUseCaseImpl
import com.sky.social.domain.SettingPageUseCase
import com.sky.social.domain.SettingPageUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class ApiMockModule {

    @Binds
    @Singleton
    abstract fun bindHApiMock(
        apiMockImpl: ApiMockImpl
    ): ApiMock
}

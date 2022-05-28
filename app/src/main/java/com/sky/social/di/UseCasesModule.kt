package com.sky.social.di

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
abstract class UseCasesModule {

    @Binds
    @Singleton
    abstract fun bindHomePageUseCase(
        useCaseImpl: HomePageUseCaseImpl
    ): HomePageUseCase

    @Binds
    @Singleton
    abstract fun bindSettingPageUseCase(
        useCaseImpl: SettingPageUseCaseImpl
    ): SettingPageUseCase
}

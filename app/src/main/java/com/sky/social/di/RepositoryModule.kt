package com.sky.social.di

import com.sky.social.data.VideosRepositoryImpl
import com.sky.social.domain.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindVideosRepository(
        repository: VideosRepositoryImpl
    ): VideosRepository

}

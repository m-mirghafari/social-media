package com.sky.social.domain

import com.sky.social.data.ResultState
import com.sky.social.data.VideoData
import kotlinx.coroutines.flow.*
import javax.inject.Inject


class HomePageUseCaseImpl @Inject constructor(
    private val videosRepository: VideosRepository
) : HomePageUseCase {

    override fun getVideos(): Flow<ResultState<List<VideoData>>> {
        return videosRepository.getVideos()
    }
}
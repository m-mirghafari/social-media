package com.sky.social.domain

import com.sky.social.data.ResultState
import com.sky.social.data.VideoData
import kotlinx.coroutines.flow.Flow


interface HomePageUseCase {
    fun getVideos(): Flow<ResultState<List<VideoData>>>
}

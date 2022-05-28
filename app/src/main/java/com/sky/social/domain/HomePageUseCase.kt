package com.sky.social.domain

import com.sky.social.data.VideoData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow


interface HomePageUseCase {
    fun getVideos(): Flow<SharedFlow<Result<List<VideoData>>>>
}

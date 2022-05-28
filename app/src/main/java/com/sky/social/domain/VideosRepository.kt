package com.sky.social.domain

import com.sky.social.data.VideoData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow

interface VideosRepository {
    fun getVideos(): Flow<SharedFlow<Result<List<VideoData>>>>
    fun updateVideoLikeAndViewCounter(videoId: String?, likes: Int, views: Int): Flow<Result<Nothing>>
}
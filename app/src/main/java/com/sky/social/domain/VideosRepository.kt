package com.sky.social.domain

import com.sky.social.data.ResultState
import com.sky.social.data.VideoData
import kotlinx.coroutines.flow.Flow

interface VideosRepository {
    fun getVideos(): Flow<ResultState<List<VideoData>>>
    fun updateVideoLikeAndViewCounter(videoId: String?, likes: Int, views: Int): Flow<Result<Nothing>>
}
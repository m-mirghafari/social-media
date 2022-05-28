package com.sky.social.domain

import com.sky.social.data.VideoData
import kotlinx.coroutines.flow.*
import javax.inject.Inject


class SettingPageUseCaseImpl @Inject constructor(
    private val videosRepository: VideosRepository
) : SettingPageUseCase {

    override fun getVideos(): Flow<SharedFlow<Result<List<VideoData>>>> {
        return videosRepository.getVideos()
    }

    override fun updateVideoLikeAndViewCounter(
        videoId: String?,
        likes: Int,
        views: Int
    ): Flow<Result<Nothing>> {
        return videosRepository.updateVideoLikeAndViewCounter(
            videoId = videoId, likes = likes, views = views
        )
    }
}
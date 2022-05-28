package com.sky.social.data

import com.sky.social.di.IoDispatcher
import com.sky.social.domain.VideosRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*
import javax.inject.Inject


class VideosRepositoryImpl @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) : VideosRepository {

    private var videos = MutableSharedFlow<Result<List<VideoData>>>()

    override fun getVideos(): Flow<SharedFlow<Result<List<VideoData>>>> {
        TODO("Not yet implemented")
    }

    override fun updateVideoLikeAndViewCounter(
        videoId: String?,
        likes: Int,
        views: Int
    ): Flow<Result<Nothing>> {
        TODO("Not yet implemented")
    }
}
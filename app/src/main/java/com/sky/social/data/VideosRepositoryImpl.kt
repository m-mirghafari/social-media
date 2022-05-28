package com.sky.social.data

import com.sky.social.di.IoDispatcher
import com.sky.social.domain.VideosRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*
import javax.inject.Inject


class VideosRepositoryImpl @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val apiMock: ApiMock
) : VideosRepository {

    private var videos = MutableStateFlow<ResultState<List<VideoData>>?>(null)

    override fun getVideos(): Flow<ResultState<List<VideoData>>> = flow {
        emit(ResultState.Loading())
        getVideosFromNetwork()

        videos.collect { videosResult ->
            videosResult?.let {
                emit(it)
            }
        }

    }.flowOn(ioDispatcher)

    private suspend fun getVideosFromNetwork() {
        videos.emit(ResultState.Success(apiMock.getVideos()))
    }

    override fun updateVideoLikeAndViewCounter(
        videoId: String?,
        likes: Int,
        views: Int
    ): Flow<Result<Nothing>> {
        TODO("Not yet implemented")
    }
}
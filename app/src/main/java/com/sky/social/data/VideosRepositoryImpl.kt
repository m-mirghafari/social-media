package com.sky.social.data

import android.util.Log
import com.sky.social.di.IoDispatcher
import com.sky.social.domain.VideosRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class VideosRepositoryImpl @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val apiMock: ApiMock
) : VideosRepository {

    private var videos = MutableStateFlow<ResultState<List<VideoData>>?>(null)

    override fun getVideos(): Flow<ResultState<List<VideoData>>> = flow {
        if (videos.value == null) {
            emit(ResultState.Loading())
            getVideosFromNetwork()
        }

        videos.collect { videosResult ->
            videosResult?.let {
                Log.i(">>>>", "videos: $it")
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
    ): Flow<Result<Nothing>> = flow<Nothing> {
        if (videos.value is ResultState.Success) {
            (videos.value as? ResultState.Success<List<VideoData>>)?.data?.let { videosList ->
                videos.emit(
                    ResultState.Success(
                        videosList.map { video ->
                            video.copy().also {
                                if (it.id == videoId) {
                                    it.likes = likes
                                    it.views = views
                                }
                            }
                        }
                    )
                )
            }
        }

    }.flowOn(ioDispatcher)
}
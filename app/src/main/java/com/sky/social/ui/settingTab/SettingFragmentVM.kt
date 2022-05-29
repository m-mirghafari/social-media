package com.sky.social.ui.settingTab

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sky.social.data.ResultState
import com.sky.social.data.VideoData
import com.sky.social.di.IoDispatcher
import com.sky.social.domain.SettingPageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SettingFragmentVM @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val settingPageUseCase: SettingPageUseCase
) : ViewModel() {

    private val _showLoading = MutableStateFlow(false)
    val showLoading: StateFlow<Boolean> = _showLoading.asStateFlow()

    private val _videos = MutableStateFlow<List<VideoData>?>(null)
    val videos: StateFlow<List<VideoData>?> = _videos.asStateFlow()

    init {
        getVideos()
    }

    private fun getVideos() {
        viewModelScope.launch(ioDispatcher) {
            settingPageUseCase.getVideos().collect { result ->
                when (result) {
                    is ResultState.Success -> {
                        _showLoading.emit(false)
                        _videos.emit(result.data)
                    }
                    is ResultState.Error -> {
                        _showLoading.emit(false)
                    }
                    is ResultState.Loading -> {
                        _showLoading.emit(true)
                    }
                }
            }
        }
    }

    fun updateTheVideoViewsAndLikes(
        videoId: String?,
        viewCount: Int,
        likesCount: Int
    ) {
        viewModelScope.launch(ioDispatcher) {
            settingPageUseCase.updateVideoLikeAndViewCounter(
                videoId = videoId,
                views = viewCount,
                likes = likesCount
            ).collect()
        }
    }
}
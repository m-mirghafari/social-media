package com.sky.social.ui.homeTab

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sky.social.data.ResultState
import com.sky.social.data.VideoData
import com.sky.social.di.IoDispatcher
import com.sky.social.domain.HomePageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeFragmentVM @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val homePageUseCase: HomePageUseCase
) : ViewModel() {

    private val _videos = MutableStateFlow<ResultState<List<VideoData>>?>(null)
    val videos: StateFlow<ResultState<List<VideoData>>?> = _videos.asStateFlow()

    init {
        getVideos()
    }

    private fun getVideos() {
        viewModelScope.launch(ioDispatcher) {
            homePageUseCase.getVideos().collect { result ->
                _videos.emit(result)
            }
        }
    }
}
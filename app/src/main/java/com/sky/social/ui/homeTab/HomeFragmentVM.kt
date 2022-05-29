package com.sky.social.ui.homeTab

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

    private val _showLoading = MutableStateFlow(false)
    val showLoading: StateFlow<Boolean> = _showLoading.asStateFlow()

    private val _videos = MutableStateFlow<List<VideoData>?>(null)
    val videos: StateFlow<List<VideoData>?> = _videos.asStateFlow()

    init {
        getVideos()
    }

    private fun getVideos() {
        viewModelScope.launch(ioDispatcher) {
            homePageUseCase.getVideos().collect { result ->
                when (result) {
                    is ResultState.Success -> {
                        _showLoading.emit(false)
                        _videos.emit(result.data)
                    }
                    is ResultState.Error -> {
                        // TODO: handle the error and show some message!
                        _showLoading.emit(false)
                    }
                    is ResultState.Loading -> {
                        _showLoading.emit(true)
                    }
                }
            }
        }
    }
}
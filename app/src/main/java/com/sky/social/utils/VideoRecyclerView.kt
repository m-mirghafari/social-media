package com.sky.social.utils

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.source.DefaultMediaSourceFactory
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource
import com.google.android.exoplayer2.upstream.HttpDataSource
import com.google.android.exoplayer2.upstream.cache.CacheDataSource
import com.sky.social.App.Companion.simpleCache


class VideoRecyclerView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : RecyclerView(context, attrs, defStyleAttr) {


    private val httpDataSourceFactory: HttpDataSource.Factory =
        DefaultHttpDataSource.Factory().setAllowCrossProtocolRedirects(true)

    private val cacheDataSourceFactory: DataSource.Factory = CacheDataSource.Factory()
        .setCache(simpleCache)
        .setUpstreamDataSourceFactory(httpDataSourceFactory)

    private val videoPlayer = ExoPlayer.Builder(context.applicationContext)
        .setMediaSourceFactory(DefaultMediaSourceFactory(cacheDataSourceFactory))
        .build()


    private var currentPlayingPosition = -1

    override fun onScrolled(dx: Int, dy: Int) {
        super.onScrolled(dx, dy)

        val firstVisibleViewPosition =
            (layoutManager as LinearLayoutManager).findFirstCompletelyVisibleItemPosition()
        if (firstVisibleViewPosition != currentPlayingPosition && firstVisibleViewPosition > -1) {
            stopLastVideoAndStartPlayTheNewOne(firstVisibleViewPosition)
        }
    }

    private fun stopLastVideoAndStartPlayTheNewOne(newPosition: Int) {
        (findViewHolderForAdapterPosition(currentPlayingPosition) as? VideoRecyclerViewViewHolder)?.let { lastViewHolder ->
//            Log.e(">>>>", "stop player on position $currentPlayingPosition")
            lastViewHolder.stopTheVideo()
        }

        (findViewHolderForAdapterPosition(newPosition) as? VideoRecyclerViewViewHolder)?.let { lastViewHolder ->
//            Log.i(">>>>", "play video on position $newPosition")
            currentPlayingPosition = newPosition
            lastViewHolder.prepareTheVideo(videoPlayer)
            lastViewHolder.playTheVideo()
        }
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        videoPlayer.stop()
        videoPlayer.release()
    }
}


interface VideoRecyclerViewViewHolder {
    fun prepareTheVideo(videoPlayer: ExoPlayer)
    fun playTheVideo()
    fun stopTheVideo()
}
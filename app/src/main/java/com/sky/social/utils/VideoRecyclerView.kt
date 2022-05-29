package com.sky.social.utils

import android.content.Context
import android.media.MediaCodec
import android.util.AttributeSet
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.Player

class VideoRecyclerView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : RecyclerView(context, attrs, defStyleAttr) {

    private val videoPlayer = ExoPlayer.Builder(context.applicationContext).build().also {
//        it.repeatMode = Player.REPEAT_MODE_ALL
        it.videoScalingMode = MediaCodec.VIDEO_SCALING_MODE_SCALE_TO_FIT_WITH_CROPPING
    }

    private var currentPlayingPosition = -1

    override fun onScrolled(dx: Int, dy: Int) {
        super.onScrolled(dx, dy)

        val firstVisibleViewPosition = (layoutManager as LinearLayoutManager).findFirstCompletelyVisibleItemPosition()
        if (firstVisibleViewPosition != currentPlayingPosition && firstVisibleViewPosition > -1) {
            stopLastVideoAndStartPlayTheNewOne(firstVisibleViewPosition)
        }
    }

    private fun stopLastVideoAndStartPlayTheNewOne(newPosition: Int) {
        (findViewHolderForAdapterPosition(currentPlayingPosition) as? VideoRecyclerViewViewHolder)?.let { lastViewHolder ->
            Log.e(">>>>", "stop player on position $currentPlayingPosition")
            lastViewHolder.stopTheVideo()
        }

        (findViewHolderForAdapterPosition(newPosition) as? VideoRecyclerViewViewHolder)?.let { lastViewHolder ->
            Log.i(">>>>", "play video on position $newPosition")
            currentPlayingPosition = newPosition
            lastViewHolder.prepareTheVideo(videoPlayer)
            lastViewHolder.playTheVideo()
        }
    }
}


interface VideoRecyclerViewViewHolder {
    fun prepareTheVideo(videoPlayer: ExoPlayer)
    fun playTheVideo()
    fun stopTheVideo()
}
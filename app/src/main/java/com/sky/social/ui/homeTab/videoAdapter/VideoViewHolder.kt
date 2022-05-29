package com.sky.social.ui.homeTab.videoAdapter

import android.net.Uri
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.sky.social.data.VideoData
import com.sky.social.databinding.ItemVideoBinding
import com.sky.social.utils.VideoRecyclerViewViewHolder
import kotlinx.coroutines.*


class VideoViewHolder(private val binding: ItemVideoBinding) :
    RecyclerView.ViewHolder(binding.root), VideoRecyclerViewViewHolder {

    private var item: VideoData? = null

    fun onBind(item: VideoData?) {
        item?.let { video ->
            this@VideoViewHolder.item = item

            with(binding) {
                thumbnailImage.load(video.thumbnail)
                thumbnailImage.visibility = View.VISIBLE
                playerView.visibility = View.INVISIBLE

                txtVideoId.text = video.id
                txtLikes.text = video.likes.toString()
                txtViews.text = video.views.toString()
            }
        }
    }

    override fun prepareTheVideo(videoPlayer: ExoPlayer) {
        with(binding) {
            thumbnailImage.visibility = View.VISIBLE
            playerView.visibility = View.INVISIBLE
            playerView.player = videoPlayer
        }

        with(videoPlayer) {
            videoPlayer.stop()
            videoPlayer.clearMediaItems()
            item?.videoUrl?.let { videoUrl ->
                addMediaItem(MediaItem.fromUri(Uri.parse(videoUrl)))
                videoPlayer.prepare()
            }
        }
    }

    override fun playTheVideo() {
        with(binding) {
            if (playerView.player != null) {
                playerView.player?.play()
                playerView.visibility = View.VISIBLE
                thumbnailImage.visibility = View.INVISIBLE
            }
        }
    }

    override fun stopTheVideo() {
        with(binding) {
            playerView.player = null
            thumbnailImage.visibility = View.VISIBLE
            playerView.visibility = View.INVISIBLE
        }
    }
}
package com.sky.social.ui.homeTab.videoAdapter

import androidx.recyclerview.widget.DiffUtil
import com.sky.social.data.VideoData

class VideoDiffUtils : DiffUtil.ItemCallback<VideoData>() {

    override fun areItemsTheSame(oldItem: VideoData, newItem: VideoData): Boolean {
        return true
    }

    override fun areContentsTheSame(oldItem: VideoData, newItem: VideoData): Boolean {
        return oldItem.id == newItem.id
                && oldItem.likes == newItem.likes
                && oldItem.views == newItem.views
    }
}
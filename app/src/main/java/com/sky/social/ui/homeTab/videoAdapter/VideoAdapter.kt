package com.sky.social.ui.homeTab.videoAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.sky.social.data.VideoData
import com.sky.social.databinding.ItemVideoBinding
import dagger.hilt.android.scopes.FragmentScoped
import javax.inject.Inject

@FragmentScoped
class VideoAdapter @Inject constructor() : ListAdapter<VideoData, VideoViewHolder>(VideoDiffUtils()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        return VideoViewHolder(
            binding = ItemVideoBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        holder.onBind(item = getItem(position))
    }
}
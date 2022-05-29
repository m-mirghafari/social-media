package com.sky.social.ui.settingTab.settingAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.sky.social.data.VideoData
import com.sky.social.databinding.ItemSettingBinding
import com.sky.social.ui.homeTab.videoAdapter.VideoDiffUtils
import dagger.hilt.android.scopes.FragmentScoped
import javax.inject.Inject

@FragmentScoped
class SettingAdapter @Inject constructor() :
    ListAdapter<VideoData, SettingViewHolder>(VideoDiffUtils()) {

    var onChangeTheVideoLikesOrViews: ((videoId: String?, viewsCount: Int, likesCount: Int) -> Unit)? =
        null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SettingViewHolder {
        return SettingViewHolder(
            binding = ItemSettingBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            ),
            onClickIncreaseView = { position ->
                getItem(position)?.let { item ->
                    onChangeTheVideoLikesOrViews?.invoke(
                        item.id,
                        (item.views ?: 0).plus(1),
                        item.likes ?: 0,
                    )
                }
            },
            onClickDecreaseView = { position ->
                getItem(position)?.let { item ->
                    onChangeTheVideoLikesOrViews?.invoke(
                        item.id,
                        (item.views ?: 0).minus(1),
                        item.likes ?: 0,
                    )
                }
            },
            onClickIncreaseLike = { position ->
                getItem(position)?.let { item ->
                    onChangeTheVideoLikesOrViews?.invoke(
                        item.id,
                        item.views ?: 0,
                        (item.likes ?: 0).plus(1),
                    )
                }
            },
            onClickDecreaseLike = { position ->
                getItem(position)?.let { item ->
                    onChangeTheVideoLikesOrViews?.invoke(
                        item.id,
                        item.views ?: 0,
                        (item.likes ?: 0).minus(1),
                    )
                }
            }
        )
    }

    override fun onBindViewHolder(holder: SettingViewHolder, position: Int) {
        holder.onBind(item = getItem(position))
    }

}
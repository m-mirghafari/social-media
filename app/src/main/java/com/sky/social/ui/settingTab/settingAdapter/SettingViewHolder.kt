package com.sky.social.ui.settingTab.settingAdapter

import androidx.recyclerview.widget.RecyclerView
import com.sky.social.data.VideoData
import com.sky.social.databinding.ItemSettingBinding


class SettingViewHolder(
    private val binding: ItemSettingBinding,
    private val onClickIncreaseView: (position: Int) -> Unit,
    private val onClickDecreaseView: (position: Int) -> Unit,
    private val onClickIncreaseLike: (position: Int) -> Unit,
    private val onClickDecreaseLike: (position: Int) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

    init {
        with(binding) {
            btnIncreaseView.setOnClickListener {
                onClickIncreaseView.invoke(absoluteAdapterPosition)
            }

            btnDecreaseView.setOnClickListener {
                onClickDecreaseView.invoke(absoluteAdapterPosition)
            }

            btnIncreaseLike.setOnClickListener {
                onClickIncreaseLike.invoke(absoluteAdapterPosition)
            }

            btnDecreaseLike.setOnClickListener {
                onClickDecreaseLike.invoke(absoluteAdapterPosition)
            }
        }
    }

    fun onBind(item: VideoData?) {
        item?.let { video ->

            with(binding) {
                txtVideoId.text = video.id
                txtLikes.text = video.likes.toString()
                txtViews.text = video.views.toString()
            }
        }
    }
}
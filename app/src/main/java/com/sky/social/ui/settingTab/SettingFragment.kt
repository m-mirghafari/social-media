package com.sky.social.ui.settingTab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.sky.social.R
import com.sky.social.databinding.FragmentSettingBinding
import com.sky.social.ui.settingTab.settingAdapter.SettingAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class SettingFragment : Fragment() {

    private lateinit var binding: FragmentSettingBinding
    private val viewModel: SettingFragmentVM by viewModels()

    @Inject
    lateinit var adapter: SettingAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_setting, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initTheObservers()
        initAdapter()
    }

    private fun initTheObservers() {
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.videos.collectLatest { result ->
                    adapter.submitList(result)
                }
            }
        }
    }

    private fun initAdapter() {
        binding.list.layoutManager = LinearLayoutManager(requireContext())
        binding.list.adapter = adapter
        adapter.onChangeTheVideoLikesOrViews = { videoId, viewsCount, likesCount ->
            viewModel.updateTheVideoViewsAndLikes(
                videoId = videoId,
                viewCount = viewsCount,
                likesCount = likesCount
            )
        }
    }
}
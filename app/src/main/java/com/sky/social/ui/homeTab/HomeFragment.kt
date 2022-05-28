package com.sky.social.ui.homeTab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.sky.social.R
import com.sky.social.data.ResultState
import com.sky.social.databinding.FragmentHomeBinding
import com.sky.social.di.MainDispatcher
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeFragmentVM by viewModels()

    @Inject
    @MainDispatcher
    lateinit var mainDispatcher: CoroutineDispatcher


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initTheObservers()
    }

    private fun initTheObservers() {
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.videos.collectLatest { result ->
                    when(result) {
                        is ResultState.Success -> {
                            // TODO: submit the adapter
                            withContext(mainDispatcher) {
                                binding.showLoading = false
                            }
                        }
                        is ResultState.Error -> {
                            withContext(mainDispatcher) {
                                binding.showLoading = false
                                Toast.makeText(requireContext(), result.error.message, Toast.LENGTH_LONG).show()
                            }
                        }
                        is ResultState.Loading -> {
                            withContext(mainDispatcher) {
                                binding.showLoading = true
                            }
                        }
                        null -> {}
                    }
                }
            }
        }
    }
}
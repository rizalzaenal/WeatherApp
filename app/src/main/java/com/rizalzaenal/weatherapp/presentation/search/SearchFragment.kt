package com.rizalzaenal.weatherapp.presentation.search

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.rizalzaenal.weatherapp.R
import com.rizalzaenal.weatherapp.databinding.FragmentSearchBinding
import com.rizalzaenal.weatherapp.presentation.State
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : Fragment(R.layout.fragment_search) {
    private var _binding: FragmentSearchBinding? = null
    private val binding: FragmentSearchBinding get() = _binding!!
    private val viewModel: SearchViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSearchBinding.bind(view)
        initRecyclerView()
        collectState()

        binding.edittext.setOnEditorActionListener { v, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH && v.text.isNotBlank()) {
                viewModel.getLocations(v.text.toString())
                true
            } else {
                false
            }
        }
    }

    private fun collectState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.searchState
                    .collect {
                        when (it) {
                            is State.Loading -> {
                                binding.progressBar.visibility = View.VISIBLE
                            }
                            is State.Success -> {
                                binding.progressBar.visibility = View.GONE
                                (binding.rvLocation.adapter as? SearchAdapter)?.setItems(it.data)
                            }
                            is State.Error -> {
                                binding.progressBar.visibility = View.GONE
                                Snackbar.make(binding.root, it.message, Snackbar.LENGTH_LONG)
                                    .setAction(R.string.try_again) {
                                        viewModel.getLocations(binding.edittext.text.toString())
                                    }.show()
                            }
                            else -> {}
                        }
                    }
            }
        }
    }

    private fun initRecyclerView() {
        binding.rvLocation.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = SearchAdapter {
                viewModel.setLatestLocation(it)
                findNavController().popBackStack()
            }

            val itemDecoration = DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL)
            ResourcesCompat.getDrawable(resources, R.drawable.separator, null)?.let {
                itemDecoration.setDrawable(it)
            }
            addItemDecoration(itemDecoration)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
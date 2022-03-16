package com.rizalzaenal.weatherapp.presentation.favorites

import android.os.Bundle
import android.view.View
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
import com.rizalzaenal.weatherapp.databinding.FragmentFavoritesBinding
import com.rizalzaenal.weatherapp.presentation.State
import com.rizalzaenal.weatherapp.presentation.search.SearchAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FavoritesFragment : Fragment(R.layout.fragment_favorites) {
    private var _binding: FragmentFavoritesBinding? = null
    private val binding: FragmentFavoritesBinding get() = _binding!!
    private val viewModel: FavoritesViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentFavoritesBinding.bind(view)
        initRecyclerView()
        collectState()
        viewModel.getFavoriteLocations()
    }

    private fun collectState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.favoriteLocationsState
                    .collect {
                        when (it) {
                            is State.Success -> {
                                (binding.rvFavorites.adapter as? SearchAdapter)?.setItems(it.data)
                            }
                            is State.Error -> {
                                Snackbar.make(binding.root, it.message, Snackbar.LENGTH_LONG)
                                    .setAction(R.string.try_again) {
                                        viewModel.getFavoriteLocations()
                                    }.show()
                            }
                            else -> {}
                        }
                    }
            }
        }
    }

    private fun initRecyclerView() {
        binding.rvFavorites.apply {
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
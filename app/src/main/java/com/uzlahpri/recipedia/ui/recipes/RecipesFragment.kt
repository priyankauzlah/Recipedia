package com.uzlahpri.recipedia.ui.recipes

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.uzlahpri.recipedia.R
import com.uzlahpri.recipedia.adapters.RecipesAdapter
import com.uzlahpri.recipedia.databinding.FragmentRecipesBinding
import com.uzlahpri.recipedia.util.NetworkResult
import com.uzlahpri.recipedia.util.observeOnce
import com.uzlahpri.recipedia.viewmodels.MainViewModel
import com.uzlahpri.recipedia.viewmodels.RecipesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RecipesFragment : Fragment() {

    private var _binding: FragmentRecipesBinding? = null
    private val binding get() = _binding!!

    private lateinit var mainViewModel: MainViewModel
    private lateinit var recipesViewModel: RecipesViewModel
    private val mAdapter by lazy { RecipesAdapter() }
    private lateinit var recipeFragmentBinding: FragmentRecipesBinding
//    private lateinit var mView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        recipesViewModel = ViewModelProvider(requireActivity()).get(RecipesViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecipesBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.mainViewModel = mainViewModel

//        recipeFragmentBinding = FragmentRecipesBinding.inflate(layoutInflater)

        setupRecyclerView()
//        requestApiData()
        readDatabase()

        binding.recipesFab.setOnClickListener{
            findNavController().navigate(R.id.action_navigation_recipes_to_reciipesBottomSheet)
        }

//        return recipeFragmentBinding.root
        return binding.root
    }

    private fun setupRecyclerView() {
        binding.recyclerview.adapter = mAdapter
        binding.recyclerview.layoutManager = LinearLayoutManager(requireContext())
        showShimmerEffect()
    }

    //video ke 47
    private fun readDatabase() {
        lifecycleScope.launch {
            mainViewModel.readRecipes.observeOnce(viewLifecycleOwner, { database ->
                if (database.isNotEmpty()) {
                    Log.d("RecipesFragment", "readDatabase called!")
                    mAdapter.setData(database[0].foodRecipe)
                    hideShimmerEffect()
                } else {
                    requestApiData()
                }
            })
        }
    }

    private fun requestApiData() {
        mainViewModel.getRecipes(recipesViewModel.applyQueries())
        mainViewModel.recipesResponse.observe(viewLifecycleOwner, { response ->
            when (response) {
                is NetworkResult.Success -> {
                    hideShimmerEffect()
                    response.data?.let { mAdapter.setData(it) }
                }
                is NetworkResult.Error -> {
                    hideShimmerEffect()
                    Toast.makeText(
                        requireContext(),
                        response.message.toString(),
                        Toast.LENGTH_SHORT
                    ).show()
                }
                is NetworkResult.Loading -> {
                    showShimmerEffect()

                }
            }
        })
    }

    //video ke 47
    private fun loadDataFromCache() {
        lifecycleScope.launch {
            mainViewModel.readRecipes.observe(viewLifecycleOwner, { database ->
                if (database.isNotEmpty()) {
                    mAdapter.setData(database[0].foodRecipe)
                }
            })
        }
    }

    //munculin shimmer tapi mViewnya diganti recipeFragmentBinding
    private fun showShimmerEffect() {
        binding.recyclerview.showShimmer()
    }

    //hide shimmer tapi mViewnya diganti recipeFragmentBinding
    private fun hideShimmerEffect() {
        binding.recyclerview.hideShimmer()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerview.showShimmer()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}
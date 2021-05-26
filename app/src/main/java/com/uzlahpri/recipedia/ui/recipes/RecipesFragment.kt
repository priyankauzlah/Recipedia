package com.uzlahpri.recipedia.ui.recipes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.uzlahpri.recipedia.R
import com.uzlahpri.recipedia.adapters.RecipesAdapter
import com.uzlahpri.recipedia.databinding.FragmentRecipesBinding
import com.uzlahpri.recipedia.util.NetworkResult
import com.uzlahpri.recipedia.viewmodels.MainViewModel
import com.uzlahpri.recipedia.viewmodels.RecipesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecipesFragment : Fragment() {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var recipesViewModel: RecipesViewModel
    private val mAdapter by lazy { RecipesAdapter() }
    private lateinit var recipeFragmentBinding: FragmentRecipesBinding
    private lateinit var mView: View

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
        mView = inflater.inflate(R.layout.fragment_recipes, container, false)
        recipeFragmentBinding = FragmentRecipesBinding.inflate(layoutInflater)


        setupRecyclerView()
        requestApiData()

        return recipeFragmentBinding.root
        return mView
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


    private fun setupRecyclerView() {
        recipeFragmentBinding.recyclerview.adapter = mAdapter
        recipeFragmentBinding.recyclerview.layoutManager = LinearLayoutManager(requireContext())
        showShimmerEffect()
    }

    //munculin shimmer tapi mViewnya diganti recipeFragmentBinding
    private fun showShimmerEffect() {
        recipeFragmentBinding.recyclerview.showShimmer()
    }

    //hide shimmer tapi mViewnya diganti recipeFragmentBinding
    private fun hideShimmerEffect() {
        recipeFragmentBinding.recyclerview.hideShimmer()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recipeFragmentBinding.recyclerview.showShimmer()
    }
}
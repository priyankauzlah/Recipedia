package com.uzlahpri.recipedia.ui.recipes.bottomsheet

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.uzlahpri.recipedia.R
import com.uzlahpri.recipedia.databinding.RecipesBottomSheetBinding
import com.uzlahpri.recipedia.util.Constants.Companion.DEFAULT_DIET_TYPE
import com.uzlahpri.recipedia.util.Constants.Companion.DEFAULT_MEAL_TYPE
import com.uzlahpri.recipedia.viewmodels.RecipesViewModel
import java.util.*

class ReciipesBottomSheet : BottomSheetDialogFragment() {

    private lateinit var recipesViewModel: RecipesViewModel
    private lateinit var recipesBottomBinding:RecipesBottomSheetBinding

    private var mealTypeChip = DEFAULT_MEAL_TYPE
    private var mealTypeChipId = 0
    private var dietTypeChip = DEFAULT_DIET_TYPE
    private var dietTypeChipId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        recipesViewModel = ViewModelProvider(requireActivity()).get(RecipesViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val mView = inflater.inflate(R.layout.recipes_bottom_sheet, container, false)
        recipesBottomBinding = RecipesBottomSheetBinding.inflate(layoutInflater)

        recipesViewModel.readMealAndDietType.asLiveData().observe(viewLifecycleOwner, { value ->
            mealTypeChip = value.selectedMealType
            dietTypeChip = value.selectedDietType
            updateChip(value.selectedMealTypeId, recipesBottomBinding.mealTypeChipGroup)
            updateChip(value.selectedDietTypeId, recipesBottomBinding.dietTypeChipGroup)
        })

        //video 59
        recipesBottomBinding.mealTypeChipGroup.setOnCheckedChangeListener { group, selectedChipId ->
            val chip = group.findViewById<Chip>(selectedChipId)
            val selectedMealType = chip.text.toString().toLowerCase(Locale.ROOT)
            mealTypeChip = selectedMealType
            mealTypeChipId = selectedChipId
        }
        recipesBottomBinding.dietTypeChipGroup.setOnCheckedChangeListener { group, selectedChipId ->
            val chip = group.findViewById<Chip>(selectedChipId)
            val selectedDietType = chip.text.toString().toLowerCase(Locale.ROOT)
            dietTypeChip = selectedDietType
            dietTypeChipId = selectedChipId
        }

        recipesBottomBinding.applyBtn.setOnClickListener {
            recipesViewModel.saveMealAndDietType(
                mealTypeChip,
                mealTypeChipId,
                dietTypeChip,
                dietTypeChipId
            )
            //tergantung di action di my_nav
            val action = ReciipesBottomSheetDirections.actionReciipesBottomSheetToNavigationRecipes(true)
                    findNavController().navigate(action)
        }

        return mView
    }

    private fun updateChip(chipId: Int, chipGroup: ChipGroup) {
        if (chipId != 0) {
            try {
                chipGroup.findViewById<Chip>(chipId).isChecked = true
            } catch (e: Exception) {
                Log.d("RecipeBottomSheet", e.message.toString())
            }
        }
    }

}
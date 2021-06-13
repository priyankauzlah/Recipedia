package com.uzlahpri.recipedia.models

import com.google.gson.annotations.SerializedName

data class FoodRecipe(
    @SerializedName("results")
    val resultRecipes: List<ResultRecipe>
)
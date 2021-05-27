package com.uzlahpri.recipedia.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.uzlahpri.recipedia.models.FoodRecipe
import com.uzlahpri.recipedia.util.Constants.Companion.RECIPES_TABLE

@Entity(tableName = RECIPES_TABLE)
class RecipesEntity(
    var foodRecipe: FoodRecipe
) {
    @PrimaryKey(autoGenerate = false)
    var id: Int= 0
}
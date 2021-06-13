package com.uzlahpri.recipedia.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.uzlahpri.recipedia.models.ResultRecipe
import com.uzlahpri.recipedia.util.Constants.Companion.FAVORITE_RECIPES_TABLE

@Entity(tableName = FAVORITE_RECIPES_TABLE)
class FavoritesEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var resultRecipe: ResultRecipe
)
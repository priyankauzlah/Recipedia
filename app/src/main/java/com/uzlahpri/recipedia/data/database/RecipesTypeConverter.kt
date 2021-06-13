package com.uzlahpri.recipedia.data.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.uzlahpri.recipedia.models.FoodRecipe
import com.uzlahpri.recipedia.models.ResultRecipe

class RecipesTypeConverter {

    var gson = Gson()
    //untuk mempertahankan tipe costum tertentu kedalam data base
    @TypeConverter
    fun foodRecipeToString(foodRecipe: FoodRecipe): String {
        return gson.toJson(foodRecipe)
    }

    @TypeConverter
    fun stringToFoodRecipe(data: String): FoodRecipe {
        val listType = object : TypeToken<FoodRecipe>() {}.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun resultToString(resultRecipe: ResultRecipe): String {
        return gson.toJson(resultRecipe)
    }

    @TypeConverter
    fun stringToResult(data: String): ResultRecipe {
        val listType = object : TypeToken<ResultRecipe>() {}.type
        return gson.fromJson(data, listType)
    }

}
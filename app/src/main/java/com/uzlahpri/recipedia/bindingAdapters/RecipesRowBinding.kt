package com.uzlahpri.recipedia.bindingAdapters

import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.navigation.findNavController
import coil.load
import com.uzlahpri.recipedia.R
import com.uzlahpri.recipedia.models.Result
import com.uzlahpri.recipedia.ui.recipes.RecipesFragmentDirections
import java.lang.Exception

class RecipesRowBinding {
    companion object {

        @BindingAdapter("onRecipeClickListener")
        @JvmStatic
        fun onRecipeClickListener(recipeRowLayout: ConstraintLayout, result: Result){
            Log.d("onRecipeClickListener", "CALLED")
            recipeRowLayout.setOnClickListener{
                try {
                    val action =
                        RecipesFragmentDirections.actionNavigationRecipesToDetailsActivity(result)
                    recipeRowLayout.findNavController().navigate(action)
                }catch (e: Exception){
                    Log.d("onRecipeClickListener", e.toString())
                }
            }
        }

        @BindingAdapter("loadImageFromUrl")
        @JvmStatic
        fun loadImageFromUrl(imageView: ImageView, imageUrl: String) {
            imageView.load(imageUrl) {
                crossfade(600)
                error(R.drawable.ic_error_placeholder)
            }
        }

        //untuk biar muncul berapa orang yg nge like
        @BindingAdapter("setNumberOfLikes")
        @JvmStatic
        fun setNumberOfLikes(textView: TextView, likes: Int) {
            textView.text = likes.toString()
        }

        // biar muncul yang likesnya jadi text
        @BindingAdapter("setNumberOfMinutes")
        @JvmStatic
        fun setNumberOfMinutes(textView: TextView, minutes: Int) {
            textView.text = minutes.toString()
        }

        //biar warna ijo pas vegan
        @BindingAdapter("applyVeganColor")
        @JvmStatic
        fun applyVeganColor(view: View, vegan: Boolean) {
            if (vegan) {
                when (view) {
                    //buat ngasi warna ijo pas dia vegan di text
                    is TextView -> {
                        view.setTextColor(
                            ContextCompat.getColor(
                                view.context,
                                R.color.green_secondary
                            )
                        )
                    }
                    //buat ngasi warna ijo pas dia vegan di logonya
                    is ImageView -> {
                        view.setColorFilter(
                            ContextCompat.getColor(
                                view.context,
                                R.color.green_secondary
                            )
                        )
                    }
                }
            }
        }
    }
}
package com.uzlahpri.recipedia.ui.overview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import coil.load
import com.uzlahpri.recipedia.R
import com.uzlahpri.recipedia.models.ResultRecipe
import com.uzlahpri.recipedia.util.Constants.Companion.RECIPE_RESULT_KEY
import kotlinx.android.synthetic.main.fragment_overview.view.*
import org.jsoup.Jsoup

class OverviewFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_overview, container, false)

        val args = arguments
        val myBundle: ResultRecipe? = args?.getParcelable(RECIPE_RESULT_KEY)

        view.main_imageView.load(myBundle?.image)
        view.title_textView.text = myBundle?.title
        view.likes_textView.text = myBundle?.aggregateLikes.toString()
        view.time_textView.text = myBundle?.readyInMinutes.toString()
        myBundle?.summary.let {
            val summary = Jsoup.parse(it).text()
            view.summary_textView.text = summary
        }

        if(myBundle?.vegetarian == true){
            view.vegetarian_imageView.setColorFilter(ContextCompat.getColor(requireContext(), R.color.green_primary))
            view.vegetarian_textView.setTextColor(ContextCompat.getColor(requireContext(), R.color.green_primary))
        }

        if(myBundle?.vegan == true){
            view.vegan_imageView.setColorFilter(ContextCompat.getColor(requireContext(), R.color.green_primary))
            view.vegan_textView.setTextColor(ContextCompat.getColor(requireContext(), R.color.green_primary))
        }

        if(myBundle?.glutenFree == true){
            view.gluten_free_imageView.setColorFilter(ContextCompat.getColor(requireContext(), R.color.green_primary))
            view.gluten_free_textView.setTextColor(ContextCompat.getColor(requireContext(), R.color.green_primary))
        }

        if(myBundle?.dairyFree == true){
            view.dairy_free_imageView.setColorFilter(ContextCompat.getColor(requireContext(), R.color.green_primary))
            view.dairy_free_textView.setTextColor(ContextCompat.getColor(requireContext(), R.color.green_primary))
        }

        if(myBundle?.veryHealthy == true){
            view.healthy_imageView.setColorFilter(ContextCompat.getColor(requireContext(), R.color.green_primary))
            view.healthy_textView.setTextColor(ContextCompat.getColor(requireContext(), R.color.green_primary))
        }

        if(myBundle?.cheap == true){
            view.cheap_imageView.setColorFilter(ContextCompat.getColor(requireContext(), R.color.green_primary))
            view.cheap_textView.setTextColor(ContextCompat.getColor(requireContext(), R.color.green_primary))
        }

        return view
    }
}
package com.uzlahpri.recipedia

import android.os.Bundle
import android.os.Parcelable
import android.os.ResultReceiver
import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.uzlahpri.recipedia.R
import com.uzlahpri.recipedia.models.ResultRecipe
import java.io.Serializable
import java.lang.UnsupportedOperationException
import kotlin.Int
import kotlin.Suppress

public class RecipesFragmentDirections private constructor() {
    private data class ActionRecipesFragmentToDetailsActivity(
        public val result: ResultRecipe
    ) : NavDirections {
        public override fun getActionId(): Int = R.id.action_recipesFragment_to_detailsActivity

        @Suppress("CAST_NEVER_SUCCEEDS")
        public override fun getArguments(): Bundle {
            val result = Bundle()
            if (Parcelable::class.java.isAssignableFrom(Result::class.java)) {
                result.putParcelable("result", this.result as Parcelable)
            } else if (Serializable::class.java.isAssignableFrom(Result::class.java)) {
                result.putSerializable("result", this.result as Serializable)
            } else {
                throw UnsupportedOperationException(Result::class.java.name +
                        " must implement Parcelable or Serializable or must be an Enum.")
            }
            return result
        }
    }

    public companion object {
        public fun actionRecipesFragmentToRecipesBottomSheet(): NavDirections =
            ActionOnlyNavDirections(R.id.action_recipesFragment_to_recipesBottomSheet)

        public fun actionRecipesFragmentToDetailsActivity(result: ResultRecipe): NavDirections =
            ActionRecipesFragmentToDetailsActivity(result)
    }
}

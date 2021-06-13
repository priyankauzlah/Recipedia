package com.uzlahpri.recipedia

import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.NavArgs
import com.uzlahpri.recipedia.models.ResultRecipe
import java.io.Serializable
import java.lang.IllegalArgumentException
import java.lang.UnsupportedOperationException
import kotlin.Suppress
import kotlin.jvm.JvmStatic

public data class DetailsActivityArgs(
    public val result: ResultRecipe
) : NavArgs {
    @Suppress("CAST_NEVER_SUCCEEDS")
    public fun toBundle(): Bundle {
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

    public companion object {
        @JvmStatic
        public fun fromBundle(bundle: Bundle): DetailsActivityArgs {
            bundle.setClassLoader(DetailsActivityArgs::class.java.classLoader)
            val __result : ResultRecipe?
            if (bundle.containsKey("result")) {
                if (Parcelable::class.java.isAssignableFrom(Result::class.java) ||
                    Serializable::class.java.isAssignableFrom(Result::class.java)) {
                    __result = bundle.get("result") as ResultRecipe?
                } else {
                    throw UnsupportedOperationException(Result::class.java.name +
                            " must implement Parcelable or Serializable or must be an Enum.")
                }
                if (__result == null) {
                    throw IllegalArgumentException("Argument \"result\" is marked as non-null but was passed a null value.")
                }
            } else {
                throw IllegalArgumentException("Required argument \"result\" is missing and does not have an android:defaultValue")
            }
            return DetailsActivityArgs(__result)
        }
    }
}

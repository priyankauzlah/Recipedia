package com.uzlahpri.recipedia.util

import androidx.recyclerview.widget.DiffUtil
import com.uzlahpri.recipedia.models.Result

class RecipesDiffUtil(
    private val oldList: List<Result>,
    private val newList: List<Result>
) : DiffUtil.Callback() {

    //get list lama
    override fun getOldListSize(): Int {
        return oldList.size
    }

    //get list baru
    override fun getNewListSize(): Int {
        return newList.size
    }

    //untuk milih apakah 2 obj ada di old list sama new list ga
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] === newList[newItemPosition]
    }

    // check apakah ada 2 item yg isinya samaan ga
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }


}
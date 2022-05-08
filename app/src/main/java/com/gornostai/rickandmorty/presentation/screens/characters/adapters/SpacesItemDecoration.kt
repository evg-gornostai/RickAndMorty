package com.gornostai.rickandmorty.presentation.screens.characters.adapters

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class SpacesItemDecoration(private val space: Int): RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        if (parent.getChildLayoutPosition(view) == 0 || parent.getChildLayoutPosition(view) == 1){
            outRect.top = space
        } else {
            outRect.top = 0
        }

        if (parent.getChildLayoutPosition(view)%2 == 0){
            outRect.right = space/2
            outRect.left = space
        } else {
            outRect.right = space
            outRect.left = space/2
        }

        outRect.bottom = space
    }
}
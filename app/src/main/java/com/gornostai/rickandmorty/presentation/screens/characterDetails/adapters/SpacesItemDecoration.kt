package com.gornostai.rickandmorty.presentation.screens.characterDetails.adapters

import android.content.Context
import android.graphics.Rect
import android.util.TypedValue
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class SpacesItemDecoration(
    paddingDip: Float,
    context: Context
) : RecyclerView.ItemDecoration() {

    val metrics = context.resources.displayMetrics
    val margin = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, paddingDip, metrics).toInt()

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.top = 0
        outRect.right = margin
        outRect.left = margin
        outRect.bottom = margin
    }
}
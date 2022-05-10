package com.gornostai.rickandmorty.presentation.screens.episodeDetails.adapters

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

        if (parent.getChildLayoutPosition(view) == 0 || parent.getChildLayoutPosition(view) == 1) {
            outRect.top = margin
        } else {
            outRect.top = 0
        }

        if (parent.getChildLayoutPosition(view) % 2 == 0) {
            outRect.right = margin / 2
            outRect.left = margin
        } else {
            outRect.right = margin
            outRect.left = margin / 2
        }

        outRect.bottom = margin
    }
}
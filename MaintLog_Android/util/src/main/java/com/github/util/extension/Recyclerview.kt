package com.github.util.extension

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnScrollListener
import com.github.util.R

fun RecyclerView.init(
    context: Context,
    adapter: RecyclerView.Adapter<*>,
    @RecyclerView.Orientation orientation: Int = RecyclerView.VERTICAL,
) {
    val dividerItemDecoration =
        DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
    dividerItemDecoration.setDrawable(
        ContextCompat.getDrawable(
            context,
            R.drawable.divider_item_decoration
        )!!
    )

    this.layoutManager = LinearLayoutManager(context, orientation, false)
//    this.addItemDecoration(dividerItemDecoration)
    this.adapter = adapter
}

fun RecyclerView.setFocusListener(focusItemBlock: (pos: Int) -> Unit) {
    addOnScrollListener(object : OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)

            if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                if (layoutManager is LinearLayoutManager) {
                    (layoutManager as LinearLayoutManager).let { manager ->
                        val firstPos = manager.findFirstVisibleItemPosition()
                        recyclerView.getGlobalVisibleRect(Rect())
                        manager.findViewByPosition(firstPos)?.let { view ->
                            if (getVisibleHeightPercentage(view) > 50) {
                                focusItemBlock(firstPos)
                            } else {
                                focusItemBlock(firstPos + 1)
                            }
                        }
                    }
                }
            }
        }
    })
}

// 리사이클러뷰 Item visible 퍼센트 조회
private fun getVisibleHeightPercentage(view: View): Int {
    val itemRect = Rect()
    val isParentViewEmpty = view.getLocalVisibleRect(itemRect)

    // Find the height of the item.
    val visibleHeight = itemRect.height().toDouble()
    val height = view.measuredHeight.toDouble()
    val viewVisibleHeightPercentage = visibleHeight / height * 100
    return if (isParentViewEmpty) {
        viewVisibleHeightPercentage.toInt()
    } else {
        0
    }
}

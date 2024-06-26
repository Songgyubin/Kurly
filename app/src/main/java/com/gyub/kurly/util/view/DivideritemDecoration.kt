package com.gyub.kurly.util.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import androidx.annotation.ColorRes
import androidx.recyclerview.widget.RecyclerView

/**
 * 구분선 데코레이션
 *
 * @property dividerHeight 구분선 높이
 * @property spaceHeight 아이템 간격
 * @param context
 * @param color 컬러 리소스 Id
 *
 * @author   Gyub
 * @created  2024/04/01
 */
class DividerItemDecoration(
    context: Context,
    private val dividerHeight: Int,
    @ColorRes color: Int,
    private val spaceHeight: Int
) : RecyclerView.ItemDecoration() {
    private val paint = Paint()

    init {
        paint.color = context.resources.getColor(color, null)
        paint.style = Paint.Style.FILL
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val left = parent.paddingLeft
        val right = parent.width - parent.paddingRight

        for (i in 0 until parent.childCount) {
            val child = parent.getChildAt(i)
            val params = child.layoutParams as RecyclerView.LayoutParams

            val top = child.bottom + params.bottomMargin
            val bottom = top + dividerHeight

            c.drawRect(left.toFloat(), top.toFloat(), right.toFloat(), bottom.toFloat(), paint)
        }
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        outRect.bottom = spaceHeight
    }
}
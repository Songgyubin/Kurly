package com.gyub.kurly.util.view

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Vertical Margin Item Decoration
 *
 * @author   Gyub
 * @created  2024/04/01
 *
 * @property verticalMargin 아이템의 상/하 여백 크기
 * @property firstItemTopMargin 첫 번째 아이템의 시작 여백 크기, 기본값 NOT_SET(-1)
 * @property lastItemMargin 마지막 아이템의 끝 여백 크기, 기본값 NOT_SET(-1)
 */
class VerticalMarginItemDecoration(
    private val verticalMargin: Int,
    private val firstItemTopMargin: Int = NOT_SET,
    private val lastItemMargin: Int = NOT_SET
) : RecyclerView.ItemDecoration() {

    companion object {
        private const val NOT_SET = -1
    }

    /**
     * 아이템의 여백을 설정
     *
     * @param outRect 아이템의 여백을 설정하기 위한 Rect 객체
     * @param view 현재 처리 중인 아이템의 View
     * @param parent RecyclerView 부모 View
     * @param state RecyclerView 상태 정보
     */
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        with(outRect) {
            top = if (isFirstItem(parent, view) && firstItemTopMargin != NOT_SET) {
                firstItemTopMargin
            } else {
                0
            }

            bottom = if (lastItemMargin == NOT_SET) {
                verticalMargin
            } else {

                if (isLastItem(parent, view, state)) {
                    lastItemMargin
                } else {
                    verticalMargin
                }
            }
        }
    }

    private fun isFirstItem(parent: RecyclerView, view: View) =
        parent.getChildAdapterPosition(view) == 0

    private fun isLastItem(parent: RecyclerView, view: View, state: RecyclerView.State) =
        parent.getChildAdapterPosition(view) == state.itemCount - 1
}

package com.gyub.kurly.util.extension

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

/**
 * View 관련 확장 함수
 *
 * @author   Gyub
 * @created  2024/04/01
 */

/**
 * DataBinding Inflate Extension
 *
 * @param T Type
 * @param viewGroup ViewGroup
 * @param layoutRes inflate 시킬 Resource
 * @return
 */
fun <T : ViewDataBinding> viewBind(viewGroup: ViewGroup, layoutRes: Int): T {
    return DataBindingUtil.inflate(
        LayoutInflater.from(viewGroup.context),
        layoutRes,
        viewGroup,
        false
    )
}

/**
 *  ViewDataBinding 업데이트 확장함수
 *
 * @param block
 */
inline fun <T : ViewDataBinding> T.executeAfter(block: T.() -> Unit) {
    block()
    executePendingBindings()
}
package com.gyub.core.common.util.extension

/**
 * Boolean형 확장 함수
 *
 * @author   Gyub
 * @created  2024/03/30
 */

/**
 * null이면 default Value 반환
 */
fun Boolean?.orDefault(defaultValue: Boolean) =
    this ?: defaultValue
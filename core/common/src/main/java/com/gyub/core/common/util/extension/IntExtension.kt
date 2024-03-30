package com.gyub.common.util.extension

/**
 * Int 형에 대한 확장 함수
 *
 * @author   Gyub
 * @created  2024/03/30
 */

/**
 * null이면 defaultValue를 반환
 */
fun Int?.orDefault(defaultValue: Int = 0) =
    this ?: defaultValue
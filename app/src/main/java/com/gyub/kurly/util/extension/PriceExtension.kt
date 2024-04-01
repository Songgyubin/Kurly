package com.gyub.kurly.util.extension

import android.content.Context
import java.text.DecimalFormat

/**
 * 가격 관련 확장 함수
 *
 * @author   Gyub
 * @created  2024/04/01
 */


/**
 * 할인율 계산 후 String 반환
 *
 * @receiver 원가
 * @param discountedPrice 할인된 가격
 * @return 계산된 할인율 String
 * ex) 30%
 */
fun Int.calcDiscountPercent(discountedPrice: Int): String {
    val discountPercent = (this - discountedPrice / this) * 100

    return "$discountPercent%"
}

/**
 * Int 값을 가격 포맷 (###,###)으로 변경하고, 원화 문자열을 더한 가격을 반환
 * @param context 컨텍스트
 * @param allowZero 0 값을 허용할지 여부를 지정(기본값은 false, false <- 설정할 경우 "0" 값은 빈 문자열로 반환)
 * @return 가격 포맷이 적용된 원화 문자열
 */
fun Int.toFormattedPriceString(context: Context, allowZero: Boolean = false): String {
    if (this == 0 && !allowZero) {
        return ""
    }
    val formatter = DecimalFormat("###,###")
    return context.getString(com.gyub.kurly.R.string.won, formatter.format(this))
}
package com.gyub.kurly.ui.section.event

/**
 * 찜 이벤트 인터페이스
 * UI 모델 및 ViewModel 에서 구현
 *
 * @author   Gyub
 * @created  2024/04/02
 */
interface WishEvent {
    var onWishClicked: (Int, Boolean) -> Unit
}

/**
 * 찜 이벤트 적용
 *
 * @param T [WishEvent]를 구현하는 Ui Model
 * @param onWishClicked 찜 클릭 이벤트
 * @return 찜 이벤트 콜백이 적용된 [T] 반환
 */
fun <T> T.applyOnWishClicked(onWishClicked: (Int, Boolean) -> Unit): T where T : WishEvent {
    return apply {
        this.onWishClicked = onWishClicked
    }
}
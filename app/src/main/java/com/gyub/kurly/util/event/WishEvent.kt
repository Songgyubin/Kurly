package com.gyub.kurly.util.event

/**
 * 찜 이벤트 인터페이스
 * 찜 동작이 필요한 UI Model에서 구현
 *
 * @author   Gyub
 * @created  2024/04/01
 */
//TODO: UI Model에 적용
interface WishEvent {
    var onWishClicked: (Long, Boolean) -> Unit
}

/**
 * 찜 이벤트 적용
 *
 * @param T [WishEvent]를 구현하는 Ui Model
 * @param onWishClicked 찜 클릭 이벤트
 * @return 찜 이벤트 콜백이 적용된 [T] 반환
 */
fun <T> T.applyOnWishClicked(onWishClicked: (Long, Boolean) -> Unit): T where T : WishEvent {
    return apply {
        this.onWishClicked = onWishClicked
    }
}
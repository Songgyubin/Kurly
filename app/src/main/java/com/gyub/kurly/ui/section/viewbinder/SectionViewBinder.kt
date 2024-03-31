package com.gyub.kurly.ui.section.viewbinder

import android.graphics.Paint
import android.graphics.Typeface
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.gyub.kurly.R
import com.gyub.kurly.util.extension.calcDiscountPercent
import com.gyub.kurly.util.extension.toFormattedPriceString

/**
 * 섹션 공통 뷰 바인더
 *
 * @author   Gyub
 * @created  2024/04/01
 */
class SectionViewBinder {

    /**
     * 상품 이미지 세팅
     *
     * @param view [AppCompatImageView]
     * @param imageUrl 상품 이미지 url
     */
    fun setProductImage(view: AppCompatImageView, imageUrl: String) {
        Glide.with(view)
            .load(imageUrl)
            .apply(
                RequestOptions()
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                    .format(DecodeFormat.PREFER_ARGB_8888)
            )
            .into(view)
    }

    /**
     * 찜 아이콘 세팅
     *
     * @param view [AppCompatImageView]
     * @param isWish 찜 여부
     */
    fun setWishIcon(view: AppCompatImageView, isWish: Boolean) {
        val wishResource = if (isWish) {
            WishIcon.WISH.resourceId
        } else {
            WishIcon.NOT_WISH.resourceId
        }

        view.setImageResource(wishResource)
    }

    /**
     * 섹션 제목 세팅
     *
     * @param view [AppCompatTextView]
     * @param title 섹션 제목
     */
    fun setSectionTitle(view: AppCompatTextView, title: String) {
        view.run {
            text = title
        }
    }

    /**
     * 상품 제목 세팅
     *
     * @param view [AppCompatTextView]
     * @param title 상품 제목
     */
    fun setProductTitle(view: AppCompatTextView, title: String) {
        view.run {
            text = title
        }
    }

    /**
     * 상품 할인율 세팅
     *
     * @param view [AppCompatTextView]
     * @param originalPrice 상품 원가
     * @param discountedPrice 할인된 가격
     */
    fun setDiscountPercent(
        view: AppCompatTextView,
        originalPrice: Int,
        discountedPrice: Int
    ) {
        view.run {
            isVisible = discountedPrice > 0

            text = originalPrice.calcDiscountPercent(discountedPrice)
        }
    }

    /**
     * 판매 가격 세팅
     *
     * @param view [AppCompatTextView]
     * @param originalPrice 원가
     * @param discountedPrice 할인된 가격
     */
    fun setSalePrice(
        view: AppCompatTextView,
        originalPrice: Int,
        discountedPrice: Int
    ) {
        view.run {
            when (discountedPrice > 0) {
                true -> {
                    typeface = Typeface.DEFAULT_BOLD
                    text = discountedPrice.toFormattedPriceString(context, false)
                }

                false -> {
                    typeface = Typeface.DEFAULT
                    text = originalPrice.toFormattedPriceString(context, false)
                }
            }
        }
    }

    /**
     * 원가 세팅
     *
     * @param view [AppCompatTextView]
     * @param originalPrice 상품 원가
     * @param discountedPrice 할인된 가격
     */
    fun setOriginalPrice(
        view: AppCompatTextView,
        originalPrice: Int,
        discountedPrice: Int
    ) {
        view.run {
            isVisible = discountedPrice > 0

            paintFlags = Paint.STRIKE_THRU_TEXT_FLAG

            text = originalPrice.toFormattedPriceString(context, false)
        }
    }


    enum class WishIcon(val resourceId: Int) {
        WISH(R.drawable.ic_btn_heart_on),
        NOT_WISH(R.drawable.ic_btn_heart_off)
    }
}
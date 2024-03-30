package com.gyub.core.common.di

import javax.inject.Qualifier

/**
 * 코루틴 Dispatcher 의존성 Annotation 식별자
 *
 * @author   Gyub
 * @created  2024/03/30
 */
@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class IoDispatcher
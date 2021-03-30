package com.howtostudykorean.coroutine

import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

@Singleton
open class CoroutineContexts @Inject constructor() {
    open val Main: CoroutineContext = Dispatchers.Main
    open val Default: CoroutineContext = Dispatchers.Default
}
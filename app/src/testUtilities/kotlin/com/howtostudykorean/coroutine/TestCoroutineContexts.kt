package com.howtostudykorean.coroutine

import kotlinx.coroutines.Dispatchers

class TestCoroutineContexts : CoroutineContexts() {

    override val Main = Dispatchers.Unconfined
    override val Default = Dispatchers.Unconfined
}
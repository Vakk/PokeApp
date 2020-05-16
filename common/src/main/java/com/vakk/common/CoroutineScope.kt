package com.vakk.common

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

fun CoroutineScope.launchSafe(
    onSuccess: () -> Unit = {},
    onComplete: () -> Unit = {},
    onError: (Throwable) -> Unit = { it.printStackTrace() },
    action: suspend CoroutineScope.() -> Unit
) = launch {
    try {
        action(this)
        onSuccess()
    } catch (e: Throwable) {
        onError(e)
    }
    onComplete()
}
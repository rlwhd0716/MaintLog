package com.github.util.extension

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.concurrent.atomic.AtomicBoolean

class PollingCoroutine(
    private val delay: Long = 0,
    private val period: Long = 1000,
    private val coroutineScope: CoroutineScope = GlobalScope,
    action: suspend PollingCoroutine.() -> Unit,
) {
    private val keepRunning = AtomicBoolean(true)
    private var job: Job? = null
    private val tryAction = suspend {
        try {
            action()
        } catch (e: Throwable) {
            e.printStackTrace()
        }
    }
    private var pollingCount = 0

    /**
     * delay 시간후 폴링 시작
     * period 시간 만큼 딜레이
     */
    fun start() {
        keepRunning.set(true)
        job = coroutineScope.launch {
            delay(delay)
            while (keepRunning.get()) {
                tryAction()
                delay(period)
            }
        }
    }

    /**
     * Initiates an orderly shutdown, where if the timer task is currently running,
     * we will let it finish, but not run it again.
     * Invocation has no additional effect if already shut down.
     */
    fun shutdown() {
        keepRunning.set(false)
    }

    /**
     * Immediately stops the timer task, even if the job is currently running,
     * by cancelling the underlying Koros Job.
     */
    fun cancel() {
        shutdown()
        job?.cancel("cancel() called")
    }

    fun isRunning() = keepRunning.get()
}

fun polling(
    delay: Long = 0,
    period: Long = 1000,
    coroutineScope: CoroutineScope = GlobalScope,
    action: suspend PollingCoroutine.() -> Unit,
) = PollingCoroutine(delay, period, coroutineScope, action).apply { start() }

fun CoroutineScope.polling(
    delay: Long = 0,
    period: Long = 1000,
    action: suspend PollingCoroutine.() -> Unit,
) = PollingCoroutine(delay, period, this, action).apply { start() }

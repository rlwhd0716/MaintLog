package com.github.util.base

import android.content.Intent
import android.util.Log
import android.view.View
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.util.BuildConfig
import com.github.util.R
import com.github.util.event.Empty
import com.github.util.event.EventLiveData
import com.github.util.extension.logE
import com.github.util.provider.ResourceProvider.getString
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

open class BaseViewModel : ViewModel(), LifecycleObserver {
    val TAG = javaClass.simpleName
    val toast = EventLiveData<Any>()
    val startActivity = EventLiveData<Pair<Class<*>, Boolean>>()
    val startActivityIntent = EventLiveData<Triple<Intent.() -> Unit, Class<*>, Boolean>>()
    val setResult = EventLiveData<Pair<Intent.() -> Unit, Boolean>>()
    val backPressed = EventLiveData<Empty>()
    val isLoading = MutableLiveData<Boolean>()
    val finish = EventLiveData<Empty>()
    val finishAffinity = EventLiveData<Empty>()
    val isKeyboard = EventLiveData<Boolean>()
    val showDialTel = EventLiveData<String>()
    val copyData = EventLiveData<String>()

    val showAlertDialog = EventLiveData<
            Triple<Pair<String, String>, // <타이틀, 메세지>
                    Pair<String, (() -> Unit)>?, // <확인버튼명, 확인시 실행> 2번째를 null 넣으면 확인버튼 사라짐.
                    Pair<String, (() -> Unit)>?>>() // <취소버튼명, 취소시 실행> 3번째를 null 넣으면 취소버튼 사라짐.

    fun finish(view: View) {
        finish.postEvent(Empty.Call)
    }

    protected fun <T : Any> Flow<UsecaseResult<T>>.getResult(
        isShowProgress: Boolean = true,
        loading: (() -> Unit)? = null,
        complete: (() -> Unit)? = null,
        error: ((String) -> Unit)? = null,
        success: (T?) -> Unit,
    ) {
        onEach {
            try {
                when (it) {
                    UsecaseResult.Loading -> {
                        loading?.run { invoke() }
                        if (isShowProgress) isLoading.postValue(true)
                    }

                    UsecaseResult.Complete -> {
                        complete?.run { invoke() }
                        if (isShowProgress) isLoading.postValue(false)
                    }

                    is UsecaseResult.Success -> {
                        logE("success::: $success")
                        it.data.also(success)
                    }

                    is UsecaseResult.Error -> {
                        logE("exception::: ${it.exception}")
                        error?.run { invoke(it.exception) }
                        if (BuildConfig.DEBUG) {
                            if (it.exception.contains("failed to connect to"))
                                toast.postEvent("서버가 꺼져있음.")
                            else
                                toast.postEvent(getString(R.string.msg_error_network_connection_2))
                        } else {
                            toast.postEvent(getString(R.string.msg_error_network_connection_1))
                        }

                    }
                }
            } catch (e: Exception) {
                Log.e(TAG, "getResult(Network error) - ${e.message}")
            }
        }.launchIn(viewModelScope)
    }

    protected fun toastShow(msg: String) {
        toast.postEvent(msg)
    }
}
package com.github.util.base

import android.content.Intent
import android.content.res.Configuration
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.view.Window
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.lifecycle.ViewModelLazy
import com.github.util.BR
import com.github.util.extension.copyData
import com.github.util.extension.hideKeyboard
import com.github.util.extension.logE
import com.github.util.extension.showDialTel
import com.github.util.extension.showKeyboard
import com.github.util.extension.toastShow
import com.github.util.ui.ProgressDialogUtil
import com.github.util.ui.showAlertDialog
import java.lang.reflect.ParameterizedType

abstract class BaseActivity<B : ViewDataBinding, VM : BaseViewModel>(val layoutId: Int) :
    AppCompatActivity(layoutId) {
    val TAG = javaClass.simpleName

    protected val binding by lazy {
        DataBindingUtil
            .bind<B>(
                (window.decorView.findViewById(android.R.id.content) as ViewGroup)
                    .getChildAt(0)
            )
    }

    protected val viewModel by ViewModelLazy(
        ((javaClass.genericSuperclass as ParameterizedType?)?.actualTypeArguments?.get(1) as Class<VM>).kotlin,
        { viewModelStore },
        { defaultViewModelProviderFactory },
        { defaultViewModelCreationExtras }
    )

    protected open var bindingApply: (B.() -> Unit)? = null
    protected open var vmApply: (VM.() -> Unit)? = null
    protected open var isTwoTouchAppExit = false
    private var backKeyPressedTime : Long= 0
    private val BACKPRESS_INTERVAL = 2000

    override fun onCreate(savedInstanceState: Bundle?) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE) // toolbar 영역 Off
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO) // 야간모드 Off

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        with(binding) {
            this?.lifecycleOwner = this@BaseActivity
            this?.setVariable(BR.vm, viewModel)
            this?.root?.let {
                applySystemBarInsets(it)
            }
            this?.let { bindingApply?.invoke(it) }
        }

        with(viewModel) viewModel@{
            lifecycle.addObserver(this@viewModel)
            toast.observe(this@BaseActivity) { toastShow(it) }
            finish.observe(this@BaseActivity) { finish() }
            finishAffinity.observe(this@BaseActivity) { finishAffinity() }
            backPressed.observe(this@BaseActivity) { onBackPressed() }
            startActivity.observe(this@BaseActivity) {
                startActivity(Intent(this@BaseActivity, it.first))
                if (it.second) finish()
            }
            startActivityIntent.observe(this@BaseActivity) {
                startActivity(Intent(this@BaseActivity, it.second).apply(it.first))
                if (it.third) finish()
            }
            setResult.observe(this@BaseActivity) {
                intent.apply(it.first)
                setResult(if(it.second) RESULT_OK else RESULT_CANCELED, intent)
                finish()
            }
            isLoading.observe(this@BaseActivity) {
                if (it) ProgressDialogUtil.show(this@BaseActivity)
                else ProgressDialogUtil.dismiss()
            }
            isKeyboard.observe(this@BaseActivity) {
                if (it) {
                    showKeyboard()
                } else {
                    hideKeyboard()
                }
            }
            showAlertDialog.observe(this@BaseActivity) {
                showAlertDialog(
                    title = it.first.first,
                    message = it.first.second,
                    layout = true,
                    ok = if (it.second != null) it.second?.first else null,
                    okAction = if (it.second != null) {
                        it.second?.second
                    } else null,
                    cancel = if (it.third != null) it.third?.first else null,
                    cancelAction = if (it.third != null) {
                        it.third?.second
                    } else null
                )
            }
            showDialTel.observe(this@BaseActivity) {
                showDialTel(it)
            }
            copyData.observe(this@BaseActivity) {
                copyData(it)
            }

            vmApply?.invoke(this)
            onBackPressedDispatcher.addCallback(this@BaseActivity, onAbsBackPressedCallback)
        }
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onDestroy() {
        ProgressDialogUtil.destroy()
        super.onDestroy()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)

        val locale = newConfig.locales[0]

        Log.d("LocaleChange", "언어 변경 감지됨: ${locale.toLanguageTag()}")
    }

    /**
     * 시스템 바 Inset 추가
     * Android SDK 35이상에서 필요.
     **/
    private fun applySystemBarInsets(rootView: android.view.View) {
        val initialPaddingLeft = rootView.paddingLeft
        val initialPaddingTop = rootView.paddingTop
        val initialPaddingRight = rootView.paddingRight
        val initialPaddingBottom = rootView.paddingBottom

        ViewCompat.setOnApplyWindowInsetsListener(rootView) { view, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())

            view.updatePadding(
                left = initialPaddingLeft + systemBars.left,
                top = initialPaddingTop + systemBars.top,
                right = initialPaddingRight + systemBars.right,
                bottom = initialPaddingBottom
            )

            insets
        }
    }

    /**
     * 로딩 다이얼로그 셋팅 함수
     **/
    protected fun setProgressDialog(isShow:Boolean) {
        if (isShow) ProgressDialogUtil.show(this@BaseActivity)
        else ProgressDialogUtil.dismiss()
    }

    private val onAbsBackPressedCallback = object : OnBackPressedCallback(true){
        override fun handleOnBackPressed() {
            finish()
            logE("onAbsBackPressedCallback")
//            if (isTwoTouchAppExit) {
//                if(System.currentTimeMillis() > backKeyPressedTime + BACKPRESS_INTERVAL){
//                    backKeyPressedTime = System.currentTimeMillis()
//                    toastShow(R.string.text_app_exit_warning_explantion)
//                }else{
//                    exitProcess(0)
//                }
//            } else {
//                exitProcess(0)
//            }
        }
    }
}
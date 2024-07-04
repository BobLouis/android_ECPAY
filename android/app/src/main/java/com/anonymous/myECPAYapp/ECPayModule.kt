package com.anonymous.myECPAYapp

import android.app.Activity
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import com.facebook.react.bridge.Callback
import tw.com.ecpay.paymentgatewaykit.manager.PaymentkitManager
import tw.com.ecpay.paymentgatewaykit.manager.ServerType

enum class LanguageCode {
    ZH_TW, EN_US
}

class ECPayModule(reactContext: ReactApplicationContext) : ReactContextBaseJavaModule(reactContext) {

    private var activity: Activity? = null

    init {
        activity = currentActivity
    }

    override fun getName(): String {
        return "ECPayModule"
    }

    @ReactMethod
    fun initializeSDK() {
        activity?.let {
            PaymentkitManager.initialize(it, ServerType.Stage)
        }
    }

    @ReactMethod
    fun createPayment(token: String, language: String, useResultPage: Int, appStoreName: String, callback: Callback) {
        activity?.let {
            val languageCode = if (language == "zh-TW") LanguageCode.ZH_TW else LanguageCode.EN_US
            PaymentkitManager.createPayment(
                it,
                token,
                "",
                languageCode,
                useResultPage == 1,
                appStoreName,
                PaymentkitManager.RequestCode_CreatePayment
            )
            callback.invoke("Payment initiated")
        }
    }
}

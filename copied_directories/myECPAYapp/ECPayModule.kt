package com.anonymous.myECPAYapp

import android.app.Activity
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import com.facebook.react.bridge.Callback
import tw.com.ecpay.paymentgatewaykit.manager.PaymentkitManager
import tw.com.ecpay.paymentgatewaykit.manager.ServerType

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
            // 假設你已經設置了 PaymentkitManager 並正確初始化
            PaymentkitManager.createPayment(
                it,
                null, // Replace with a Fragment if you have one
                token,
                language,
                useResultPage,
                appStoreName,
                PaymentkitManager.RequestCode_CreatePayment
            )
            callback.invoke("Payment initiated")
        }
    }
}

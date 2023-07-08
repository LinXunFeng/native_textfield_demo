package com.gitlqr.android_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.*
import android.util.Log
import android.view.inputmethod.BaseInputConnection
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val etTest = findViewById<EditText>(R.id.etTest)
        etTest.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // log("beforeTextChanged: ${s}, start = ${start}, count = ${count}, after = ${after}")
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // val isComposition = isComposition(s)
                // log("onTextChanged: ${s}, start = ${start}, before = ${before}, count = ${count}, isComposition = ${isComposition}")
            }

            override fun afterTextChanged(s: Editable?) {
                val isComposition = isComposition(s)
                // log("afterTextChanged: ${s?.toString()}, length = ${etTest.length()}, isComposition = ${isComposition}")
                // log("==================================")
                if (isComposition) {
                    log("isComposition: ${s?.toString()}")
                } else {
                    log("text: ${s?.toString()}")
                }
            }
        })
    }

    /**
     * reference android code:
     * 1、frameworks/base/core/java/android/widget/Editor.java
     *      https://cs.android.com/android/platform/superproject/main/+/main:frameworks/base/core/java/android/widget/Editor.java;l=7421?q=isComposition&sq=
     *
     * reference flutter code:
     * 1、flutter/packages/flutter/lib/src/services/text_input.dart
     *      https://github.com/flutter/flutter/blob/7495415363d836cba2f6720379b23e0a7e4db643/packages/flutter/lib/src/services/text_input.dart#L861
     * 2、engine/lib/ui/text.dart
     *      https://github.com/flutter/engine/blob/9006633571bbe0613398db94b0e98cc44d4d94cd/lib/ui/text.dart#L2335
     */
    fun isComposition(source: CharSequence?): Boolean {
        if (source !is Spannable) return false
        val composeBegin = BaseInputConnection.getComposingSpanEnd(source)
        val composeEnd = BaseInputConnection.getComposingSpanEnd(source)

        // Whether this range represents a valid position in the text.
        val isValid = composeBegin >= 0 && composeEnd >= 0
        // Whether this range is empty (but still potentially placed inside the text).
        // val isCollapsed = composeBegin == composeEnd
        // Whether the start of this range precedes the end
        val isNormalized = composeEnd >= composeBegin

        val isComposingRangeValid = isValid && isNormalized && composeEnd <= source.length
        // log("composeBegin = ${composeBegin}, composeEnd = ${composeEnd}, isComposingRangeValid = ${isComposingRangeValid}")
        return isComposingRangeValid
    }

    fun log(msg: String) {
        Log.e("lqr", "EditText: ${msg}")
    }
}
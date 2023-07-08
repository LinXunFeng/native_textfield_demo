package com.gitlqr.android_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val etTest = findViewById<EditText>(R.id.etTest)
        etTest.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                log("beforeTextChanged: ${s}, start = ${start}, count = ${count}, after = ${after}")
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                log("onTextChanged: ${s}, start = ${start}, before = ${before}, count = ${count}")
            }

            override fun afterTextChanged(s: Editable?) {
                log("afterTextChanged: ${s?.toString()}")
                log("==================================")
            }
        })
    }

    fun log(msg: String) {
        Log.e("lqr", msg)
    }
}
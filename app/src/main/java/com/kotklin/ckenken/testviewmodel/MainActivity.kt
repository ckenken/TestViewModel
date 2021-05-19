package com.kotklin.ckenken.testviewmodel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "MainActivity"
    }

    private val edit: EditText by lazy { findViewById(R.id.editMain) }
    private val btnSave: Button by lazy { findViewById(R.id.btnMainSave) }
    private val btnResult: Button by lazy { findViewById(R.id.btnMainResult) }

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this, MyViewModelFactory).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnSave.setOnClickListener {
            viewModel.saveText(edit.text.toString())
            edit.text = null
        }

        btnResult.setOnClickListener {
            startActivity(Intent(this, ResultActivity::class.java))
        }
    }
}
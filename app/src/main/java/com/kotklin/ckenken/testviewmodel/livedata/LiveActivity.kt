package com.kotklin.ckenken.testviewmodel.livedata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.kotklin.ckenken.testviewmodel.R

class LiveActivity : AppCompatActivity() {
    private lateinit var edit: EditText
    private lateinit var btnSave: Button
    private lateinit var textResult: TextView

    private val viewModel: LiveViewModel by lazy {
        ViewModelProvider(this, com.kotklin.ckenken.testviewmodel.MyViewModelFactory).get(LiveViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_live)

        edit= findViewById(R.id.editMain)
        btnSave= findViewById(R.id.btnMainSave)
        textResult= findViewById(R.id.textMainResult)

        btnSave.setOnClickListener {
            viewModel.saveText(edit.text.toString())
            edit.text = null
        }

        edit.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.saveText(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        viewModel.getText().observe(this, Observer{
            textResult.text= it
        })
    }
}
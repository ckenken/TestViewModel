package com.kotklin.ckenken.testviewmodel.room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.kotklin.ckenken.testviewmodel.MainViewModel
import com.kotklin.ckenken.testviewmodel.MyViewModelFactory
import com.kotklin.ckenken.testviewmodel.R
import kotlinx.coroutines.Dispatchers
import java.lang.StringBuilder

class DataBaseActivity : AppCompatActivity() {

    val edit by lazy { findViewById<EditText>(R.id.editMain) }
    val btnSave by lazy { findViewById<Button>(R.id.btnMainSave) }
    val btnClear by lazy { findViewById<Button>(R.id.btnMainClear) }
    val textResult by lazy { findViewById<TextView>(R.id.dataResult) }

    var index = 0

    private val viewModel: DataViewModel by lazy {
        ViewModelProvider(this, DataViewModelFactory(
            DataRepository(
                LocalDataSource(BCDatabase.getInstance(applicationContext).dataDao)
            ),
            application
        )).get(DataViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_base)

        btnSave.setOnClickListener {
            viewModel.insertComment(edit.text.toString(), "kkman ${index++}")
            edit.text = null
        }

        btnClear.setOnClickListener {
            viewModel.clearAll()
        }

        viewModel.comments.observe(this, {
            Log.d("viewModel.comments", "inn")
            val sb = StringBuilder()
            it.observe(this,{ list ->
                sb.clear()
                list.forEach {  contentData ->
                    sb.append("${contentData.title} ${contentData.content}\n")
                }
                textResult.text = sb.toString()
            })
        })
    }
}
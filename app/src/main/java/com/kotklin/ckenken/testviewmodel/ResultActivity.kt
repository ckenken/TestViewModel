package com.kotklin.ckenken.testviewmodel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ResultActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "ResultActivity"
    }

    private val viewModel: ResultViewModel by lazy {
        ViewModelProvider(this, MyViewModelFactory).get(ResultViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val recyclerView: RecyclerView = findViewById(R.id.listResult)
        recyclerView.apply {
            adapter = ResultRecyclerAdapter(viewModel)
            layoutManager = LinearLayoutManager(context)
        }
    }

    class ResultRecyclerAdapter(private val viewModel: ResultViewModel):
        RecyclerView.Adapter<ResultRecyclerAdapter.ResultViewHolder>() {
        class ResultViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
            val textId: TextView = itemView.findViewById(R.id.textItemResultId)
            val textContent: TextView= itemView.findViewById(R.id.textItemResultContent)
            val textPrint: TextView= itemView.findViewById(R.id.textItemResultPrint)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultViewHolder {
            return ResultViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_result, parent, false))
        }

        override fun onBindViewHolder(holder: ResultViewHolder, position: Int) {
            Log.d(TAG, "onBindViewHolder: $position")
            holder.textId.text = viewModel.getAllText()[position]._id.toString()
            holder.textContent.text = viewModel.getAllText()[position].content
            holder.textPrint.text = viewModel.getAllText()[position].print()
        }

        override fun getItemCount(): Int = viewModel.getAllText().size
    }
}

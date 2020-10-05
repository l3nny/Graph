package com.yulie.graph.view.ui


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.yulie.graph.R
import com.yulie.graph.viewModel.DataViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

      //DataViewModel.getInstance().fetchList();
    }
}
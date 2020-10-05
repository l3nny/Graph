package com.yulie.graph.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.yulie.graph.viewModel.DataViewModel
import com.yulie.graph.databinding.GraphLoadBinding
import com.yulie.graph.service.model.Data
import com.yulie.graph.view.adapter.DataPoint
import kotlinx.android.synthetic.main.graph_load.*
import kotlin.collections.ArrayList

class GraphFragment : Fragment() {
    private lateinit var viewDataBinding: GraphLoadBinding



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        viewDataBinding = GraphLoadBinding.inflate(inflater, container, false).apply {
            viewmodel = ViewModelProvider(this@GraphFragment).get(DataViewModel::class.java)
            lifecycleOwner = viewLifecycleOwner


        }
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewDataBinding.viewmodel?.fetchList()

        setupObservers()

    }


    private fun setupObservers() {
        viewDataBinding.viewmodel?.listLive?.observe(viewLifecycleOwner, Observer {

            graph_view.setData(generateDataPoints(it))

        })

    }


    private fun generateDataPoints(data: Data): ArrayList<DataPoint> {
      /*  val a = arrayOf("0","1","2","3","4","5","6","7","8","9","10") //x
        val b = arrayOf("0","0","0","10","12","15","20","30","40","50","52") //y

        return a.zip(b).map {

            DataPoint(it.first.toInt(),it.second.toInt())

        } as java.util.ArrayList<DataPoint>*/

       return data.total_power_list.zip(data.speed_list).map {

                DataPoint(it.first.toInt(),paceToMinutesKm(it.second))

        } as ArrayList<DataPoint>
    }

    fun paceToMinutesKm(speed: String): Int {

        return (speed.toDouble() % 16.6666667).toInt()

    }
}

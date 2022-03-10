package com.light.cbrconv.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.light.cbrconv.databinding.FragmentMainBinding
import com.light.cbrconv.view.adapter.MainAdapter
import com.light.cbrconv.viewmodel.AppState
import com.light.cbrconv.viewmodel.MainViewModel

class MainFragment : Fragment() {

    private var vb: FragmentMainBinding? = null
    private var adapterMain:MainAdapter?=null
    private val viewModel: MainViewModel by lazy {
        MainViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        vb = FragmentMainBinding.inflate(inflater, container, false)
        return vb?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getLiveData().observe(viewLifecycleOwner) { it -> render(it) }
        viewModel.getUpdate()
        vb?.recyclerContainer?.run {
            layoutManager = LinearLayoutManager(context?.applicationContext,RecyclerView.VERTICAL,false)
            adapterMain = MainAdapter()
            adapter = adapterMain
        }
    }

    private fun render(data: AppState) {
        when (data) {
            is AppState.Success -> {
                data.listData.getValute()?.let { adapterMain?.init(it)
                adapterMain?.notifyDataSetChanged()}


            }
            is AppState.Loading -> {
                Log.i("AAA", "Loading ${data.numb}")
            }
            is AppState.Error -> {
                Log.i("AAA", "Error ${data.e}")
            }
        }
    }

    companion object {

        @JvmStatic
        fun newInstance() = MainFragment()
    }
}
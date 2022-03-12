package com.light.cbrconv.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.light.cbrconv.databinding.FragmentMainBinding
import com.light.cbrconv.model.data.Aui
import com.light.cbrconv.view.adapter.MainAdapter
import com.light.cbrconv.viewmodel.AppState
import com.light.cbrconv.viewmodel.MainViewModel

class MainFragment : Fragment() {

    private var vb: FragmentMainBinding? = null
    private var adapterMain: MainAdapter? = null
    private var checkAutoUpdate = false
    private var checkVisibleConvert = false
    private var listCharCode: List<String> = listOf("Пустой список")
    private var modelAui: Aui? = null
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
        viewModel.getLiveDataConvert().observe(viewLifecycleOwner) { it -> render(it) }
        viewModel.getLiveDataConvertAui().observe(viewLifecycleOwner) { it -> render(it) }
        viewModel.countItemDB(checkAutoUpdate)
        vb?.recyclerContainer?.run {
            layoutManager =
                LinearLayoutManager(context?.applicationContext, RecyclerView.VERTICAL, false)
            adapterMain = MainAdapter()
            adapter = adapterMain
        }
        initAutoUpdate()
       // viewModel.searchAllCharCode()
    }

    private fun initAutoUpdate() {
        vb?.autoUpdateBtn?.setOnCheckedChangeListener { buttonView, isChecked ->
            checkAutoUpdate = isChecked
            viewModel.countItemDB(isChecked)
            Log.i("AAA", "Checked = $isChecked")
        }
        vb?.updateDataBtn?.setOnClickListener {
            viewModel.getUpdate()
        }
        vb?.convertValuteBtn?.setOnClickListener {

            if (vb?.convertContainer?.visibility == View.VISIBLE) {
                vb?.convertContainer?.visibility = View.GONE
            } else {
                viewModel.searchAllCharCode()
                vb?.convertContainer?.visibility = View.VISIBLE
            }
        }
        vb?.enterValune?.doOnTextChanged { _, _, _, _ ->
            sherlokHolms()
        }

        vb?.enterValuneNumber?.doOnTextChanged { _, _, _, _ ->
            sherlokHolms()
        }

        vb?.spinnerValute?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                viewModel.searchAui(parent?.selectedItem.toString())
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

    }

    fun sherlokHolms() {
        if (!vb?.enterValune?.text.toString()
                .isNullOrEmpty() && !vb?.enterValuneNumber?.text.isNullOrEmpty() && modelAui != null
        ) {
            val textValute = vb?.enterValune?.text.toString().toDouble()
            val textNumberValute = vb?.enterValuneNumber?.text.toString().toDouble()
            val result = textValute * textNumberValute * modelAui?.getValue()!!.toInt() /
                    modelAui?.getNominal()!!.toInt()
            vb?.resultValute?.text = result.toString()

        } else {
            vb?.resultValute?.text = "***"
        }


    }

    private fun render(data: AppState) {
        when (data) {
            //Результат от сети
            is AppState.Success -> {
                data.listData.getValute()?.let {
                    adapterMain?.init(it)
                    adapterMain?.notifyDataSetChanged()
                    Log.i("AAA", "Данные c сети")
                    //Обновить данные в бд если есть, если нет то добавить все записи
                    viewModel.setUpdateDataModel(data.listData.getValute()!!)
                }
            }
            // Вывод с БД
            is AppState.SuccessAui -> {
                data.listData.let {
                    adapterMain?.init(it)
                    adapterMain?.notifyDataSetChanged()

                }
            }
            //показываем список валют
            is AppState.SuccessCharCode -> {
                listCharCode = data.listCharCode
                val adapterForSpinner = ArrayAdapter(
                    requireContext(),
                    android.R.layout.simple_spinner_item,
                    listCharCode
                )
                vb?.spinnerValute?.adapter = adapterForSpinner
            }
            //Выводим объект объектов (для конвертации)
            is AppState.SuccessAuiTest -> {
                modelAui = data.listData
                sherlokHolms()
            }
            is AppState.Loading -> {
                Log.i("AAA", "Loading ${data.numb}")
            }
            is AppState.Error -> {
                Toast.makeText(context?.applicationContext, "${data.e.message}", Toast.LENGTH_SHORT)
                    .show()
                Log.i("AAA", "Error ${data.e.message}")
            }
        }
    }

    companion object {

        @JvmStatic
        fun newInstance() = MainFragment()
    }
}
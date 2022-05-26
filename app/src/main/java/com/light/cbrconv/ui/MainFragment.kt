package com.light.cbrconv.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.light.cbrconv.App
import com.light.cbrconv.R
import com.light.cbrconv.databinding.FragmentMainBinding
import com.light.cbrconv.domain.entity.Currency
import com.light.cbrconv.presentation.AppState
import com.light.cbrconv.presentation.MainViewModel
import com.light.cbrconv.ui.adapter.MainAdapter
import javax.inject.Inject


class MainFragment : Fragment() {

    companion object {
        @JvmStatic
        fun newInstance() = MainFragment()
    }

    private var vb: FragmentMainBinding? = null
    private var mainAdapter: MainAdapter? = MainAdapter()
    private var checkAutoUpdate = false
    private var listCharCode: List<String> = emptyList()
    private var currencyModel: Currency? = null

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var viewModel: MainViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as App).appComponent.inject(this)
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
        initViewModels()
        viewModel.getListCurrency(checkAutoUpdate)
        initRecyclerView()
        initAutoUpdateListCurrency()
    }

    private fun initViewModels() {
        viewModel = viewModelFactory.create(MainViewModel::class.java)
        viewModel._liveDataListCurrency.observe(viewLifecycleOwner, ::render)
        viewModel._liveDataListCharCode.observe(viewLifecycleOwner, ::render)
        viewModel._liveDataCurrentForCharCode.observe(viewLifecycleOwner, ::render)
        viewModel._liveDataConversionCurrency.observe(viewLifecycleOwner, ::render)
    }

    private fun initRecyclerView() {
        vb?.recyclerContainer?.adapter = mainAdapter
    }

    private fun initAutoUpdateListCurrency() {
        vb?.autoUpdateBtn?.setOnCheckedChangeListener { _, isChecked ->
            checkAutoUpdate = isChecked
            viewModel.getListCurrency(isChecked)

        }
        vb?.updateDataBtn?.setOnClickListener {
            viewModel.getUpdate()
        }
        vb?.convertValuteBtn?.setOnClickListener {

            if (vb?.convertContainer?.visibility == View.VISIBLE) {
                vb?.convertContainer?.visibility = View.GONE
            } else {
                viewModel.getListCharCode()
                vb?.convertContainer?.visibility = View.VISIBLE
            }
        }
        vb?.enterValune?.doOnTextChanged { _, _, _, _ ->
            sherlockHolmes()
        }

        vb?.enterValuneNumber?.doOnTextChanged { _, _, _, _ ->
            sherlockHolmes()
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

    private fun sherlockHolmes() {
        if (vb?.enterValune?.text.toString()
                .isNotEmpty() && !vb?.enterValuneNumber?.text.isNullOrEmpty() && currencyModel != null
        ) {
            try {
                val currency = vb?.enterValune?.text.toString().toBigDecimal()
                val amountCurrency = vb?.enterValuneNumber?.text.toString().toBigDecimal()
                val auiModel = currencyModel!!
                viewModel.currentConversion(currency, amountCurrency, auiModel)

            } catch (e: Exception) {
                Toast.makeText(requireContext(), e.message.toString(), Toast.LENGTH_SHORT).show()
            }
        } else {
            vb?.resultValute?.text = getString(R.string.plug_field)
        }
    }

    //list of currencies Output from the database
    private fun render(data: AppState) {
        when (data) {
            //Result from the network
            is AppState.Success -> {
                val listCurrency = data.listCurrency
                mainAdapter?.init(listCurrency)
                //Update the data in the database if there is, if not then add all the records
                viewModel.setUpdateDataModel(listCurrency)
            }
            //list of currencies
            is AppState.SuccessListCharCode -> {
                listCharCode = data.listCharCode
                val adapterForSpinner = ArrayAdapter(
                    requireContext(),
                    android.R.layout.simple_spinner_item,
                    listCharCode
                )
                vb?.spinnerValute?.adapter = adapterForSpinner
            }
            is AppState.SuccessCurrentForCharCode -> {
                currencyModel = data.listData
                sherlockHolmes()
            }
            //result of currency conversion calculation
            is AppState.SuccessConversionCurrency -> {
                vb?.resultValute?.text = data.resultTransaction
            }

            is AppState.Error -> {
                Toast.makeText(context?.applicationContext, "${data.e.message}", Toast.LENGTH_SHORT)
                    .show()

            }
        }
    }


}
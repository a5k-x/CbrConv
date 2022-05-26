package com.light.cbrconv.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.light.cbrconv.domain.entity.Currency
import com.light.cbrconv.domain.usecase.ConversionCurrencyUseCase
import com.light.cbrconv.domain.usecase.WorkingWithCurrencyUseCase
import kotlinx.coroutines.launch
import java.math.BigDecimal
import java.util.*
import javax.inject.Inject
import kotlin.concurrent.schedule

class MainViewModel @Inject constructor(
    private val workingWithCurrencyUseCase: WorkingWithCurrencyUseCase,
    private val conversionCurrencyUseCase: ConversionCurrencyUseCase
) :
    ViewModel() {

    companion object {
        private const val ERROR_INFO_DB = "Database is empty"
        private const val ERROR_NO_DATA = "There is no data"
    }

    private val liveDataListCurrency = MutableLiveData<AppState>()
    val _liveDataListCurrency: LiveData<AppState> = liveDataListCurrency

    private val liveDataListCharCode = MutableLiveData<AppState>()
    val _liveDataListCharCode: LiveData<AppState> = liveDataListCharCode

    private val liveDataCurrentForCharCode = MutableLiveData<AppState>()
    val _liveDataCurrentForCharCode: LiveData<AppState> = liveDataCurrentForCharCode

    private val liveDataConversionCurrency = MutableLiveData<AppState>()
    val _liveDataConversionCurrency: LiveData<AppState> = liveDataConversionCurrency

    private var timer: Timer? =null

    //Set the request interval if the database is empty then the request to the network
    fun getListCurrency(autoTimer: Boolean) {
         if (timer == null) {
            timer = Timer()
        }
            if (autoTimer) {
               timer?.schedule(0,10000){
                   getUpdate()
               }
        } else {
            timer?.cancel()
            timer = null

            viewModelScope.launch {
                //If false, then we make a request to the database, if it is empty, then we try to get data from the network
                val countsItemDatabase: Int = workingWithCurrencyUseCase.getCountDB()

                if (countsItemDatabase == 0) {
                    liveDataListCurrency.postValue(AppState.Error(Throwable(ERROR_INFO_DB)))
                    getUpdate()
                } else {
                    //if the database is not empty, then the output of all data from the database
                    val listCurrency = workingWithCurrencyUseCase.getListCurrency(false)
                    if (listCurrency != null) {
                        liveDataListCurrency.postValue(AppState.Success(listCurrency))
                    } else liveDataListCurrency.postValue(AppState.Error(Throwable(ERROR_NO_DATA)))
                }
            }
        }
    }



    //Get data from the network
    fun getUpdate() {
        viewModelScope.launch {
            val listCurrency = workingWithCurrencyUseCase.getListCurrency(true)
            if (listCurrency != null) {
                liveDataListCurrency.postValue(AppState.Success(listCurrency))
            } else liveDataListCurrency.postValue(AppState.Error(Throwable(ERROR_NO_DATA)))
        }
    }

    //Updating data in the database
    fun setUpdateDataModel(listCurrency: List<Currency>) {
        viewModelScope.launch {
            workingWithCurrencyUseCase.updateAllList(listCurrency)
        }
    }

    //Get a list of currencies from the database
    fun getListCharCode() {
        viewModelScope.launch {
            val listCharCode = workingWithCurrencyUseCase.getListCharCode()
            if (listCharCode != null) {
                liveDataListCharCode.postValue(AppState.SuccessListCharCode(listCharCode))
            } else liveDataListCharCode.postValue(AppState.Error(Throwable(ERROR_NO_DATA)))
        }
    }

    //Get the selected object from the database
    fun searchAui(charCode: String) {
        viewModelScope.launch {
            val currencyForCharCode = workingWithCurrencyUseCase.getCurrencyForCharCode(charCode)
            if (currencyForCharCode != null) {
                liveDataCurrentForCharCode.postValue(
                    AppState.SuccessCurrentForCharCode(
                        currencyForCharCode
                    )
                )
            } else liveDataCurrentForCharCode.postValue(AppState.Error(Throwable(ERROR_NO_DATA)))
        }
    }

    //Run the conversion
    fun currentConversion(
        currency: BigDecimal,
        amountCurrency: BigDecimal,
        currencyModel: Currency
    ) {

        viewModelScope.launch {
            val resultConversionCurrency = conversionCurrencyUseCase.conversionCurrency(
                currency = currency,
                amountCurrency = amountCurrency,
                selectCurrency = currencyModel
            )
            liveDataConversionCurrency.postValue(
                AppState.SuccessConversionCurrency(
                    resultConversionCurrency
                )
            )
        }
    }



}
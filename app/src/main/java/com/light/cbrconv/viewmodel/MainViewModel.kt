package com.light.cbrconv.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.light.cbrconv.App
import com.light.cbrconv.model.data.Aui
import com.light.cbrconv.model.data.CurrencyConvertation
import com.light.cbrconv.model.repository.IRepositoryLocal
import com.light.cbrconv.model.repository.RepositoryImp
import com.light.cbrconv.model.repository.RepositoryLocalImp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.math.BigDecimal
import java.util.*
import javax.inject.Inject


class MainViewModel() : ViewModel() {

    init {
        App.instance.appComponent.inject(this)
    }

    @Inject
    lateinit var repositoryImp: RepositoryImp
    @Inject
    lateinit var repositoryLocalImp: IRepositoryLocal

    private val scope = CoroutineScope(Dispatchers.IO)

    private var liveData = MutableLiveData<AppState>()
    val _livaData: LiveData<AppState> = liveData

    private var liveDataConvert = MutableLiveData<AppState>()
    val _liveDataConvert: LiveData<AppState> = liveDataConvert

    private var liveDataConvertAui = MutableLiveData<AppState>()
    val _liveDataConvertAui: LiveData<AppState> = liveDataConvertAui

    private var liveDataCurrencyTransaction = MutableLiveData<AppState>()
    val _liveDataCurrencyTransaction: LiveData<AppState> = liveDataCurrencyTransaction

    private var timer: Timer? = null

    //Получить данные из сети
    fun getUpdate() {
        scope.launch {
            val data = repositoryImp.getData()
            liveData.postValue(AppState.Success(data))
        }
    }

    //Задать интервал запросов если БД пустая то запрос в сеть
    fun countItemDB(boolean: Boolean) {
        if (timer == null) {
            timer = Timer()
        }
        //если true то выполняем запрос с интервалом
        if (boolean) {
            timer?.schedule(object : TimerTask() {
                override fun run() {
                    getUpdate()
                }
            }, 0L, 15000)
        } else {
            timer?.cancel()
            timer = null
            scope.launch {
                //Если false то делаем запрос в БД, если она пуста то пытаемся взять данные из сети
                val countsItemDatabase: Int = repositoryLocalImp.getCountDB()
                if (countsItemDatabase <= 0 || countsItemDatabase == null) {
                    //если нет данных отобразить ошибку
                    liveData.postValue(AppState.Error(Throwable(ERROR_INFO_DB)))
                    getUpdate()

                } else {
                    //если бд не пуста, то вывод всех данных c бд
                    liveData.postValue(repositoryLocalImp.getListAllValute())
                }
            }
        }

    }

    //Обновление данных в БД
    fun setUpdateDataModel(data: List<Aui>) {
        scope.launch {
            RepositoryLocalImp().updateAllList(data)
        }
    }

    //Получить список валют из БД
    fun searchAllCharCode() {
        scope.launch {
            liveDataConvert.postValue(repositoryLocalImp.getListCharCode())
        }
    }

    //Получить выбранный объект из БД
    fun searchAui(charCode: String) {
        scope.launch {
            liveDataConvertAui.postValue(
                repositoryLocalImp.getDataCharCode(
                    charCode
                )
            )
        }
    }

    //Выполнить конвертацию
    fun currentConvertation(currency: BigDecimal, amountCurrency: BigDecimal, auiModel: Aui) {
        scope.launch {
            val result = CurrencyConvertation().currencyTransactions(
                currency = currency,
                amountCurrency = amountCurrency,
                selectCurrency = auiModel
            )
                .toString()
            liveDataCurrencyTransaction.postValue(AppState.SuccessConvertationTransaction(result))
        }
    }

    companion object {
        private const val ERROR_INFO_DB = "База данных пуста"
    }
}
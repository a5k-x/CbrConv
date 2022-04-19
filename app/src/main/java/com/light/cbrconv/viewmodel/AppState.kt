package com.light.cbrconv.viewmodel

import android.os.Parcelable
import com.light.cbrconv.model.data.Aui
import com.light.cbrconv.model.data.DataModel
import kotlinx.parcelize.Parcelize

sealed class AppState{

    data class Success(val listData: DataModel): AppState()
    data class SuccessCharCode(val listCharCode:List<String>):AppState()
    data class SuccessAuiTest(val listData: Aui): AppState()
    data class SuccessAui(val listData: List<Aui>): AppState()
    data class SuccessConvertationTransaction(val resultTransaction: String): AppState()

    data class Error(val e:Throwable):AppState()
}

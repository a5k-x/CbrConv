package com.light.cbrconv.viewmodel

import android.os.Parcelable
import com.light.cbrconv.model.data.Aui
import com.light.cbrconv.model.data.DataModel
import kotlinx.parcelize.Parcelize

sealed class AppState{
    @Parcelize
    data class Success(val listData: DataModel): AppState(), Parcelable

    data class SuccessCharCode(val listCharCode:List<String>):AppState()
    data class SuccessAuiTest(val listData: Aui): AppState()
    @Parcelize
    data class SuccessAui(val listData: List<Aui>): AppState(), Parcelable
    data class Loading(val numb:Int):AppState()
    data class Error(val e:Throwable):AppState()
}

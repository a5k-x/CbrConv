package com.light.cbrconv.viewmodel

import com.light.cbrconv.model.data.DataModel

sealed class AppState{
    data class Success(val listData: DataModel): AppState()
    data class Loading(val numb:Int):AppState()
    data class Error(val e:Throwable):AppState()
}

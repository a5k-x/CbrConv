package com.light.cbrconv.viewmodel

sealed class AppState{
    data class Success(val listData:List<DataModel>): AppState()
    data class Loading(val numb:Int):AppState()
    data class Error(val e:Throwable):AppState()
}

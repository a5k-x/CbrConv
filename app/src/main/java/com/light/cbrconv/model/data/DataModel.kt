package com.light.cbrconv.model.data

class DataModel (
    val timestamp:String?,
    val valute:Valute?
)

class Valute(
    val id:String,
    val NumCode:Int,
    val charCode:String,
    val nominal:Int,
    val name:String,
    val value:Double,
    val previous:Double
)

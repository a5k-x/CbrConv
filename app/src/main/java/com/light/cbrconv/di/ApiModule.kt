package com.light.cbrconv.di

import com.google.gson.*
import com.light.cbrconv.model.data.Aui
import com.light.cbrconv.model.data.DataModel
import com.light.cbrconv.model.datasource.DataSource
import com.light.cbrconv.model.datasource.DataSourceRemote
import com.light.cbrconv.model.datasource.RetrofitImp
import com.light.cbrconv.model.repository.IRepositoryLocal
import com.light.cbrconv.model.repository.RepositoryImp
import com.light.cbrconv.model.repository.RepositoryLocalImp
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type
import javax.inject.Named
import javax.inject.Singleton

@Module
class ApiModule {
    @Named("BASE_URL")
    @Provides
    fun getBaseUrl():String = "https://www.cbr-xml-daily.ru/"

    @Named("JSON")
    @Provides
    fun getJSon() = GsonBuilder()
        .registerTypeAdapter(
            DataModel::class.java, object : JsonDeserializer<DataModel> {
                override fun deserialize(
                    json: JsonElement?,
                    typeOfT: Type?,
                    context: JsonDeserializationContext?
                ): DataModel {
                    val elements: JsonObject = json?.asJsonObject!!
                    val valuteObj = elements.getAsJsonObject("Valute")
                    val jsonn = GsonBuilder()
                        .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                        .create()
                    val listKey = listOf(
                        "AUD", "AZN", "GBP",
                        "BYN","BGN","BRL","HUF","HKD","DKK","USD","EUR","INR","KZT","CAD",
                        "KGS","MDL","CNY","NOK","PLN","RON","XDR","SGD","TJS","TRY","TMT",
                        "UZS","UAH","SEK","CZK","CHF","ZAR","KRW","JPY")
                    val listVatuleAui = mutableListOf<Aui>()
                    for (i in listKey.indices) {
                        val qwe = valuteObj[listKey[i]]
                        listVatuleAui.add(jsonn.fromJson(qwe, Aui::class.java))
                    }
                    return DataModel(listVatuleAui)
                }
            }
        )
        .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
        .create()

@Singleton
    @Provides
    fun getRetrofit(@Named("JSON")json:Gson,@Named("BASE_URL") baseUrl:String): Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create(json))
        .build()


    @Provides
    fun getRetrofitIml() = RetrofitImp()

    @Provides
    fun dataSourceRemote():DataSource<DataModel> = DataSourceRemote()

    @Provides
    fun repositoryImpl():RepositoryImp = RepositoryImp()

    @Provides
    fun repositoryLocalImpl(): IRepositoryLocal = RepositoryLocalImp()

}
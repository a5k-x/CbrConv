package com.light.cbrconv.model.datasource

import com.google.gson.*
import com.light.cbrconv.model.data.Aui
import com.light.cbrconv.model.data.DataModel
import com.light.cbrconv.viewmodel.AppState
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type

class RetrofitImp : DataSource<AppState> {

    private var app: AppState = AppState.Loading(1)
    override fun getData(): AppState {

        val data = getService().getData()
        data.enqueue(object : Callback<DataModel> {
            override fun onResponse(call: Call<DataModel>, response: Response<DataModel>) {
                if (response.body() != null) {
                    app = AppState.Success(response.body()!!)
                } else {
                    app = AppState.Error(Throwable("Пустой объект"))
                }
            }
            override fun onFailure(call: Call<DataModel>, t: Throwable) {
                app = AppState.Error(t)
            }
        })
        Thread.sleep(1000)
        return app
    }

    private fun getService(): ApiService {
        return createRetrofit().create(ApiService::class.java)
    }

    private fun createRetrofit(): Retrofit {
        val json = GsonBuilder()
            .registerTypeAdapter(
                DataModel::class.java, object : JsonDeserializer<DataModel> {
                    override fun deserialize(
                        json: JsonElement?,
                        typeOfT: Type?,
                        context: JsonDeserializationContext?
                    ): DataModel {
                        val elements: JsonObject = json?.asJsonObject!!
                        val timeElement = elements["Timestamp"]
                        val timeStr = Gson().fromJson(timeElement, String::class.java)
                        val valuteObj = elements.getAsJsonObject("Valute")
                        val jsonn = GsonBuilder()
                            .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                            .create()
                        val listKey = listOf(
                            "AUD", "AZN", "GBP",
                            "BYN",
                            "BGN",
                            "BRL",
                            "HUF",
                            "HKD",
                            "DKK",
                            "USD",
                            "EUR",
                            "INR",
                            "KZT",
                            "CAD",
                            "KGS",
                            "MDL",
                            "CNY",
                            "NOK",
                            "PLN",
                            "RON",
                            "XDR",
                            "SGD",
                            "TJS",
                            "TRY",
                            "TMT",
                            "UZS",
                            "UAH",
                            "SEK",
                            "CZK",
                            "CHF",
                            "ZAR",
                            "KRW",
                            "JPY"
                        )
                        val listVatuleAui = mutableListOf<Aui>()
                        for (i in listKey.indices) {
                            val qwe = valuteObj[listKey[i]]
                            listVatuleAui.add(jsonn.fromJson(qwe, Aui::class.java))
                        }
                        return DataModel(timeStr, listVatuleAui)
                    }
                }
            )
            .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
            .create()
//https://www.cbr-xml-daily.ru/daily_json.js
        return Retrofit.Builder()
            .baseUrl("https://www.cbr-xml-daily.ru/")
            .addConverterFactory(GsonConverterFactory.create(json))
            .build()
    }
}


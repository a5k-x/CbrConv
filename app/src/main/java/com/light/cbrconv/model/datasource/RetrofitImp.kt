package com.light.cbrconv.model.datasource

import com.light.cbrconv.App
import com.light.cbrconv.model.data.DataModel
import retrofit2.Retrofit
import javax.inject.Inject

class RetrofitImp : DataSource<DataModel> {

    init {
        App.instance.appComponent.inject(this)
    }

    @Inject
    lateinit var retrofitImp: Retrofit

    override suspend fun getData(): DataModel = getService().getData()

    private fun getService(): ApiService = retrofitImp.create(ApiService::class.java)


    /*  private fun createRetrofit(): Retrofit {
          val json = GsonBuilder()
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

          return Retrofit.Builder()
              .baseUrl("https://www.cbr-xml-daily.ru/")
              .addConverterFactory(GsonConverterFactory.create(json))
              .build()
      }*/
}


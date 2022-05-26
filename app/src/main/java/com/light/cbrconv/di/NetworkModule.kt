package com.light.cbrconv.di

import com.google.gson.*
import com.light.cbrconv.data.model.CurrencyRemote
import com.light.cbrconv.data.model.CurrencyRemoteModel
import com.light.cbrconv.data.network.ApiService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type
import javax.inject.Singleton

@Module
class NetworkModule {

    companion object {

        private const val BASE_URL = "https://www.cbr-xml-daily.ru/"
        val listKeyCurrency = listOf(
            "AUD","AZN","GBP","BYN","BGN","BRL","HUF","HKD","DKK","USD","EUR","INR", "KZT","CAD","KGS","MDL","CNY","NOK","PLN","RON",
            "XDR","SGD","TJS","TRY","TMT","UZS","UAH","SEK","CZK","CHF","ZAR","KRW","JPY"
        )
    }

    @Singleton
    @Provides
    fun getService(retrofit: Retrofit): ApiService =
        retrofit.create(ApiService::class.java)

    @Singleton
    @Provides
    fun provideRetrofit(json: Gson): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(json))
            .build()


    @Singleton
    @Provides
    fun provideGson(): Gson = GsonBuilder()
        .registerTypeAdapter(
            CurrencyRemoteModel::class.java, object : JsonDeserializer<CurrencyRemoteModel> {
                override fun deserialize(
                    json: JsonElement?,
                    typeOfT: Type?,
                    context: JsonDeserializationContext?
                ): CurrencyRemoteModel {
                    val elements: JsonObject = json?.asJsonObject!!
                    val valuteObj = elements.getAsJsonObject("Valute")
                    val json = GsonBuilder()
                        .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                        .create()

                    val listCurrency = mutableListOf<CurrencyRemote>()
                    for (i in listKeyCurrency.indices) {
                        listCurrency.add(json.fromJson( valuteObj[listKeyCurrency[i]], CurrencyRemote::class.java))
                    }
                    return CurrencyRemoteModel(listCurrency)
                }
            }
        )
        .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
        .create()


}
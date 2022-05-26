package com.light.cbrconv.di

import com.light.cbrconv.data.database.ConversionDbModel
import com.light.cbrconv.data.model.CurrencyDb
import com.light.cbrconv.data.model.CurrencyRemote
import com.light.cbrconv.data.network.ConversionRemoteModel
import com.light.cbrconv.data.repository.RepositoryImp
import com.light.cbrconv.domain.repository.Repository
import com.light.cbrconv.data.datasource.*
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class DataModule {

    @Singleton
    @Binds
    abstract fun repository(repository: RepositoryImp): Repository

    @Singleton
    @Binds
    abstract fun dataSourceRemote(dataSourceRemote: DataSourceRemoteImpl): DataSourceRemote<CurrencyRemote>

    @Singleton
    @Binds
    abstract fun dataSourceLocal(dataSourceLocal: DataSourceLocalImpl): DataSourceLocal<CurrencyDb>

    @Singleton
    @Binds
    abstract fun conversionRemoteModel(conversionRemoteModel: ConversionRemoteModel): ConvertDataModel<CurrencyRemote>

    @Singleton
    @Binds
    abstract fun conversionDbModel(conversionDbModel: ConversionDbModel): ConvertDataModel<CurrencyDb>
}
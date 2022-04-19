package com.light.cbrconv.di

import com.light.cbrconv.model.datasource.DataSourceLocal
import com.light.cbrconv.model.datasource.DataSourceRemote
import com.light.cbrconv.model.datasource.RetrofitImp
import com.light.cbrconv.model.datasource.RoomDatabaseIml
import com.light.cbrconv.model.repository.RepositoryImp
import com.light.cbrconv.model.repository.RepositoryLocalImp
import com.light.cbrconv.view.MainActivity
import com.light.cbrconv.viewmodel.MainViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApiModule::class,AppModule::class,DbModule::class])
interface AppComponent {
    fun inject(retrofit: RetrofitImp)
    fun inject(mainActivity: MainActivity)
    fun inject(roomDatabaseIml: RoomDatabaseIml)
    fun inject(dataSourceLocal: DataSourceLocal)
    fun inject(dataSourceRemote: DataSourceRemote)
    fun inject(repositoryImp: RepositoryImp)
    fun inject(mainViewModel: MainViewModel)
    fun inject(repositoryLocalImp: RepositoryLocalImp)
}
package com.light.cbrconv.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.light.cbrconv.presentation.MainViewModel
import com.light.cbrconv.presentation.MainViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    internal abstract fun viewModel(viewModel: MainViewModel): ViewModel


    @Binds
    abstract fun viewModelFactory(factory: MainViewModelFactory) : ViewModelProvider.Factory

}
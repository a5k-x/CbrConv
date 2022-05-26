package com.light.cbrconv.di

import com.light.cbrconv.domain.entity.ConversionCurrency
import com.light.cbrconv.domain.entity.ConversionCurrencyImpl
import com.light.cbrconv.domain.usecase.ConversionCurrencyUseCase
import com.light.cbrconv.domain.usecase.ConversionCurrencyUseCaseImpl
import com.light.cbrconv.domain.usecase.WorkingWithCurrencyUseCase
import com.light.cbrconv.domain.usecase.WorkingWithCurrencyUseCaseImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton


@Module
abstract class DomainModule {

    @Singleton
    @Binds
    abstract fun useCase(useCase: WorkingWithCurrencyUseCaseImpl): WorkingWithCurrencyUseCase

    @Singleton
    @Binds
    abstract fun conversionCurrent(useCase: ConversionCurrencyUseCaseImpl): ConversionCurrencyUseCase

    @Singleton
    @Binds
    abstract fun conversion(convert: ConversionCurrencyImpl): ConversionCurrency
}
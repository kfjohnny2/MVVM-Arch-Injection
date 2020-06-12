package com.johnnylee.mvvm_arch_injection.injection.components

import com.johnnylee.mvvm_arch_injection.injection.module.NetworkModule
import com.johnnylee.mvvm_arch_injection.ui.main.MainViewModel
import dagger.Component
import javax.inject.Singleton

/**
 * Component providing inject() methods for ViewModels.
 */
@Singleton
@Component(modules = [NetworkModule::class])
interface ViewModelInjector {

    /**
     * Injects required dependencies into the specified MainViewModel.
     * @param mainViewModel MainViewModel in which to inject the dependencies
     */
    fun inject(mainViewModel: MainViewModel)


    @Component.Builder
    interface Builder {

        fun build(): ViewModelInjector
        fun networkModule(networkModule: NetworkModule): Builder
    }
}
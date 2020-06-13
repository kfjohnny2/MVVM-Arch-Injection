package com.johnnylee.mvvm_arch_injection.base

import androidx.lifecycle.ViewModel
import com.johnnylee.mvvm_arch_injection.injection.components.DaggerViewModelInjector
import com.johnnylee.mvvm_arch_injection.injection.components.ViewModelInjector
import com.johnnylee.mvvm_arch_injection.injection.module.NetworkModule
import com.johnnylee.mvvm_arch_injection.ui.main.MainViewModel

/**
 * @author Johnnylee Bryan Marques da Rocha
 *
 * Generic ViewModel class execution providing Dependency Injection for the implemented ViewModel
 * class
 *
 */

open class BaseViewModel : ViewModel() {
    private val injectorApi: ViewModelInjector = DaggerViewModelInjector
        .builder()
        .networkModule(NetworkModule)
        .build()

    init {
        inject()
    }

    /**
     * Injects the required dependencies
     */
    private fun inject() {
        when (this) {
            is MainViewModel -> injectorApi.inject(this)
        }
    }
}
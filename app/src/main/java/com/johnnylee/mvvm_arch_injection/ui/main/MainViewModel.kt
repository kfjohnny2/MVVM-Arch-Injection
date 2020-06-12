package com.johnnylee.mvvm_arch_injection.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.johnnylee.mvvm_arch_injection.base.BaseViewModel
import com.johnnylee.mvvm_arch_injection.base.UseCaseResult
import com.johnnylee.mvvm_arch_injection.model.DataSample
import com.johnnylee.mvvm_arch_injection.network.InterfaceApi
import com.johnnylee.mvvm_arch_injection.ui.main.repository.MainRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MainViewModel : BaseViewModel(){
    @Inject
    lateinit var interfaceApi : InterfaceApi

    private val repository by lazy { MainRepositoryImpl(interfaceApi) }

    val dataSample = MutableLiveData<DataSample>().apply { value = null }

    init {
        get()
    }

    fun get() {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                    repository.getDataSample()

            }
            when(result){
                is UseCaseResult.Success -> {
                    dataSample.value = result.data

                    Log.d("DATA", result.data.toString())
                }
                is UseCaseResult.Error -> {
                    Log.d("ERROR", result.exception.message!!)
                }
            }

        }


    }

}
package com.johnnylee.mvvm_arch_injection.ui.main.repository

import com.johnnylee.mvvm_arch_injection.base.UseCaseResult
import com.johnnylee.mvvm_arch_injection.model.DataSample
import com.johnnylee.mvvm_arch_injection.network.InterfaceApi

open class MainRepositoryImpl(private val interfaceApi: InterfaceApi) : MainRepository {
    override suspend fun getDataSample(): UseCaseResult<DataSample> {
        return try{
            val result = interfaceApi.getDataSampleService()
            if (result.isSuccessful){
                UseCaseResult.Success(result.body()!!)
            } else{
                UseCaseResult.Error(Throwable(result.message()))
            }
        }catch (ex : Exception){
            UseCaseResult.Error(ex)
        }
    }

    override suspend fun postDataSample(dataSample: DataSample): UseCaseResult<Int> {
        return try {
            val request = interfaceApi.postDataSampleService(dataSample)
            if (request.isSuccessful) {
                UseCaseResult.Success(request.code())
            } else {
                UseCaseResult.Error(Throwable(request.message()))
            }
        } catch (ex: Exception) {
            UseCaseResult.Error(ex)
        }
    }
}

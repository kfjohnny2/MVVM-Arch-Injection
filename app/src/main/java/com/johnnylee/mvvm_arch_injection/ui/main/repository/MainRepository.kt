package com.johnnylee.mvvm_arch_injection.ui.main.repository

import com.johnnylee.mvvm_arch_injection.base.UseCaseResult
import com.johnnylee.mvvm_arch_injection.model.DataSample

interface MainRepository {
    suspend fun getDataSample() : UseCaseResult<DataSample>
    suspend fun postDataSample(dataSample: DataSample) : UseCaseResult<Int>
}
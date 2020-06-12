package com.johnnylee.mvvm_arch_injection.network

import com.johnnylee.mvvm_arch_injection.model.DataSample
import com.johnnylee.mvvm_arch_injection.util.PATH_DATA_SAMPLE
import com.johnnylee.mvvm_arch_injection.util.PATH_DATA_SAMPLE_POST
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * Example interface for declaring a request method using retrofit
 */
interface InterfaceApi {

    /**
     * Get data from the API/Webservice
     * GET Value = Path for the URL, example: API_URL/PATH_DATA_SAMPLE
     */
    @GET(PATH_DATA_SAMPLE)
    fun getDataSampleService() : Response<DataSample>

    /**
     * Get data from the API/Webservice
     * POST Value = Path for the URL, example: API_URL/PATH_DATA_SAMPLE_POST
     * @param dataSample = Body for the Post request
     */
    @POST(PATH_DATA_SAMPLE_POST)
    fun postDataSampleService(@Body dataSample: DataSample) : Response<Void>
}
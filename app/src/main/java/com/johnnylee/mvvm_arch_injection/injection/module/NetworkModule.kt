package com.johnnylee.mvvm_arch_injection.injection.module

import com.google.gson.GsonBuilder
import com.johnnylee.mvvm_arch_injection.BuildConfig
import com.johnnylee.mvvm_arch_injection.BuildConfig.API_URL
import com.johnnylee.mvvm_arch_injection.network.InterfaceApi
import dagger.Module
import dagger.Provides
import dagger.Reusable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
object NetworkModule {
    /**
     * Provides the InterfaceApi service implementation.
     * @param retrofit the Retrofit object used to instantiate the service
     * @return the InterfaceApi service implementation.
     */
    @Provides
    @Reusable
    @JvmStatic
    internal fun provideInterfaceApi(retrofit: Retrofit): InterfaceApi {
        return retrofit.create(InterfaceApi::class.java)
    }

    /**
     * Provides the Retrofit object.
     * @return the Retrofit object
     */
    @Provides
    @Reusable
    @JvmStatic
    internal fun provideRetrofitInterface(): Retrofit {
        val gson = GsonBuilder()
            .setLenient()
            .create()

        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val httpClient = OkHttpClient.Builder()

        httpClient.readTimeout(230, TimeUnit.SECONDS)
        httpClient.connectTimeout(230, TimeUnit.SECONDS)

        //INTERCEPTORS
        if (BuildConfig.DEBUG){
            httpClient.addInterceptor(logging)
        }

        return Retrofit.Builder()
            .baseUrl(API_URL)
            .client(httpClient.build())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }
}
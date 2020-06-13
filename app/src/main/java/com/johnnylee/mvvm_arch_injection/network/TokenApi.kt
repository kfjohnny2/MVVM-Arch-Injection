package com.johnnylee.mvvm_arch_injection.network

import com.johnnylee.mvvm_arch_injection.BuildConfig.API_CLIENT_ID
import com.johnnylee.mvvm_arch_injection.BuildConfig.API_CLIENT_SECRET
import com.johnnylee.mvvm_arch_injection.model.Token
import com.johnnylee.mvvm_arch_injection.util.helpers.*
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * @author Johnnylee Bryan Marques da Rocha
 *
 */

interface TokenApi {
    @FormUrlEncoded
    @POST(PATH_TOKEN_ACCESS_URL)
    fun refreshToken(@Field(GRANT_TYPE) grant_type: String = REFRESH_TOKEN,
                     @Field(CLIENT_ID)client_id: String = API_CLIENT_ID,
                     @Field(CLIENT_SECRET) secret: String = API_CLIENT_SECRET,
                     @Field(REFRESH_TOKEN) refresh_token: String
    ): Call<Token>
}
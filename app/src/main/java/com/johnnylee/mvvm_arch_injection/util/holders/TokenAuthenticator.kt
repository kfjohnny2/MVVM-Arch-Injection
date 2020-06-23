package com.johnnylee.mvvm_arch_injection.util.holders

import com.johnnylee.mvvm_arch_injection.application.MVVMArchInjectionApplicationManager
import com.johnnylee.mvvm_arch_injection.model.Token
import com.johnnylee.mvvm_arch_injection.network.TokenApi
import com.johnnylee.mvvm_arch_injection.util.helpers.ACCESS_TOKEN
import com.johnnylee.mvvm_arch_injection.util.helpers.EXPIRES_IN
import com.johnnylee.mvvm_arch_injection.util.helpers.REFRESH_TOKEN
import com.johnnylee.mvvm_arch_injection.util.helpers.cleanPreferences
import com.johnnylee.mvvm_arch_injection.util.helpers.getPreferenceLong
import com.johnnylee.mvvm_arch_injection.util.helpers.getRefreshToken
import com.johnnylee.mvvm_arch_injection.util.helpers.setPreferences
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

/**
 * @author Johnnylee Bryan Marques da Rocha
 *
 * Authentication interceptor class for redoing request when token has expired,
 * getting a new accessToken and refreshToken
 */
class TokenAuthenticator(private val tokenApi: TokenApi) : Authenticator {
    override fun authenticate(route: Route?, response: Response): Request? {
        val refreshToken = getRefreshToken()
        val retrofitResponse: retrofit2.Response<Token>?
        when{
            System.currentTimeMillis() <= getPreferenceLong(EXPIRES_IN) -> return null
        }

        retrofitResponse = when {
            !refreshToken.isNullOrEmpty() -> tokenApi.refreshToken(refresh_token = refreshToken).execute()
            else -> null
        }

        if (retrofitResponse != null) {
            val grant = retrofitResponse.body()!!

            MVVMArchInjectionApplicationManager.appContext?.let {
                val time = System.currentTimeMillis()
                cleanPreferences(it)
                setPreferences(grant.accessToken,
                    ACCESS_TOKEN, true, it)
                setPreferences(grant.refreshToken,
                    REFRESH_TOKEN, true, it)
                setPreferences(time + (grant.expiresIn * 1000),
                    EXPIRES_IN, true, it)
                //Call previous request with the new token
                return response.request().newBuilder().header("Authorization", "Bearer " + grant.accessToken).build()
            }

        }
        return null
    }

}
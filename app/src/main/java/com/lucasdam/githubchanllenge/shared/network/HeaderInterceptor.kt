package com.lucasdam.githubchanllenge.shared.network

import com.lucasdam.githubchanllenge.shared.sessionmanger.AppSessionManager.KeyNotFoundException
import com.lucasdam.githubchanllenge.shared.sessionmanger.SessionManager
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import timber.log.Timber

/**
 * @author Lucas Marciano on 05/04/20.
 */

class HeaderInterceptor(private val sessionManager: SessionManager) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var original: Request = chain.request().newBuilder().build()

        try {
            original = original.newBuilder()
                .addHeader(
                    HEADER_KEY_AUTHORIZATION,
                    USER_HEADER
                )
                .build()
        } catch (keyNotFoundException: KeyNotFoundException) {
            Timber.e(keyNotFoundException)
        }

        return chain.proceed(original)
    }

    companion object {
        private const val HEADER_KEY_AUTHORIZATION = "Authorization"
        private const val USER_HEADER = ""
    }
}

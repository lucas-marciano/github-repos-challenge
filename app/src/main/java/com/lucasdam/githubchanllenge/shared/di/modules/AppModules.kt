package com.lucasdam.githubchanllenge.shared.di.modules

import android.content.Context
import com.lucasdam.githubchanllenge.R
import com.lucasdam.githubchanllenge.shared.network.HeaderInterceptor
import com.lucasdam.githubchanllenge.shared.sessionmanger.AppSessionManager
import com.lucasdam.githubchanllenge.shared.sessionmanger.AppSessionManager.Companion.SHARED_PREFERENCES_NAME
import com.lucasdam.githubchanllenge.shared.sessionmanger.SessionManager
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * @author Lucas Marciano on 05/04/20.
 */
object AppModules {

    private val appModule = module {
        single<SessionManager> {
            AppSessionManager(
                androidContext().getSharedPreferences(
                    SHARED_PREFERENCES_NAME,
                    Context.MODE_PRIVATE
                ),
                get()
            )
        }
    }

    private val networkModule = module {
        single {
            OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(HeaderInterceptor(get()))
                .addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
                .build().let { client ->
                    Retrofit.Builder()
                        .baseUrl(androidContext().getString(R.string.api_url))
                        .client(client)
                        .addConverterFactory(GsonConverterFactory.create(get()))
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .build()
                }
        }
    }

    val modules = mutableListOf(
        appModule,
        networkModule,
        ProvidersModule.module,
        UseCasesModule.module,
        ServicesModule.module
    )
}

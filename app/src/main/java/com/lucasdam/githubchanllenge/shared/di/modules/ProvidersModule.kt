package com.lucasdam.githubchanllenge.shared.di.modules

import com.lucasdam.githubchanllenge.shared.di.DisposeBag
import com.lucasdam.githubchanllenge.shared.network.ThrowableHandler
import com.lucasdam.githubchanllenge.shared.network.ThrowableHandlerImpl
import com.lucasdam.githubchanllenge.shared.provider.SchedulerProvider
import com.lucasdam.githubchanllenge.shared.provider.SchedulerProviderImpl
import com.lucasdam.githubchanllenge.shared.provider.StringProvider
import com.lucasdam.githubchanllenge.shared.provider.StringProviderImpl
import com.google.gson.GsonBuilder
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

/**
 * @author Lucas Marciano on 14/04/20.
 */

object ProvidersModule {

    val module = module {
        single {
            GsonBuilder()
                .setLenient()
                .create()
        }
        factory { DisposeBag() }
        factory<SchedulerProvider> { SchedulerProviderImpl() }
        factory<ThrowableHandler> {
            ThrowableHandlerImpl(get(), androidContext())
        }
        factory<StringProvider> { StringProviderImpl(androidContext()) }
    }
}

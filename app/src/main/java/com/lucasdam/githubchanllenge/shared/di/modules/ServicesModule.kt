package com.lucasdam.githubchanllenge.shared.di.modules

import com.lucasdam.githubchanllenge.shared.services.MainService
import org.koin.dsl.module
import retrofit2.Retrofit

/**
 * @author Lucas Marciano on 14/04/20.
 */

object ServicesModule {

    val module = module {
        factory { get<Retrofit>().create(MainService::class.java) }
    }
}

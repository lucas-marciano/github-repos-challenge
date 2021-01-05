package com.lucasdam.githubchanllenge.shared.di.modules

import com.lucasdam.githubchanllenge.shared.usecases.FetchAllRepositoriesUseCase
import com.lucasdam.githubchanllenge.shared.usecases.FetchAllRepositoriesUseCaseImpl
import org.koin.dsl.module

/**
 * @author Lucas Marciano on 14/04/20.
 */

object UseCasesModule {

    val module = module {
        factory<FetchAllRepositoriesUseCase> { FetchAllRepositoriesUseCaseImpl(get()) }
    }
}

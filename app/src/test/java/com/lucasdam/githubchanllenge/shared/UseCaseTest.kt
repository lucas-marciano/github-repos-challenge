package com.lucasdam.githubchanllenge.shared

import com.lucasdam.githubchanllenge.mvvm.BaseContract
import org.junit.Before

/**
 * @author Lucas Marciano on 03/01/21.
 */
abstract class UseCaseTest<UseCaseType> where UseCaseType : BaseContract.UseCase {

    abstract var useCase: UseCaseType

    @Before
    fun setupTest() {
        initialize()
    }

    abstract fun initialize()
}

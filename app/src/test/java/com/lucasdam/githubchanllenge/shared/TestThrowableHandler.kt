package com.lucasdam.githubchanllenge.shared

import com.lucasdam.githubchanllenge.shared.model.view.ExceptionMessage
import com.lucasdam.githubchanllenge.shared.network.ThrowableHandler

/**
 * @author Lucas Marciano on 05/04/20.
 */
class TestThrowableHandler : ThrowableHandler {

    override fun getExceptionMessage(throwable: Throwable): ExceptionMessage =
        ExceptionMessage(
            code = "-2",
            message = "Não foi possível identificar a causa do erro no servidor, ele pode estar temporariamente indisponível."
        )
}

package com.lucasdam.githubchanllenge.shared.network

import android.content.Context
import com.lucasdam.githubchanllenge.R
import com.lucasdam.githubchanllenge.shared.model.domain.response.GenericResponse
import com.lucasdam.githubchanllenge.shared.model.view.ExceptionMessage
import com.google.gson.JsonSyntaxException
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.HttpException
import retrofit2.Response
import retrofit2.Retrofit
import timber.log.Timber
import java.io.IOException

/**
 * @author Lucas Marciano on 05/04/20.
 */

interface ThrowableHandler {
    fun getExceptionMessage(throwable: Throwable): ExceptionMessage
}

class ThrowableHandlerImpl(
    private var retrofit: Retrofit,
    private var context: Context
) : ThrowableHandler {

    companion object {
        const val MESSAGE_CODE_NO_CONNECTION = "-2"
        private const val DEFAULT_MESSAGE_CODE = "-1"
    }

    override fun getExceptionMessage(throwable: Throwable): ExceptionMessage {
        return when (throwable) {
            is HttpException -> {
                convertResponse(throwable.response()!!).let {
                    return ExceptionMessage(code = it?.code, message = it?.message)
                }
            }
            is IOException -> ExceptionMessage(
                code = MESSAGE_CODE_NO_CONNECTION,
                message = context.getString(R.string.throwable_handler_no_network)
            )
            is NullPointerException -> ExceptionMessage(
                code = DEFAULT_MESSAGE_CODE,
                message = throwable.message
            )
            else -> ExceptionMessage(
                code = DEFAULT_MESSAGE_CODE,
                message = context.getString(R.string.throwable_handler_generic_message)
            )
        }
    }

    private fun convertResponse(response: Response<*>): GenericResponse? {
        val converter: Converter<ResponseBody, GenericResponse?> = retrofit.responseBodyConverter(
            GenericResponse::class.java, arrayOfNulls<Annotation>(0)
        )

        try {
            return converter.convert(response.errorBody()!!)
        } catch (exception: Exception) {
            when (exception) {
                is IOException,
                is NullPointerException,
                is IllegalStateException,
                is JsonSyntaxException -> Timber.e(exception)
            }
        }

        return GenericResponse(
            message = context.getString(
                R.string.throwable_handler_generic_message
            )
        )
    }
}


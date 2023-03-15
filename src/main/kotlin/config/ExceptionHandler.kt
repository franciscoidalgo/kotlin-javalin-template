package config

import dto.ApiExceptionDto
import exception.ApiException
import io.javalin.http.Context

object ExceptionHandler {
    fun handleApiException(e: ApiException, ctx: Context) =
        ctx.json(ApiExceptionDto(e.message, e.statusCode))
            .status(e.statusCode)
}
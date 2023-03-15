package controller

import exception.ApiException
import io.javalin.http.Context

class PingController {
    fun ping(ctx: Context) {
        ctx.result("pong")
    }

    fun pingError(ctx: Context) {
        throw ApiException("expected pong error", 418)
    }
}
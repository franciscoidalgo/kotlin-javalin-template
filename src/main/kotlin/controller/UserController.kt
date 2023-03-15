package controller

import io.javalin.http.Context
import service.UserService
import javax.inject.Inject

class UserController @Inject constructor(val userService: UserService) {
    fun login(ctx: Context) {
        // Example endpoint
    }
}
package exception

class ApiException(override val message: String, val statusCode: Int) : RuntimeException(message)
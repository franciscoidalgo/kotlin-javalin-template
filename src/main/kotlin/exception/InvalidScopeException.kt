package exception

class InvalidScopeException(scope: String) : RuntimeException("Invalid scope '$scope'")
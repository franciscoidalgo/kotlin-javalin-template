package util

import config.ScopesEnum
import exception.InvalidScopeException

object ScopeUtils {
    fun getScope(): ScopesEnum {
        val scopeEnvVar = System.getenv("SCOPE") ?: "local"
        return ScopesEnum.getScopeByValue(scopeEnvVar) ?: throw InvalidScopeException(scopeEnvVar)
    }
}
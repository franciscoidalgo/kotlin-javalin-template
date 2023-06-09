package util

import org.slf4j.Logger
import org.slf4j.LoggerFactory

object LogUtils {
    inline fun <reified T> getLogger(): Logger = LoggerFactory.getLogger(T::class.java)
}
package util

import java.util.*

class Configuration(resourceName: String) {
    private val properties: Properties

    init {
        val loader: ClassLoader = Thread.currentThread().contextClassLoader
        properties = Properties()
        loader.getResourceAsStream(resourceName).use {
            properties.load(it)
        }
    }

    fun getString(key: String): String = properties.getProperty(key)

    fun getInt(key: String) = getString(key).toInt()

    fun getDouble(key: String) = getString(key).toDouble()

    fun getFloat(key: String) = getString(key).toFloat()
}
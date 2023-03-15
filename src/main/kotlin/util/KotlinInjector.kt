package util

import com.google.inject.Guice
import com.google.inject.Injector
import com.google.inject.Module

class KotlinInjector(vararg modules: Module) {
    val injector: Injector = Guice.createInjector(*modules)

    inline fun <reified T> getInstance(): T = injector.getInstance(T::class.java)

}
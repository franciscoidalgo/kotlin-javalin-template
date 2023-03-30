package config.injection

import com.google.inject.AbstractModule
import util.Configuration

class ConfigInjectionModule : AbstractModule() {
    override fun configure() {
        val configuration = Configuration("application.properties")
        bind(Configuration::class.java).toInstance(configuration)
    }
}
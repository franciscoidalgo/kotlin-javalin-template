package config.injection

import com.google.inject.AbstractModule
import repository.UserRepository
import repository.impl.UserRepositoryImpl

class RepositoryInjectionModule : AbstractModule() {

    override fun configure() {
        bind(UserRepository::class.java).to(UserRepositoryImpl::class.java).asEagerSingleton()
    }

}
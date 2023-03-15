package config.injection

import com.google.inject.AbstractModule
import com.google.inject.Provides
import com.google.inject.Singleton
import org.hibernate.SessionFactory
import org.hibernate.cfg.Configuration
import org.reflections.Reflections
import javax.persistence.Entity

class SessionFactoryInjectionModule : AbstractModule() {

    @Provides
    @Singleton
    fun providesSessionFactory(): SessionFactory {
        val configuration = Configuration().configure("hibernate.xml")
        Reflections("model").getTypesAnnotatedWith(Entity::class.java)
            .forEach { configuration.addAnnotatedClass(it) }

        return configuration.buildSessionFactory()
    }

}
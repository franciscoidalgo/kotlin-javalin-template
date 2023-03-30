package config.injection

import com.google.inject.AbstractModule
import com.google.inject.Inject
import com.google.inject.Provides
import com.google.inject.Singleton
import jakarta.persistence.Entity
import org.h2.server.web.WebServer
import org.h2.tools.Server
import org.hibernate.SessionFactory
import org.hibernate.boot.MetadataSources
import org.hibernate.boot.registry.StandardServiceRegistryBuilder
import org.reflections.Reflections
import util.Configuration
import util.LogUtils

class SessionFactoryInjectionModule : AbstractModule() {
    private val log = LogUtils.getLogger<SessionFactoryInjectionModule>()

    @Provides
    @Singleton
    @Inject
    fun providesSessionFactory(configuration: Configuration): SessionFactory {
        val server = Server(WebServer(), "-tcp", "-tcpPort", "8082")
        server.start()
        log.info(server.status)

        val standardServiceRegistry = StandardServiceRegistryBuilder()
            .applySetting("hibernate.connection.url", configuration.getString("database.url"))
            .applySetting("hibernate.connection.username", "sa")
            .applySetting("hibernate.connection.password", "")
            .applySetting("hibernate.dialect", "org.hibernate.dialect.H2Dialect")
            .applySetting("hibernate.show_sql", "true")
            .applySetting("hibernate.format_sql", "true")
            .applySetting("hibernate.current_session_context_class", "thread")
            .applySetting("hibernate.hbm2ddl.auto", "create-drop")
            .build()

        val metadataSources = MetadataSources(standardServiceRegistry)

        Reflections("model").getTypesAnnotatedWith(Entity::class.java).forEach {
            metadataSources.addAnnotatedClass(it)
        }

        val metadata = metadataSources.buildMetadata()

        return metadata.buildSessionFactory()
    }

}
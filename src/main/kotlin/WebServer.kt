import config.ExceptionHandler
import config.ScopesEnum
import config.injection.ConfigInjectionModule
import config.injection.JsonMapperInjectionModule
import config.injection.RepositoryInjectionModule
import config.injection.SessionFactoryInjectionModule
import controller.PingController
import exception.ApiException
import io.javalin.Javalin
import io.javalin.apibuilder.ApiBuilder.get
import io.javalin.apibuilder.ApiBuilder.path
import util.KotlinInjector
import util.LogUtils
import util.ScopeUtils

class WebServer {
    private val log = LogUtils.getLogger<WebServer>()

    private val injector = KotlinInjector(
        SessionFactoryInjectionModule(),
        RepositoryInjectionModule(),
        JsonMapperInjectionModule(),
        ConfigInjectionModule()
    )

    private val app = Javalin.create {
        it.jsonMapper(injector.getInstance())
    }

    private lateinit var scope: ScopesEnum

    fun start() {
        scope = ScopeUtils.getScope()
        log.info("Scope set to ${scope.value}")
        configureRoutes()
        configureExceptions()
        app.start(8080)
    }

    private fun configureRoutes() {
        val pingController: PingController = injector.getInstance()

        app.routes {
            path("/ping") {
                get(pingController::ping)
                get("/force_error", pingController::pingError)
            }

            path("/api") {

            }
        }
    }

    private fun configureExceptions() {
        app.exception(ApiException::class.java, ExceptionHandler::handleApiException)
    }

}
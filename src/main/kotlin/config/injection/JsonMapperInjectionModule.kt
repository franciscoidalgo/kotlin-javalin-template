package config.injection

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.google.inject.AbstractModule
import com.google.inject.Provides
import io.javalin.json.JavalinJackson
import io.javalin.json.JsonMapper
import io.jsonwebtoken.JwtBuilder
import io.jsonwebtoken.JwtParserBuilder
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.jackson.io.JacksonDeserializer
import io.jsonwebtoken.jackson.io.JacksonSerializer

class JsonMapperInjectionModule : AbstractModule() {

    private val objectMapper = ObjectMapper()
        .registerModule(JavaTimeModule())
        .setPropertyNamingStrategy(PropertyNamingStrategies.SnakeCaseStrategy())

    @Provides
    fun provideJsonMapper(): JsonMapper = JavalinJackson(objectMapper)

    @Provides
    fun provideJwtBuilder(): JwtBuilder = Jwts.builder()
        .serializeToJsonWith(JacksonSerializer(objectMapper))

    @Provides
    fun provideJwtParserBuilder(): JwtParserBuilder = Jwts.parserBuilder()
        .deserializeJsonWith(JacksonDeserializer(objectMapper))
}

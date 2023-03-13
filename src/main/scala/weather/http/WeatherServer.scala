package weather.http

import cats.effect.Async
import cats.syntax.all.*
import com.comcast.ip4s.*
import org.http4s.ember.client.EmberClientBuilder
import org.http4s.ember.server.EmberServerBuilder
import org.http4s.implicits.*
import org.http4s.server.middleware.Logger
import weather.http.routes.WeatherRoutes
import weather.services.cache.WeatherCache
import weather.services.externalApi.WeatherApi
import weather.programs.WeatherProgram

object WeatherServer:

  def run[F[_]: Async]: F[Nothing] = {
    for {
      client <- EmberClientBuilder.default[F].build
      weatherCache = WeatherCache.make[F]
      weatherAPi = WeatherApi.make[F](client)
      weatherProgram = WeatherProgram.make[F](weatherCache, weatherAPi)

      // Combine Service Routes into an HttpApp.
      // Can also be done via a Router if you
      // want to extract a segments not checked
      // in the underlying routes.
      httpApp = (
        WeatherRoutes[F](weatherProgram).routes
      ).orNotFound

      // With Middlewares in place
      finalHttpApp = Logger.httpApp(true, true)(httpApp)

      _ <- 
        EmberServerBuilder.default[F]
          .withHost(ipv4"0.0.0.0")
          .withPort(port"8080")
          .withHttpApp(finalHttpApp)
          .build
    } yield ()
  }.useForever

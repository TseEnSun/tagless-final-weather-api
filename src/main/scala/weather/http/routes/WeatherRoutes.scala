package weather.http
package routes

import cats.effect.Sync
import cats.implicits.*
import org.http4s.HttpRoutes
import org.http4s.dsl.Http4sDsl
import org.http4s.dsl.impl.QueryParamDecoderMatcher
import org.http4s.server.Router
import weather.programs.WeatherProgramAlgebra
import weather.services.Jokes
import weather.services.HelloWorld

object CityQueryParamMatcher extends QueryParamDecoderMatcher[String]("city")

final case class WeatherRoutes[F[_]: Sync](
  weather: WeatherProgramAlgebra[F]
) extends Http4sDsl[F] {
  private[routes] val prefixPath = "/currentWeather"

  private val httpRoutes: HttpRoutes[F] = HttpRoutes.of[F] {
    case GET -> Root :? CityQueryParamMatcher(city) =>
      Ok()
  }

  val routes: HttpRoutes[F] = Router(
    prefixPath -> httpRoutes
  )
}
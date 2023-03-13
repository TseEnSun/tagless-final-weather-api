package weather.services.externalApi

import org.http4s.client.Client

object WeatherApi{
  def make[F[_]](client: Client[F]): WeatherApiAlgebra[F] = ???
}
  

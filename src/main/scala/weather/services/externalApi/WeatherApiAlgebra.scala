package weather.services.externalApi

import weather.domain.Weather

trait WeatherApiAlgebra[F[_]] {
  def get(params: Map[String, String]): F[Weather]
}
  

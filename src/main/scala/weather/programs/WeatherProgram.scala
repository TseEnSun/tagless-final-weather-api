package weather.programs

import weather.domain.{City, Weather}
import weather.services.cache.WeatherCacheAlgebra
import weather.services.externalApi.WeatherApiAlgebra

object WeatherProgram {
  def make[F[_]](
    cacheService: WeatherCacheAlgebra[F],
    apiService: WeatherApiAlgebra[F]
  ): WeatherProgramAlgebra[F] = ???
}


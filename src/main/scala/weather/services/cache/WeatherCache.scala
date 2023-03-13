package weather.services.cache

object WeatherCache {
  def make[F[_]]: WeatherCacheAlgebra[F] = ???
}
  

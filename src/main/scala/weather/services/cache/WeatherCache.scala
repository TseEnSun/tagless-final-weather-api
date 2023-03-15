package weather.services.cache

import cats.syntax.all._
import cats.effect.Sync
import cats.effect.kernel.Ref

import weather.domain.Weather
import weather.domain.City

object WeatherCache {
  def make[F[_]: Sync](inMemoryStore: Ref[F, Map[String, Weather]]): WeatherCacheAlgebra[F] =
    new WeatherCacheAlgebra[F] {
      override def set(key: String, weather: Weather): F[Unit] =
        inMemoryStore.update(state => state + (key -> weather))

      override def get(key: String): F[Option[Weather]] =
        inMemoryStore.get.map(_.get(key))
    }
}
  

package weather

import cats.effect.{ExitCode, IO, IOApp}
import weather.http.WeatherServer

object Main extends IOApp.Simple:
  val run = WeatherServer.run[IO]

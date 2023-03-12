package com.seansun.weather

import cats.effect.{ExitCode, IO, IOApp}

object Main extends IOApp.Simple:
  val run = TaglessfinalweatherapiServer.run[IO]

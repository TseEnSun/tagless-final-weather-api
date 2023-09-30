package embedding

import scala.concurrent.{ Future, ExecutionContext }
import scala.collection.mutable.Map

object HigherKindTf {
  
  given ExecutionContext = ExecutionContext.global
  case class Id(value: Int) extends AnyVal
  case class EmailAddress(value: String) extends AnyVal

  trait DomoProgramAlg[F[_]] {
    def getEmail(id: Id): F[Option[EmailAddress]]
    def setEmail(id: Id, emailAddress: EmailAddress): F[Unit]
    def sendMessage(emailAddress: EmailAddress, message: String): F[Unit]
  }

  class DomoProgramInterpreter(state: Map[Id, EmailAddress]) extends DomoProgramAlg[Future] {
    def getEmail(id: Id): Future[Option[EmailAddress]] = Future.successful(state.get(id))
    
    def setEmail(id: Id, emailAddress: EmailAddress): Future[Unit] = 
      Future.successful(state += (id -> emailAddress))

    def sendMessage(emailAddress: EmailAddress, message: String): Future[Unit] = 
      Future.successful(println(s"Sending $message to $emailAddress"))
  }

  def main(args: Array[String]): Unit = {
    val interpreter = new DomoProgramInterpreter(Map.empty)

    for {
      _ <- Future.successful(println("-" * 15))
      _ <- interpreter.setEmail(Id(1), EmailAddress("jcconf@test.com"))
      email <- interpreter.getEmail(Id(1))
    } yield {
      email.foreach { emailAddress =>
        interpreter.sendMessage(emailAddress, "Hello World!")
        println("-" * 15)
      }
    }
  }
}

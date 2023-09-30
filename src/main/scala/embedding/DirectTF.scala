package embedding

import scala.collection.mutable.Map



object DirectTF {
  
  case class Id(value: Int) extends AnyVal
  case class EmailAddress(value: String) extends AnyVal

  trait DomoProgramAlg {
    def getEmail(id: Id): Option[EmailAddress]
    def setEmail(id: Id, emailAddress: EmailAddress): Unit
    def sendMessage(emailAddress: EmailAddress, message: String): Unit
  }

  class DomoProgramInterpreter(state: Map[Id, EmailAddress]) extends DomoProgramAlg {
    def getEmail(id: Id): Option[EmailAddress] = state.get(id)
    def setEmail(id: Id, emailAddress: EmailAddress): Unit = state += (id -> emailAddress)
    def sendMessage(emailAddress: EmailAddress, message: String): Unit = println(s"Sending $message to ${emailAddress.value}")
  }

  def main(args: Array[String]): Unit = {
    println("-" * 15)
    val interpreter = new DomoProgramInterpreter(Map.empty)

    interpreter.setEmail(Id(1), EmailAddress("jcconf@test.com"))
    
    interpreter.getEmail(Id(1)).foreach { emailAddress =>
      interpreter.sendMessage(emailAddress, "Hello World!")
    }

    println("-" * 15)
  }
}

package basic

import java.awt.Point

object Syntax {
  
  val x: Int = 1 + 1

  class TimeBomb(timer: Int) {
    def fire(): Unit = {
      Thread.sleep(timer)
      println("BOOM!")
    }
  }

  case class Point(x: Int, y: Int) {
    def move(dx: Int, dy: Int): Point = Point(x + dx, y + dy)
  }

  val point = Point(1, 2)
  val anotherPoint = Point(1, 2)

  point == anotherPoint // true

  trait Greeter {
    def greet(name: String): Unit
  }

  class DefaultGreeter extends Greeter {
    override def greet(name: String): Unit = println(s"Hello, $name")
  }

  // type class
  trait Showable[A]{
    def show(a: A): String
  }

  given Showable[Point] with {
    def show(a: Point): String = s"Point(${a.x}, ${a.y})"
  }

  def showAll[A](list: List[A])(using s: Showable[A]): String = 
    list.map(s.show).mkString(", ")

  showAll(List(Point(1, 2), Point(3, 4)))

  trait Developer

  case class ScalaDeveloper(name: String) extends Developer
  case class JavaDeveloper(name: String) extends Developer

  def greetDeveloper(developer: Developer): String = developer match {
    case ScalaDeveloper(name) => s"Hello, Scala developer $name"
    case JavaDeveloper(name) => s"Hello, Java developer $name"
  }
  

  def main(args: Array[String]): Unit = {
    println("-" * 15)
    println(s"x: $x")
    println("-" * 15)
    println(s"point: $point")
    println(s"anotherPoint: $anotherPoint")
    println("-" * 15)
    println(s"showAll: ${showAll(List(Point(1, 2), Point(3, 4)))}")
    println("-" * 15)
  }

}

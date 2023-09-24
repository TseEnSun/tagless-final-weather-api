package embedding

object InitialExtension {
  import embedding.{Initial => Old}

  trait Exp
  case class OldExp(e: Old.Exp) extends Exp
  case class Mul(e1: Exp, e2: Exp) extends Exp

  val ti2 = Mul(OldExp(Old.Lit(7)), OldExp(Old.ti1))
  // val tim1 = OldExp(Old.Add(Old.Lit(7), (Old.Neg(Mul(OldExp(Old.Lit(1)), OldExp(Old.Lit(2)))))))
  
  def eval(exp: Exp): Int = exp match {
    case OldExp(e) => Old.eval(e)
    case Mul(e1, e2) => eval(e1) * eval(e2)
  }

  def view(exp: Exp): String = exp match {
    case OldExp(e) => Old.view(e)
    case Mul(e1, e2) => "(" ++ view(e1) ++ "*" ++ view(e2) ++ ")"
  }

  def main(args: Array[String]): Unit = {
    println("-" * 15)
    println(s"ti1_eval: ${Old.eval(Old.ti1)}")
    println(s"ti1_view: ${Old.view(Old.ti1)}")
    println(s"ti2_eval: ${eval(ti2)}")
    println(s"ti2_view: ${view(ti2)}")
    println("-" * 15)
  }

}

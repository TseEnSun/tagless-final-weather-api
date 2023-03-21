package embedding

object TypeClassFinal {

  trait ExpSYM[repr]:
    def lit(n: Int): repr
    def neg(e: repr): repr
    def add(e1: repr , e2: repr): repr

  object ExpSYMInt extends ExpSYM[Int]:
    def lit(n: Int) = n
    def neg(e: Int) = -e
    def add(e1: Int, e2: Int) = e1 + e2

  object ExpSYMString extends ExpSYM[String]:
    def lit(n: Int) = s"$n"
    def neg(e: String) = s"(-$e)"
    def add(e1: String, e2: String) = s"($e1+$e2)" 

  def tf1[A](exp: ExpSYM[A]): A = exp.add(exp.lit(8), exp.neg(exp.add(exp.lit(1), exp.lit(2))))

  def main(args: Array[String]): Unit = {
    println("-" * 15)
    println(s"tf1_eval: ${tf1(ExpSYMInt)}")
    println(s"tf1_view: ${tf1(ExpSYMString)}")
    println("-" * 15)
  }

}

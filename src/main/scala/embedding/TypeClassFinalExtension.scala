package embedding

object TypeClassFinalExtension {
  import TypeClassFinal.*

  trait MulSYM[repr]:
    def mul(e1: repr, e2: repr): repr

  object MulSYM:
    def apply[repr](using m: MulSYM[repr]): MulSYM[repr] = m

  given MulSYM[Int] with
    def mul(e1: Int, e2: Int) = e1 * e2

  given MulSYM[String] with
    def mul(e1: String, e2: String) = s"($e1*$e2)"
  
  def tf2[A](using exp: ExpSYM[A], mul: MulSYM[A]): A = mul.mul(exp.lit(8), exp.neg(mul.mul(exp.lit(1), exp.lit(2))))

  def tf2Alter[A: ExpSYM : MulSYM] = MulSYM[A].mul(ExpSYM[A].lit(8), ExpSYM[A].neg(MulSYM[A].mul(ExpSYM[A].lit(1), ExpSYM[A].lit(2))))

  def main(args: Array[String]): Unit = {
    println("-" * 15)
    println(s"tf2_eval: ${eval(tf2)}")
    println(s"tf2_view: ${view(tf2)}")
    println("-" * 15)
  }
}

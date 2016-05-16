package domain.numbertheory

import scala.math._

object BezoutCoefficients {

  private var s_0 = 1

  private var s_1 = 0

  private var t_0 = 0

  private var t_1 = 1

  /**
   * We set s0 = 1, s1 = 0, t0 = 0, and t1 = 1
   * and let sj = sj−2 − qj−1sj−1
   * and tj = tj−2 − qj−1tj−1 for j = 2,3,...,n,
   * where the qj are the quotients in the divisions used when the Euclidean algorithm finds gcd(a,b), a
   * s shown in the text. It can be shown (see [Ro10]) that gcd(a, b) = sna + tnb.
   */
  def solve(a: Int, b: Int): (Int, Int, Int) = {
    require(a > 0 && b > 0)
    val g = gcd(max(a, b), min(a, b))
    (g, s_0, t_0)
  }

  private def gcd(a: Int, b: Int): Int = {
    if (b == 0)
      a
    else {
      val q = a / b
      val r = a % b

      val s = s_0 - q * s_1
      val t = t_0 - q * t_1

      s_0 = s_1
      s_1 = s
      t_0 = t_1
      t_1 = t

      gcd(b, r)
    }

  }

  def main(args: Array[String]): Unit = {
    var a = 252
    var b = 198
    val coefficients = solve(a, b)

    println(coefficients._1 + " = " + coefficients._2 + " * " + a + " + " + coefficients._3 + " * " + b)
    println(coefficients._2  *  a + coefficients._3  * b)

  }

}
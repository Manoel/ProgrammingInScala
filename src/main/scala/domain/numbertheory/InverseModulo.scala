package domain.numbertheory

object InverseModulo {

  def inverse(a: Int, b: Int): Int = {
    val coefficients = BezoutCoefficients.solve(a, b)

    val i = if (a > b)
      coefficients._2
    else
      coefficients._3

    if (i < 0) i + b else i
  }

  def main(args: Array[String]): Unit = {
    println(inverse(3, 7))
    println(inverse(101, 4620))
  }
}
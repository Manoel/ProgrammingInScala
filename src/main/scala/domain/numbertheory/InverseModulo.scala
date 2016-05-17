package domain.numbertheory

object InverseModulo {
  
  def inverse(a: Int, b: Int): Int = {
    val coefficients = BezoutCoefficients.solve(a, b)
    
    if (a > b)
      coefficients._2
    else
      coefficients._3
  }
  
  def main(args: Array[String]): Unit = {
    println(inverse(3, 7))
    println(inverse(101, 4620))
  }
}
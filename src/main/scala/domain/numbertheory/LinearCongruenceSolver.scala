package domain.numbertheory

object LinearCongruenceSolver {

  /**
   * Given n linear congruences modulo pairwise relatively
   * prime moduli, find the simultaneous solution of these congruences
   * modulo the product of these moduli.
   */
  def solve(congruences: List[LinearCongruence]): Long = {
    val product = congruences.map { c => c.modulo }.reduce(_ * _)
    
    val sum = congruences.map { c => (c.a, product / c.modulo, InverseModulo.inverse(product / c.modulo, c.modulo)) }
      .map { t => t._1.toLong * t._2.toLong * t._3.toLong }.reduce(_ + _)

    sum % product
  }

  def main(args: Array[String]): Unit = {
    var congruences = List(LinearCongruence(2, 3), LinearCongruence(3, 5), LinearCongruence(2, 7))

    println(solve(congruences))
    
    congruences = List(LinearCongruence(65, 99), LinearCongruence(2, 98), LinearCongruence(51, 97), LinearCongruence(10, 95))

    println(solve(congruences))
    
  }

}
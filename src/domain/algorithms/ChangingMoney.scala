package domain.algorithms

import scala.collection.mutable

/**
 * Given an integer n, use the greedy algorithm to find the
 * change for n cents using quarters (25 cents), dimes (10 cents),
 * nickels (5 cents), and pennies (1 cent).
 */
object ChangingMoney {

  val QUARTER = 25
  val DIME = 10
  val NICKEL = 5
  val PENNY = 1

  def change(n: Int): Map[Int, Int] = {
    require(n > 0)

    val cents = Array(QUARTER, DIME, NICKEL, PENNY)
    val result = mutable.Map(QUARTER -> 0, DIME -> 0, NICKEL -> 0, PENNY -> 0)

    var N = n

    for (i <- 0 until cents.length) {
      while (N >= cents(i)) {
        N -= cents(i)
        result(cents(i)) += 1
      }
    }

    result.toMap
  }

  def main(args: Array[String]) {
    val changing = change(67)
    println("QUARTERS: " + changing(QUARTER))
    println("DIMES...: " + changing(DIME))
    println("NICKELS.: " + changing(NICKEL))
    println("PENNYS..: " + changing(PENNY))
  }

}
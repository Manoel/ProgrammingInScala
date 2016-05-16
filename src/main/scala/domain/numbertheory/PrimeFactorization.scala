package domain.numbertheory

import scala.collection.mutable.ListBuffer

object PrimeFactorization {

  def primeFactors(i: Int): List[Int] = {
    require(i > 0)

    val factors = ListBuffer[Int]()

    var n = i;
    var d = 2;
    while (n > 1) {
      while (n % d == 0) {
        factors += d
        n = n / d
      }
      d += 1
    }

    factors.toList
  }

  def main(args: Array[String]) {
    println(primeFactors(1))
    println(primeFactors(2))
    println(primeFactors(3))
    println(primeFactors(4))
    println(primeFactors(5))
    println(primeFactors(6))
    println(primeFactors(7))
    println(primeFactors(8))
    println(primeFactors(9))
    println(primeFactors(10))
    println(primeFactors(11))
    println(primeFactors(12))
    println(primeFactors(38))
    println(primeFactors(40))
  }

}
package domain.numbertheory

import scala.collection.mutable.ListBuffer
import scala.math._

object BaseExpansion {

  def baseExpansion(n: Int, b: Int): List[Int] = {
    require(n > 1)
    require(b > 1)

    var q = n / b
    var r = n % b

    val result = ListBuffer(r)

    while (q != 0) {
      r = q % b
      q = q / b
      result += r
    }

    result.reverse.toList
  }

  def modulus(a: Int, b: Int): (Int, Int) = {
    var q = a / b
    var r = a % b

    if (r < 0) {
      r += abs(b)
      if (q > 0)
        q += 1
      else
        q -= 1
    }

    (q, r)
  }

  def main(args: Array[String]) {
    println(baseExpansion(2, 2))
    println(baseExpansion(3, 2))
    println(baseExpansion(4, 2))
    println(baseExpansion(5, 2))
    println(baseExpansion(6, 2))
    println(baseExpansion(7, 2))
    println(baseExpansion(8, 2))
    println(baseExpansion(9, 2))
    println(baseExpansion(10, 2))

    println(-8 % 3 + " " + -8 / 3)

    println(8 % -3 + " " + 8 / -3)

    println(-8 % -3 + " " + -8 / -3)
    
    println(modulus(-8, 3))
    println(modulus(8, -3))
    println(modulus(-8, -3))
    println(modulus(8, 3))
  }

}
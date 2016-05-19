package domain.numbertheory

import scala.util.Random
import scala.collection.mutable.ListBuffer

object MillerTest {

  /**
   * s is a nonnegative integer
   */
  var s = 697

  /**
   * t is an odd positive integer
   */
  val t = 13

  var n = generateN()

  def generateN(): BigInt = (BigInt(2).pow(s) * BigInt(t)) + BigInt(1)

  def generateB(): BigInt = BigInt.apply(s + log2(t) + 1, Random)

  def test(): Boolean = {
    val b = generateB()
    val b_to_t_mod_n = modExp(b, t, n)

    if (b_to_t_mod_n == 1)
      true
    else {
      var passes = false
      var j = 0
      while (!passes && j < s) {
        val exp = BigInt(2).pow(j) * t
        val b_to_2_to_j_times_t_mod_n = modExp(b, exp, n)
        
        if (b_to_2_to_j_times_t_mod_n == -1 || b_to_2_to_j_times_t_mod_n - n == -1)
          passes = true
          
        j += 1
      }
      passes      
    }
  }
  
  def isProbablePrime(): Boolean = {
    var k = 30
    while (test() && k > 0) {
      k -= 1
    }
    k == 0
  }
  
  def probablePrime(): BigInt = {
    while (!isProbablePrime()) {
      s += 1
      n = generateN()
    }
    n
  }
  
  def twoProbablePrimes(): (BigInt, Int, BigInt, Int) = {
    val p1 = probablePrime()
    val s1 = s
    s += 1
    n = generateN()
    val p2 = probablePrime()
    val s2 = s
    (p1, s1, p2, s2)
  }
  
  private def baseExpansion(n: BigInt, b: Int): List[Int] = {
    require(n > 1)
    require(b > 1)

    var q = n / b
    var r = n % b

    val result = ListBuffer(r.intValue())

    while (q != 0) {
      r = q % b
      q = q / b
      result += r.intValue()
    }

    result.toList
  }

  private def modExp(b: BigInt, t: BigInt, n: BigInt): BigInt = {
    val n_2 = baseExpansion(t, 2)

    var b_to_n = BigInt(1)

    var acum = modulus(b, n)

    for (i <- 0 until n_2.length) {

      if (n_2(i) == 1) {
        b_to_n *= acum
        b_to_n = modulus(b_to_n, n)
      }

      acum *= acum
      acum = modulus(acum, n)
    }

    b_to_n
  }

  private def modulus(a: BigInt, b: BigInt): BigInt = {
    var q = a / b
    var r = a % b

    if (r < 0) {
      r += b.abs
      if (q > 0)
        q += 1
      else
        q -= 1
    }

    r
  }
  

  private def log2(x: Int): Int = (scala.math.log(x) / scala.math.log(2)).intValue()

  def main(args: Array[String]) {
    println(twoProbablePrimes())
  }

}
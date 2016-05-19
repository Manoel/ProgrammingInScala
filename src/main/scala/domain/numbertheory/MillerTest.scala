package domain.numbertheory

import scala.util.Random
import scala.collection.mutable.ListBuffer

object MillerTest {

  /**
   * s is a nonnegative integer
   */
  var s = 4

  /**
   * t is an odd positive integer
   */
  val t = BigInt("231916681452058459913938119644422010983954668505838345984479056394668975009054953727852640279939921902922733015695847889131865692624867609496734092037330401224974706036439624474339562209779062172031")

  var n = generateN()

  def generateN(): BigInt = (BigInt(2).pow(s) * t) + BigInt(1)

  def generateB(): BigInt = BigInt.apply(n.bitCount, Random)
  
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
    println("s: " + s)
    println("n: " + n)
    while (!isProbablePrime()) {
      println("s: " + s)
      println("n: " + n)
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
    val prime = BigInt.probablePrime(660, Random)
    
    val n = prime - 1
   
    println(prime)
    
    println(n)
        
    var bitCount = n.bitCount
    var m = BigInt(2).pow(bitCount)
    var r = n % m 
  
    println(bitCount)
    println(r)
    
    while (m > n || r != 0) {
      bitCount -= 1
      m = BigInt(2).pow(bitCount)
      r = n % m
      
      println(bitCount)
      println(r)
    
    }
    println(bitCount)
    println(r)
    println(n / m)
    
    println(isProbablePrime())
    println(this.n)  
        
  }

}
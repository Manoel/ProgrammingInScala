package domain.numbertheory

import scala.collection.mutable.ListBuffer

object CantorExpansion {

  def cantorExpansion(N: Int): Array[Int] = {
    require(N > 0)

    var n = N
    var fat = 1
    var i = 1

    while (fat <= n) {
      i += 1
      fat *= i
    }

    fat /= i
    i -= 1

    val result = Array.ofDim[Int](i)

    var acum = 0

    while (acum < N) {
      var j = 1
      var r = j * fat

      while (j < i && r < n) {
        j += 1
        r = j * fat
      }

      if (r > n) {
        j -= 1
        r -= fat
      }

      result(i - 1) = j
      acum += r
      fat /= i
      i -= 1
      n -= r
    }

    result
  }

  def decimalToCantor(n: Int): List[Int] = {
    var q = n;
    var i = 2;

    val result = ListBuffer[Int]()

    while (q > 0) {
      result += q % i
      q = q / i
      i += 1
    }

    result.toList
  }
  
  def cantorToDecimal(cantor: Array[Int]): Int = {
    var fat = 1
    var result = 0
    
    for (i <- 0 until cantor.length) {
      result += fat * cantor(i)
      fat *= (i + 2)
    }
    
    result
  }
  
  def add(cantor1: Array[Int], cantor2: Array[Int]) : Array[Int] = {
    
    val length = if (cantor1.length > cantor2.length) cantor1.length else cantor2.length
    
    val result = ListBuffer[Int]()
      
    var p = 2
    
    var carry = 0
    
    for (i <- 0 until length) {
      val c1 = if (i < cantor1.length) cantor1(i) else 0
      
      val c2 = if (i < cantor2.length) cantor2(i) else 0
      
      val sum = c1 + c2 + carry
      
      result += sum % p
      
      if (sum >= p)
        carry = 1
      else
        carry = 0
      
      p += 1
    }
    
    if (carry > 0) {
      result += 1
    }
    
    result.toArray
  }

  def main(args: Array[String]) {
    
    println(cantorExpansion(10).deep.mkString(" ")+ " new implementation: " + decimalToCantor(10))
    println(cantorExpansion(463).deep.mkString(" ")+ " new implementation: " + decimalToCantor(463))
    
    println(cantorToDecimal(Array(0, 2, 1)))
    println(cantorToDecimal(Array(1, 0, 1, 4, 3)))
    
    println(cantorExpansion(20).deep.mkString(" "))
    
    println(add(Array(0, 2, 1), Array(0, 2, 1)).deep.mkString(" "))
        
    val sum = add(Array(1, 0, 1, 4, 3), Array(1, 0, 1, 4, 3))
    
    println(sum.deep.mkString(" "))
    println(cantorToDecimal(sum))
    
    val d37 = decimalToCantor(37)
    
    val d49 = decimalToCantor(49)
    
    val d37_plus_d49 = add(d37.toArray, d49.toArray)
    
    println(cantorToDecimal(d37_plus_d49))
    
  }

}
package domain.counting

import scala.collection.mutable.ListBuffer

object UneatenLeaves {

  /**
   * Example:
   *
   * leaves = 28, jumps = {2, 4, 7}
   *
   * Eaten leaves:
   *
   * By each one individually:
   *
   * + 28 / 2 = 14
   *
   * + 28 / 4 = 7
   *
   * + 28 / 7 = 4
   *
   * By each two:
   *
   * - 28 / lcm(2,4)
   *
   * - 28 / lcm(2,7)
   *
   * - 28 / lcm(4, 7)
   *
   * By each three:
   *
   * + 28 / lcm(2, 4, 7)
   */
  def count(leaves: Int, jumps: Array[Int]): Int = {
    val subsets = Combination.subsets(jumps.length)
    
    var eaten = 0
    
    for (i <- 1 until subsets.length) {
      val set = subsets(i)
      val elems = ListBuffer[Int]()
      
      for (j <- 0 until set.length) {
        if (set(j) == 1) {
          elems += jumps(j)
        }
      }
      
      var l = elems(0)
      
      for (k <- 1 until elems.length) {
        l = lcm(l, elems(k))
      }
      
      var factor = 1
      
      if (elems.length % 2 == 0)
        factor = -1
        
      eaten += factor * leaves / l
    }
    
    leaves - eaten
  }
  
  private def gcd(a: Int, b: Int): Int = {
    if (a == 0)
      b
    else if (b == 0)
      a
    else if (a > b) 
      gcd(b, a % b)
    else
      gcd(a, b % a)
  }
  
  private def lcm(a: Int, b: Int): Int = {
    a * b / gcd(a, b)
  }
  
  def main(agrs: Array[String]) {
    println(count(28, Array(2, 4, 7)))
    println(count(24, Array(2, 3, 4)))
    println(count(24, Array(1, 3, 4)))
  }
}
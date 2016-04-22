package domain.solution.bits.binarygap

import scala.collection.JavaConversions._

// you can write to stdout for debugging purposes, e.g.
// println("this is a debug message")

object Solution {
  def solution(N: Int): Int = {
    // write your code in Scala 2.10
    require(N >= 1 && N <= 2147483647)
    var n = N
    var zeros = 0
    var max = 0
    var foundOne = false
    while (n > 0) {
      val current = n & 1
      n = n >> 1
      if (foundOne && current == 0)
        zeros += 1
      if (current == 1) {
        foundOne = true
        if (zeros > max)
          max = zeros
        zeros = 0
      }
    }
    max
  }
  
  def main(args: Array[String]) {
    println(solution(9))
    println(solution(529))
    println(solution(20))
    println(solution(15))
    println(solution(1041))
    println(solution(1))
    println(solution(7))
  }
  
}
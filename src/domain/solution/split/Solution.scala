package domain.solution.split

import scala.collection.JavaConversions._

// you can write to stdout for debugging purposes, e.g.
// println("this is a debug message")

object Solution {
  
  def solution(X: Int, A: Array[Int]): Int = {
    val n = A.length

    val countEquals = A.toIterable.filter(e => e == X).size

    if (countEquals == 0)
      n
    else if (countEquals == n)
      0
    else {
      var k = -1
      var i = 0
      var equalsX = 0
      var diffX = 0

      while (k == -1 && i < n) {
        if (A(i) == X)
          equalsX += 1
        
        var l = n - (i + 1)
        diffX = l - (countEquals - equalsX)
        
        i += 1
        
        if (equalsX == diffX)
          k = i
      }

      k
    }
    
  }
  
  def main(args: Array[String]) {
    
    println((6 / -2) + " " + (6 % (-2)))
    println((-3 / -2) + " " + (-3 % (-2)))
    println((-1 / -2) + " " + (-1 % (-2)))
    println((1 / -2) + " " + (1 % (-2)))
//    println(solution(5, Array(5, 5, 1, 7, 2, 3, 5)))
//    println(solution(5, Array(5, 5, 5, 5, 5, 5, 5)))
//    println(solution(5, Array(1, 1, 1, 1, 1, 1, 1)))
//    println(solution(5, Array(5)))
//    println(solution(5, Array(1, 5)))
  }
}
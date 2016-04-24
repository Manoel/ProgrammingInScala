package domain.solution.equilibrium

import scala.collection.JavaConversions._
import java.math.BigInteger

// you can write to stdout for debugging purposes, e.g.
// println("this is a debug message")

object Solution {
  def solution(A: Array[Int]): Int = {
    if (A == null || A.length == 0)
      -1
    else {
      var result = -1
      var index = 0
      var leftSum = BigInteger.ZERO
      var rigthSum = BigInteger.ZERO

      for (i <- 1 until A.length)
        rigthSum = rigthSum.add(BigInteger.valueOf(A(i)))

      while (result == -1 && index < A.length) {
        println(index + " " + leftSum + " " + rigthSum)
        if (leftSum == rigthSum)
          result = index
        else {
          leftSum = leftSum.add(BigInteger.valueOf(A(index)))

          if (index + 1 < A.length)
            rigthSum = rigthSum.subtract(BigInteger.valueOf(A(index + 1)))

          index += 1
        }
      }

      result
    }
  }

  def main(args: Array[String]) {
    println(solution(Array(0, 2147483640, -2147483640)))
  }
}
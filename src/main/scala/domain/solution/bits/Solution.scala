package domain.solution.bits

import scala.collection.JavaConversions._
import scala.collection.mutable.ListBuffer

object Solution {
  
  def minus(A: Array[Int]): Array[Int] = {
    val X = sum(A)
    if (X == 0)
      Array[Int]()
    else {
      var q = (-X) / (-2)
      var r = (-X) % (-2)
      
      val result = ListBuffer[Int](r)
      
      while (q != 0) {
    	  r = q % (-2)
        q = q / (-2)
        if (r < 0) {
          q += 1
          r += 2
        }
        result += r
      }
      
      result.toArray
    }
  }
  
  def solution(A: Array[Int]): Array[Int] = {
    val X = sum(A)
    if (X == 0)
      Array[Int]()
    else {
      var newValue = -X
      var len = length(newValue)
      val result = new Array[Int](len)
      result(len - 1) = 1
      var remainder = newValue - scala.math.pow(-2, len - 1).toInt
      while (remainder != 0) {
        var tempLen = length(remainder)
        for (i <- tempLen until (len - 1))
          result(i) = 0
        result(tempLen - 1) = 1
        len = tempLen
        newValue = remainder
        remainder = newValue - scala.math.pow(-2, len - 1).toInt
      }
      result
    }
  }
  def length(x: Int): Int = {
    if (x == -1)
      2
    else if (x == 1)
      1
    else {
      val y = scala.math.abs(x)
      var i = 0
      while (scala.math.pow(-2, i) < y) {
        i += 1
      }
      if (x > 0) i += 1
      i
    }
  }

  def sum(A: Array[Int]): Int = {
    if (A == null || A.length == 0)
      0
    else {
      var s = 0

      for (i <- 0 until A.length)
        s += A(i) * scala.math.pow(-2, i).toInt

      s
    }
  }

  def main(args: Array[String]) {
//    println("output: " + solution(Array(1, 0, 0, 1, 1, 1)).deep.mkString(" "))
//    println("output: " + solution(Array(1, 0, 0, 1, 1)).deep.mkString(" "))
//    println("output: " + solution(Array()).deep.mkString(" "))
//    println("output: " + solution(null).deep.mkString(" "))
//    println("output: " + solution(Array(0, 0)).deep.mkString(" "))
    
    var input = Array(0, 0, 0, 0, 1, 1)
    var output = solution(input)
    println("input: " + sum(input) + " output: sum = " + sum(output) + " array: " + output.deep.mkString(" "))
    
    input = Array(0, 0, 0, 0, 1)
    output = solution(input)
    println("input: " + sum(input) + " output: sum = " + sum(output) + " array: " + output.deep.mkString(" "))
    
    input = Array(0, 0, 0, 0, 1, 1)
    output = minus(input)
    println("input: " + sum(input) + " output: sum = " + sum(output) + " array: " + output.deep.mkString(" "))
    
    input = Array(0, 0, 0, 0, 1)
    output = minus(input)
    println("input: " + sum(input) + " output: sum = " + sum(output) + " array: " + output.deep.mkString(" "))
    
  }

}
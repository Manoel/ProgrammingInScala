package domain.numbertheory

import scala.collection.mutable.ListBuffer

object LinearCongruentialGenerator {
  
  def generate(N: Int, m: Int, a: Int, c: Int, x_0: Int):List[Int] = {
    require(N > 0)
    require(a >= 0 && a < m)
    require(c >= 0 && c < m)
    require(x_0 >= 0 && x_0 < m)
    
    val result = ListBuffer[Int]()
    
    result += x_0
    
    var previous = x_0
    
    var i = 1
    
    while (i < N) {
      val next = (a * previous + c) % m
      result += next
      previous = next
      i += 1
    }
    
    result.toList
  }
  
  def main(args: Array[String]) {
    println(generate(19, 9, 7, 4, 3))
  }
}
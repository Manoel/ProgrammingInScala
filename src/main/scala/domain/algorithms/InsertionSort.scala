package domain.algorithms

import scala.collection.mutable.ListBuffer

object InsertionSort {
  
  def sort(numbers: List[Int]) : List[Int] = {
    require(numbers != null)
    require(numbers.size > 1)
    
    val result = ListBuffer() ++ numbers
    
    for (j <- 1 until numbers.size) {
      var i = 0
      
      while (result(j) > result(i))
        i += 1
      
      var m = result(j)
      
      for (k <- 0 to j - i - 1)
        result(j - k) = result(j - k - 1)
      
      result(i) = m
    }
    
    result.toList
  }
  
  def main(args: Array[String]) {
    println(sort(List(3, 2, 4, 1, 5)))
  }
  
}
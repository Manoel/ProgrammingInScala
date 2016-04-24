package domain.algorithms

import scala.collection.mutable.ListBuffer

object BubbleSort {

  def sort(numbers: List[Int]): List[Int] = {
    require(numbers != null)
    require(numbers.size > 1)

    val result = ListBuffer() ++ numbers

    for (i <- 0 until numbers.size - 1) {
      for (j <- 0 until numbers.size - i - 1) {
        if (result(j) > result(j + 1)) {
          val temp = result(j)
          result(j) = result(j + 1)
          result(j + 1) = temp
        }
      }

    }

    result.toList
  }

  def main(args: Array[String]) {
    println(sort(List(3, 2, 4, 1, 5)))
  }

}
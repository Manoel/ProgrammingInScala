package domain.algorithms

object BinarySearch {

  def search(numbers: List[Int], n: Int): Int = {
    require(numbers != null)
    require(numbers.size > 0)

    var index = -1

    var middle = -1
    var start = 0
    var end = numbers.size - 1

    while (index == -1 && start <= end) {
      middle = (start + end) / 2
      if (numbers(middle) == n)
        index = middle
      else if (numbers(middle) < n)
        start = middle + 1
      else
        end = middle - 1
    }

    index
  }

  def main(args: Array[String]) {
    println(search(List(1), 0))
    println(search(List(1, 2), 0))
    println(search(List(1, 2, 3), 0))
    println(search(List(1, 2, 3), 1))
    println(search(List(1, 2, 3), 2))
    println(search(List(1, 2, 3), 3))
    println(search(List(1, 2, 3, 4), 0))
    println(search(List(1, 2, 3, 4), 5))
    println(search(List(1, 2, 3, 4), 1))
    println(search(List(1, 2, 3, 4), 2))
    println(search(List(1, 2, 3, 4), 3))
    println(search(List(1, 2, 3, 4), 4))
  }

}
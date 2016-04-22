package domain.algorithms

object NumberOfComparisonsLinearAndBinarySearch {

  def linearSearch(numbers: List[Int], n: Int): Int = {
    require(numbers != null)
    require(numbers.size > 0)

    var index = -1
    var i = 0
    var comparisons = 0

    while (index == -1 && i < numbers.size) {
      if (numbers(i) == n)
        index = i
      i += 1
      comparisons += 3
    }
    comparisons += 2

    comparisons
  }

  def binarySearch(numbers: List[Int], n: Int): Int = {
    require(numbers != null)
    require(numbers.size > 0)

    var index = -1

    var middle = -1
    var start = 0
    var end = numbers.size - 1
    var comparisons = 0

    while (index == -1 && start <= end) {
      middle = (start + end) / 2
      if (numbers(middle) == n) {
        index = middle
        comparisons += 3
      } else {
        comparisons += 4
        if (numbers(middle) < n)
          start = middle + 1
        else
          end = middle - 1
      }
    }
    
    comparisons += 2

    comparisons
  }

}
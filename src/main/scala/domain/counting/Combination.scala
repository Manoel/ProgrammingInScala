package domain.counting

object Combination {
  
  def subsets(n: Int): Array[Array[Int]] = {
    val num_subsets = scala.math.pow(2, n).toInt
      
    val result = new Array[Array[Int]](num_subsets)
    result(0) = new Array[Int](n)
    
    for (i <- 1 until num_subsets)
      result(i) = nextValue(result(i - 1))
      
    result
  }

  def nextValue(c: Array[Int]): Array[Int] = {
    val copy = new Array[Int](c.length)
    
    Array.copy(c, 0, copy, 0, c.length)
    
    var i = copy.length - 1

    while (i >= 0 && copy(i) == 1) {
      copy(i) = 0
      i -= 1
    }

    if (i >= 0) {
      copy(i) = 1
    }

    copy

  }

  /**
   * Next, an algorithm for generating the r-combinations of the set {1, 2, 3, . . . , n}
   * will be given. An r-combination can be represented by a sequence containing the
   * elements in the subset in increasing order. The r-combinations can be listed using
   * lexicographic order on these sequences. In this lexicographic ordering, the first
   * r-combination is {1, 2, . . . , r - 1, r} and the last r-combination is
   * {n - r + 1, n - r + 2, . . . , n - 1, n}. The next r-combination after a1a2...ar 
   * can be obtained in the following way: First, locate the last element ai in the
   * sequence such that ai != n - r + i. Then, replace ai with ai + 1 and aj with 
   * ai + j - i + 1, for j = i + 1, i + 2, ..., r. It is left for the reader to show 
   * that this produces the next larger r-combination in lexicographic order. 
   * 
   * Explanation:
   * 
   * it necessary to increase from right to left, because we want the next value
   * in the lexicographic order. If the value in a certain position from right to left
   * is already the maximum allowed value for this position, so it can not be the 
   * starting point. We need to move left to check other position. To know if the
   * value is already the maximum at a specific position, we need to know the maximum
   * value allowed, let be n, the number of elements in the combination, let be r.
   * 
   * The maximum value allowed in a specific position, i, is obtained from the formula:
   * 
   * n - r + i
   * 
   * If the value in position i is not the maximum, so, we can increase it one unity
   * and the numbers after it are successors of the number in its left.
   * 
   * If all numbers are already the maximum allowed in each position, so the value is
   * already the last one.
   * 
   */
  def nextRCombination(c: Array[Int], n: Int) = {
    var r = c.length
    var i = c.length - 1

    while (i >= 0 && c(i) == n - r + i + 1)
      i -= 1

    if (i == -1)
      c
    else {
      c(i) += 1

      for (j <- i + 1 until r)
        c(j) = c(i) + j - i

      c
    }
  }

  def main(args: Array[String]) {
    val ss = subsets(3)
    
    for (s <- ss)
      UtilArray.printArray(s)
  }

}
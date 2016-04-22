package domain.counting

object Permutation {

  def nextPermutation(p: Array[Int]): Array[Int] = {
    require(p != null)
    require(p.length >= 2)

    var i = p.length - 2

    while (i >= 0 && p(i) > p(i + 1))
      i -= 1

    if (i == -1)
      p
    else {

      var k = p.length - 1

      while (p(i) > p(k))
        k -= 1

      var temp = p(i)
      p(i) = p(k)
      p(k) = temp

      var j = i + 1
      var r = p.length - 1

      while (j < r) {
        temp = p(j)
        p(j) = p(r)
        p(r) = temp
        j += 1
        r -= 1
      }

      p
    }
  }

  def main(args: Array[String]) {
    val p = Array(1, 2, 3)

    UtilArray.printArray(nextPermutation(p))
    UtilArray.printArray(nextPermutation(p))
    UtilArray.printArray(nextPermutation(p))
    UtilArray.printArray(nextPermutation(p))
    UtilArray.printArray(nextPermutation(p))
    UtilArray.printArray(nextPermutation(p))
  }

}
package domain.counting

object UtilArray {
  def printArray(p: Array[Int]) {
    for (i <- 0 until p.length)
      print(p(i))

    println()
  }
}
package domain.numbertheory

import scala.collection.mutable.ListBuffer

object AssignMemoryLocation {

  def assign(ids: List[Int], k: Int): List[Int] = {
    require(ids.length <= k)

    val result = ListBuffer[Int]()

    val used = scala.collection.mutable.Set[Int]()

    for (i <- 0 until ids.length) {
      var location = ids(i) % k

      while (used.contains(location)) {
        location = (location + 1) % k
      }

      result += location
      used += location
    }

    result.toList
  }

  def main(args: Array[String]): Unit = {
    println(assign(List(121275, 178187, 114841879, 114841879, 36133, 29719, 8878217, 1236478, 987599, 5454), 10))
  }

}
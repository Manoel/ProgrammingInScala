package domain.algorithms

import scala.collection.mutable.ListBuffer

object TalksScheduler {

  def schedule(talks: List[Interval]): List[Interval] = {
    require(talks != null)
    require(talks.size > 1)

    val result = ListBuffer[Interval]()

    val talksSorted = talks.sorted
    
    result += talksSorted(0)

    for (i <- 1 until talksSorted.length) {
      if (!result(result.length - 1).intersect(talksSorted(i))) {
        result += talksSorted(i)
      }
    }

    result.toList
  }

  def main(args: Array[String]) {

    val talks = List(
        Interval(1, 10), 
        Interval(2, 7), 
        Interval(4, 9), 
        Interval(8, 10), 
        Interval(11, 15), 
        Interval(10, 16), 
        Interval(17, 18), 
        Interval(19, 20))

    println(schedule(talks))

  }

}
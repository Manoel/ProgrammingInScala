package domain.tree

import scala.collection.mutable.ListBuffer
import scala.collection.mutable

case class NimPosition(val piles: List[Int]) {

  require(piles != null)
  require(!piles.isEmpty)
  require(piles.forall { x => x > 0 })
  
  val stones = piles.reduce(_+_)
  
  def nextPositions():Set[NimPosition] = {
    if (stones == 1)
      Set[NimPosition]()
    else {
      val result = mutable.Set[NimPosition]()
      
      for (i <- 0 until piles.length) {
    	  var s = stones
    	  var tempList = ListBuffer() ++ piles
    	  var goNext = false
    	  while (s > 1 && !goNext) {
    	    s -= 1
    	    tempList(i) -= 1
    	    if (tempList(i) == 0) {
    	      tempList.remove(i)
    	      goNext = true
    	    }
    	    result += new NimPosition(tempList.toList)
    	  }
      }
      result.toSet
    }
  }
  
  override def equals(that: Any):Boolean = that match {
    case that:NimPosition => this.hashCode() == that.hashCode()
    case _ => false
  }
  
  override def hashCode: Int = {
    val pilesSorted = (ListBuffer() ++ piles).sortWith(_>_)
    val prime = 31
    var result = 1
    pilesSorted.foreach { x => result = prime * result + x }
    return result
  }
  
}

object NimPosition {
  def main(args: Array[String]) {
    val nimPosition = new NimPosition(List(2, 2))
    println(nimPosition.nextPositions())
  }
}
package domain.structures

import scala.collection.mutable

case class IntToIntFunction(val mapping: Map[Int, Int]) {
  
  require(mapping != null) 
  
  private val n = mapping.size
  
  private val range = 1 to n
  
  require(range.toIterable.forall(key => mapping.contains(key)))
  
  def isOnto: Boolean = range.toIterable.forall { value => mapping.exists(e => value == e._2) }
  
  def isOneToOne: Boolean = {
    var values = mutable.Map[Int, Int]()
    
    mapping.values.foreach { e => values += (e -> (values.getOrElse(e, 0) + 1)) }
    
    values.forall(e => e._2 == 1)
  }
  
  def isBijection: Boolean = isOneToOne && isOnto
  
  def inverse(): IntToIntFunction = {
    require(isBijection)
    new IntToIntFunction(mapping.map(e => (e._2, e._1)))
  }
  
}

object IntToIntFunction {
  
  def main(args: Array[String]) {
    val fn1 = new IntToIntFunction(Map(1->2, 2->1, 3->4, 4->3))
    println(fn1.isOnto)
    
    val fn2 = new IntToIntFunction(Map(1->2, 2->1, 3->3, 4->3))
    println(fn2.isOnto)
    
    val fn3 = new IntToIntFunction(Map(1->3, 2->4, 3->2, 4->1))
    println(fn3.inverse())

  }
  
}
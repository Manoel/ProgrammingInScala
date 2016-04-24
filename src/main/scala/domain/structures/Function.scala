package domain.structures

import scala.collection.mutable

class Function[E, F](val fn: Map[E, F]) {
  
  def isOneToOne: Boolean = {
    var values = mutable.Map[F, Int]()
    
    fn.values.foreach { e => values += (e -> (values.getOrElse(e, 0) + 1)) }
    
    values.forall(e => e._2 == 1)
  }
      
}

object Function {
  
  def main(args: Array[String]) {
    
    val fn1 = new Function[Char, Int](Map('a' -> 4, 'b' -> 5, 'c' -> 1, 'd' -> 3))
    
    println(fn1.isOneToOne)
    
    val fn2 = new Function[Char, Int](Map('a' -> 4, 'b' -> 5, 'c' -> 1, 'd' -> 4))
    
    println(fn2.isOneToOne)
    
  }
  
}
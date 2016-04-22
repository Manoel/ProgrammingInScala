package domain.list

object MergeSort {
  def sort[T](xs: List[T])(less: (T, T) => Boolean): List[T] = {
    val n = xs.length / 2
    
    if (n == 0)
      xs
    else {
      val split = xs.splitAt(n)
      merge(sort(split._1)(less), sort(split._2)(less), less)
    }
  }
  
  private def merge[T](xs: List[T], ys: List[T], less: (T, T) => Boolean): List[T] = 
    if (xs.isEmpty)
      ys
    else if (ys.isEmpty)
      xs
    else if (less(xs.head, ys.head))
      xs.head :: merge(xs.tail, ys, less)
    else 
      ys.head :: merge(xs, ys.tail, less)
   
  def main(args: Array[String]) = {
    val l = List(5, 4, 3, 2, 1)
    
    println(sort(l)(_ < _))
  }
}
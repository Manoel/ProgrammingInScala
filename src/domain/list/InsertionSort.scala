package domain.list

object InsertionSort {
  
  def sort(list: List[Int]) : List[Int] =
    if (list.isEmpty) Nil
    else insert(list.head, sort(list.tail))
  
  private def insert(h: Int, tail: List[Int]): List[Int] =
    if (tail.isEmpty || h <= tail.head) h::tail
    else tail.head :: insert(h, tail.tail)
}
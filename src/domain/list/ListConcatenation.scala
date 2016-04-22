package domain.list

object ListConcatenation {
  
  def append[T](xs: List[T], ys: List[T]): List[T] =
    if (xs.isEmpty) ys
    else if (ys.isEmpty) xs
    else xs.head :: append(xs.tail, ys )
}
package domain.queue

class Queue[+T] private (
    private[this] var leading: List[T],
    private[this] var trailing: List[T]) {

  private def mirror() =
    if (leading.isEmpty) {
      while (!trailing.isEmpty) {
        leading = trailing.head :: leading
        trailing = trailing.tail
      }
    }
      
  def head = {
    mirror()
    leading.head
  }

  def tail: Queue[T] = {
    mirror()
    new Queue(leading.tail, trailing)
  }

  def enqueue[U >: T](x: U) =
    new Queue[U](leading, x :: trailing)
    
}

object Queue {
  // constructs a queue with initial elements 'xs'
  def apply[T](xs: T*) = new Queue[T](xs.toList, Nil)

}
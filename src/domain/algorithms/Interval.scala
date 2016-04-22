package domain.algorithms

case class Interval(val start: Int, val end: Int) extends Ordered[Interval] {
  
  require(start <= end)
  
  override def compare(that: Interval): Int = {
    end.compare(that.end)
  }
  
  def intersect(that: Interval): Boolean = {
    var smallest = this
    var largest = that
    
    if (smallest.compare(largest) > 0) {
      smallest = that
      largest = this
    }
      
    largest.start <= smallest.end
  }
  
}
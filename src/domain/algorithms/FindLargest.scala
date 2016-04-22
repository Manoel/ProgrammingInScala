package domain.algorithms

object FindLargest {
  
  def findLargest(numbers: List[Int]) : Int = {
    require(numbers != null)
    require(numbers.size > 0)
    
    var largest = Int.MinValue
    
    numbers.foreach { n => if (n > largest) largest = n }
    
    largest
  }
  
  def main(args: Array[String]) {
    println(findLargest(List(5, 1, 4, 2, 3)))
    println(findLargest(List(3, 1, 4, 2, 5)))
    println(findLargest(List(4, 1, 5, 2, 3)))
  }
  
}
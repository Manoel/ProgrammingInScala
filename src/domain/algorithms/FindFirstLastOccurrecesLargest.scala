package domain.algorithms

object FindFirstLastOccurrecesLargest {
  
  def find(numbers: List[Int]): (Int, Int) = {
    require(numbers != null)
    require(numbers.size > 0)
    
    var largest = Int.MinValue
    
    var first = -1
    var last = -1
    var foundFirst = false
    
    for (i <- 0 until numbers.length) {
      
      if (numbers(i) >= largest) {
        last = i
        if (!foundFirst || numbers(i) > largest) {
          first = i
          foundFirst = true
        }
        largest = numbers(i)
      }
      
    }
    
    (first, last)
  }
  
  def main(args: Array[String]) {
    println(find(List(Int.MinValue)))
    println(find(List(Int.MinValue, Int.MinValue, Int.MinValue)))
    println(find(List(1, 2, 3)))
    println(find(List(3, 2, 3)))
    println(find(List(2, 1, 2, 3, 0, 3, 1)))
  }
  
}
package domain.algorithms

object LinearSearch {
  
  def search(numbers: List[Int], n: Int): Int = {
    require(numbers != null)
    require(numbers.size > 0)
        
    var index = -1
    var i = 0
    
    while (index == -1 && i < numbers.size) {
      if (numbers(i) == n)
        index = i
      i += 1
    }
    
    index
  }
  
  def main(args: Array[String]) {
    println(search(List(1, 2, 4), 0))
    println(search(List(1, 2, 4), 1))
    println(search(List(1, 2, 4), 2))
    println(search(List(1, 2, 4), 4))
  }
  
}
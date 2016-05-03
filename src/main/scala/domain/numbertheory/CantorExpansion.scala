package domain.numbertheory

object CantorExpansion {
  
  def cantorExpansion(N: Int): Array[Int] = {
    require(N > 0)
    
    var n = N
    var fat = 1
    var i = 1
    
    while (fat <= n) {
      i += 1
      fat *= i
    }
    
    fat /= i
    i -= 1
    
    val result = Array.ofDim[Int](i)
    
    var acum = 0
    
    while (acum < N) {
      var j = 1
      var r = j * fat
      
      while (j < i && r < n) {
        j += 1
        r = j * fat
      }
      
      if (r > n) {
        j -= 1
        r -= fat
      }
      
      result(i - 1) = j
      acum += r
      fat /= i
      i -= 1
      n -= r
    }
    
    result
  }
  
  def main(args: Array[String]) {
    println(cantorExpansion(1).deep.mkString(" "))
    println(cantorExpansion(2).deep.mkString(" "))
    println(cantorExpansion(3).deep.mkString(" "))
    println(cantorExpansion(4).deep.mkString(" "))
    println(cantorExpansion(5).deep.mkString(" "))
    println(cantorExpansion(6).deep.mkString(" "))
    println(cantorExpansion(7).deep.mkString(" "))
    println(cantorExpansion(8).deep.mkString(" "))
    println(cantorExpansion(9).deep.mkString(" "))
    println(cantorExpansion(10).deep.mkString(" "))
  }
  
}
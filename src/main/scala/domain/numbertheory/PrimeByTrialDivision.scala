package domain.numbertheory

object PrimeByTrialDivision {
  
  def isPrime(i: Int) : Boolean = {
    require(i > 0)
    
    var prime = true
    var d = 2

    while (prime && d <= i - 1) {
      if (i % d == 0)
        prime = false
      d += 1
    }
    
    prime
  }
  
  def main(args: Array[String]) {
    println(isPrime(1))
    println(isPrime(2))
    println(isPrime(3))
    println(isPrime(4))
    println(isPrime(5))
    println(isPrime(6))
    println(isPrime(7))
    println(isPrime(8))
    println(isPrime(9))
    println(isPrime(10))
    println(isPrime(11))
  }
  
}
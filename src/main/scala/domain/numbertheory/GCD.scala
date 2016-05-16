package domain.numbertheory

object GCD {

  def gcd(a: Int, b: Int): Int = {
    require(a > 0 && b > 0)
    gcdHelper(a, b)
  }

  private def gcdHelper(a: Int, b: Int): Int = {
    if (a == 0)
      b
    else if (b == 0)
      a
    else if (a > b)
      gcdHelper(b, a % b)
    else
      gcdHelper(a, b % a)
  }
  
  def main(args: Array[String]) {
    println(gcd(8, 4))
    println(gcd(4, 10))
  }

}
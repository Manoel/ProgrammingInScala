package domain.numbertheory

object LCM {

  def lcm(a: Int, b: Int): Int = {
    require(a > 0 && b > 0)
    a * b / GCD.gcd(a, b)
  }
  
  def main(args: Array[String]) {
    println(lcm(8, 4))
    println(lcm(4, 10))
  }

}
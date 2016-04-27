package domain.numbertheory

object ModularExponentiation {
  
  def modExp(b: Int, n: Int, m: Int): Int = {
    
    val n_2 = BaseExpansion.baseExpansion(n, 2)
    
    var b_to_n = 1
    
    var acum = BaseExpansion.modulus(b, m)._2
    
    for (i <- 0 until n_2.length) {
      
      if (n_2(i) == 1) {
        b_to_n *= acum  
        b_to_n = BaseExpansion.modulus(b_to_n, m)._2
      }
      
      acum *= acum
      acum = BaseExpansion.modulus(acum, m)._2
    }
    
    b_to_n
  }
  
  
  def main(args: Array[String]) {
    println(modExp(3, 644, 645))
  }
}
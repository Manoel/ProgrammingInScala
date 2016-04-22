package domain.logic

case class TruthValue(val value: Double) {
  
  require(value >= 0 && value <= 1)
  
  def and(that: TruthValue): TruthValue = new TruthValue(scala.math.min(value, that.value))
  
  def or(that: TruthValue): TruthValue = new TruthValue(scala.math.max(value, that.value))
  
}

object TruthValue {
  
  def main(args: Array[String]) {
    val TRUE = TruthValue(1)
    val FALSE = TruthValue(0)
    
    println("true and false = " + TRUE.and(FALSE))
    println("true and true = " + TRUE.and(TRUE))
    println("false and true = " + FALSE.and(TRUE))
    println("false and false = " + FALSE.and(FALSE))
    println("true or false = " + TRUE.or(FALSE))
    println("true or true = " + TRUE.or(TRUE))
    println("false or true = " + FALSE.or(TRUE))
    println("false or false = " + FALSE.or(FALSE))
  }
  
}
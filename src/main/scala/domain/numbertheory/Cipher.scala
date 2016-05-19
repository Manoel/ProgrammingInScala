package domain.numbertheory

class Cipher {
  
  private val chars = scala.collection.mutable.Map[Int, Char]()
    
  (0 to 25).asInstanceOf[Range.Inclusive].foreach { x => chars += (x -> ('A' + x).asInstanceOf[Char]) }
  
  val charsMap = chars.toMap
  
}
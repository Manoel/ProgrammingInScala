package domain.numbertheory

object EncryptDecryptMessage {

  private def executeOp(message: String, k: Int, op:(Int, Int) => Int): String = {
    require(message != null)
    require(!message.isEmpty())
    require(k < 26)

    val M = message.trim().toUpperCase();

    val chars = scala.collection.mutable.Map[Int, Char]()
    
    (0 to 25).asInstanceOf[Range.Inclusive].foreach { x => chars += (x -> ('A' + x).asInstanceOf[Char]) }

    val s = M.toCharArray().map { x => (( op(chars(x - 'A'), k) ) % 26).asInstanceOf[Int] }
    println(s.deep.mkString(" "))
    new String("A")
  }
  
  def encrypt(message: String, k: Int): String = {
    executeOp(message, k, _+_)
  }
  
  def decrypt(message: String, k: Int): String = {
    executeOp(message, k, _-_)
  }

  def main(args: Array[String]): Unit = {
    val message = "AMOR"
    val encrypted = encrypt(message, 3)
    val decrypted = decrypt(encrypted, 3)
    
    println(encrypted)
    println(decrypted)
  }

}
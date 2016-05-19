package domain.numbertheory

class EncryptDecryptMessage extends Cipher {

  private def executeOp(message: String, k: Int, op:(Int, Int) => Int): String = {
    require(message != null)
    require(!message.isEmpty())
    require(k < 26)

    val M = message.trim().toUpperCase()

    val s = M.toCharArray().map { x => (charsMap( op(charsMap(x - 'A'), k) % 26 ) ) }

    new String(s)
  }
  
  def encrypt(message: String, k: Int): String = {
    executeOp(message, k, _+_)
  }
  
  def decrypt(message: String, k: Int): String = {
    executeOp(message, k, _-_)
  }

}

object EncryptDecryptMessage {
  
  def main(args: Array[String]): Unit = {
    val encDec = new EncryptDecryptMessage
    
    val message = "AMOR"
    val encrypted = encDec.encrypt(message, 3)
    val decrypted = encDec.decrypt(encrypted, 3)
    
    println(encrypted)
    println(decrypted)
  }
  
}
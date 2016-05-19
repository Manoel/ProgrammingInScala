package domain.numbertheory

object AffineCipher extends Cipher {

  private def fn(a: Int, b: Int)(p: Int): Int = {
    (a * p + b) % 26
  }

  private def inverseFn(a: Int, b: Int)(c: Int): Int = {
    val ap = c - b
    val i = InverseModulo.inverse(a, 26)
    i * ap % 26
  }

  private def executeOp(message: String, a: Int, b: Int, op: Int => Int): String = {
    require(a < 26)
    require(b < 26)
    require(message != null)
    require(!message.isEmpty())

    val M = message.trim().toUpperCase()

    val s = M.toCharArray().map { x => (charsMap(op(x - 'A'))) }

    new String(s)
  }

  def encrypt(message: String, a: Int, b: Int): String = {
    executeOp(message, a, b, fn(a, b)_)
  }

  def decrypt(message: String, a: Int, b: Int): String = {
    executeOp(message, a, b, inverseFn(a, b)_)
  }

  def main(args: Array[String]): Unit = {
    val message = "AMOR"
    val encrypted = encrypt(message, 7, 3)
    val decrypted = decrypt(encrypted, 7, 3)
    println(encrypted)
    println(decrypted)
  }

}
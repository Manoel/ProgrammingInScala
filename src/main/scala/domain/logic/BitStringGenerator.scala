package domain.logic

class BitStringGenerator(val n: Int) {

  private var current = ""

  def first(): String = {
    var result = ""

    for (i <- 0 until n) result += "0"

    result
  }

  def hasNext: Boolean = current == "" || !allOnes

  def next(): String = {
    
    if (!hasNext) throw new IllegalArgumentException("There is not next element available.")
    
    if (current == "") {
      current = first()
    } else {
      var i = n - 1
      val array = current.toCharArray()
      while (array(i) == '1') {
        array(i) = '0'
        i -= 1
      }
      array(i) = '1'

      current = new String(array)

    }
    current
  }

  private def allOnes: Boolean = current.forall(c => c == '1')

}
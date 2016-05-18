package domain.numbertheory

object ISBN10CheckDigitCalculator {

  def checkDigit(digits: Array[Int]): Int = {
    require(digits != null)
    require(digits.length == 9)

    var sum = 0;

    for (i <- 0 to 8) {
      sum += (i + 1) * digits(i)
    }

    sum % 11
  }

  def main(args: Array[String]): Unit = {
    println(checkDigit(Array(0, 0, 7, 2, 8, 8, 0, 0, 8)))
  }

}
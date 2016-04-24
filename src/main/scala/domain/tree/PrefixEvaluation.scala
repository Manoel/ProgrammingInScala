package domain.tree

import scala.collection.mutable.Stack
import scala.collection.mutable.Map

object PrefixEvaluation {

  def evaluate(expr: String):Int = {
    require(expr != null)
    require(!expr.isEmpty())

    val op = Map[Char, (Int, Int) => Int]()

    op += ('+' -> (_ + _))
    op += ('-' -> (_ - _))
    op += ('*' -> (_ * _))
    op += ('/' -> (_ / _))
    op += ('^' -> ((x, y) => scala.math.pow(x, y).toInt))

    val operands = Stack[Int]()

    for (i <- expr.length() - 1 to 0 by -1) {     
      val c = expr.charAt(i)
      if (op.contains(c)) {
        val operand1 = operands.pop()
        val operand2 = operands.pop()
        operands.push(op.get(c).get(operand1, operand2))
      } else {
        operands.push(c.asDigit)
      }
    }

    operands.pop()
  }
  
  def main(args: Array[String]) {
    val expr = "+-*235/^234"
    println(evaluate(expr))
  }

}
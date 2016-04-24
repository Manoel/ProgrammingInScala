package domain.tree

import scala.collection.mutable.Stack
import scala.collection.mutable.Map

object PostfixEvaluation {
  
  def evaluate(expr: String): Int = {
    require(expr != null)
    require(!expr.isEmpty())

    val op = Map[Char, (Int, Int) => Int]()

    op += ('+' -> (_ + _))
    op += ('-' -> (_ - _))
    op += ('*' -> (_ * _))
    op += ('/' -> (_ / _))
    op += ('^' -> ((x, y) => scala.math.pow(x, y).toInt))

    val operands = Stack[Int]()

    for (c <- expr) {     
      if (op.contains(c)) {
        val operand1 = operands.pop()
        val operand2 = operands.pop()
        operands.push(op.get(c).get(operand2, operand1))
      } else {
        operands.push(c.asDigit)
      }
    }

    operands.pop()
  }
  
  def main(args: Array[String]) {
    val expr = "723*-4^93/+"
    println(evaluate(expr))
  }
  
}
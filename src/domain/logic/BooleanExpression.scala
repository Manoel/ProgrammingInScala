package domain.logic

import scala.collection.mutable

abstract sealed class BooleanExpression(val op: String) {
  def accept(visitor: Visitor): Any
}

abstract class BinaryBooleanExpression(op: String, val left: BooleanExpression, val right: BooleanExpression) extends BooleanExpression(op)

abstract class UnaryBooleanExpression(op: String, val expr: BooleanExpression) extends BooleanExpression(op)

class AndExpression(left: BooleanExpression, right: BooleanExpression) extends BinaryBooleanExpression("and", left, right) {
  def accept(visitor: Visitor): Any = visitor.visit(this)
}

class OrExpression(left: BooleanExpression, right: BooleanExpression) extends BinaryBooleanExpression("or", left, right) {
  def accept(visitor: Visitor): Any = visitor.visit(this)
}

class XorExpression(left: BooleanExpression, right: BooleanExpression) extends BinaryBooleanExpression("xor", left, right) {
  def accept(visitor: Visitor): Any = visitor.visit(this)
}

class ConditionalExpression(left: BooleanExpression, right: BooleanExpression) extends BinaryBooleanExpression("if", left, right) {
  def accept(visitor: Visitor): Any = visitor.visit(this)
}

class BiconditionalExpression(left: BooleanExpression, right: BooleanExpression) extends BinaryBooleanExpression("iff", left, right) {
  def accept(visitor: Visitor): Any = visitor.visit(this)
}

class NotExpression(expr: BooleanExpression) extends UnaryBooleanExpression("not", expr) {
  def accept(visitor: Visitor): Any = visitor.visit(this)
}

class VariableExpression(val name: String) extends BooleanExpression("") {
  def accept(visitor: Visitor): Any = visitor.visit(this)
}

object BooleanExpression {

  def envs(variables: Set[String]): List[Map[String, Boolean]] = {
    val bitStrGen = new BitStringGenerator(variables.size)

    val result = mutable.ListBuffer[Map[String, Boolean]]()

    val varList = variables.toList

    while (bitStrGen.hasNext) {
      val bitStr = bitStrGen.next()

      val env = mutable.Map[String, Boolean]()

      for (i <- 0 until varList.length) {
        env += (varList(i) -> (if (bitStr.charAt(i) == '1') true else false))
      }

      result += env.toMap

    }

    result.toList

  }

  def isSatisfiable(expr: BooleanExpression): (Boolean, Map[String, Boolean]) = {
    val visitor = new ExtractVariablesVisitor

    expr.accept(visitor)

    val envsList = envs(visitor.getEnv)

    var result = false

    var i = 0

    var assignment: Map[String, Boolean] = null

    while (!result && i < envsList.length) {
      val evaluteExprVisitor = new EvaluateBooleanExpressionVisitor(envsList(i))

      result = expr.accept(evaluteExprVisitor).asInstanceOf[Boolean]
      
      if (result)
        assignment = envsList(i)

      i += 1
    }

    (result, assignment)
  }

  def main(args: Array[String]) {
    var expr:BooleanExpression = new AndExpression(new VariableExpression("p"), new VariableExpression("q"))

    println(isSatisfiable(expr))
    
    expr = new OrExpression(new VariableExpression("p"), new VariableExpression("q"))

    println(isSatisfiable(expr))
    
    expr = new AndExpression(new VariableExpression("p"), new NotExpression(new VariableExpression("p")))

    println(isSatisfiable(expr))
    
    val A = Array[Int](1)
    
    val s = (List() ++ A).slice(1, A.length).reduce((_+_))
    
    println(s)
  }
}

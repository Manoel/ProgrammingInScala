package domain.logic

import scala.collection.mutable

class ExtractVariablesVisitor extends Visitor {

  private val env = mutable.Set[String]()

  def getEnv = env.toSet

  private def binaryExpressionVisit(exp: BinaryBooleanExpression): Any = {
    val envLeft = exp.left.accept(this).asInstanceOf[mutable.Set[String]]
    val envRight = exp.right.accept(this).asInstanceOf[mutable.Set[String]]
    env ++= envLeft
    env ++= envRight
    env
  }

  def visit(exp: AndExpression): Any = binaryExpressionVisit(exp)

  def visit(exp: OrExpression): Any = binaryExpressionVisit(exp)

  def visit(exp: XorExpression): Any = binaryExpressionVisit(exp)

  def visit(exp: ConditionalExpression): Any = binaryExpressionVisit(exp)

  def visit(exp: BiconditionalExpression): Any = binaryExpressionVisit(exp)

  def visit(exp: NotExpression): Any = {
    val envExpr = exp.expr.accept(this).asInstanceOf[mutable.Set[String]]
    env ++= envExpr
    env
  }

  def visit(exp: VariableExpression): Any = {
    env += exp.name
    env
  }
}
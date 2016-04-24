package domain.logic

class EvaluateBooleanExpressionVisitor(val env: Map[String, Boolean]) extends Visitor {

  def visit(expr: AndExpression): Any = {
    val leftValue = expr.left.accept(this).asInstanceOf[Boolean]
    val rightValue = expr.right.accept(this).asInstanceOf[Boolean]
    TruthValues.and(leftValue, rightValue)
  }

  def visit(expr: OrExpression): Any = {
    val leftValue = expr.left.accept(this).asInstanceOf[Boolean]
    val rightValue = expr.right.accept(this).asInstanceOf[Boolean]
    TruthValues.or(leftValue, rightValue)
  }

  def visit(expr: XorExpression): Any = {
    val leftValue = expr.left.accept(this).asInstanceOf[Boolean]
    val rightValue = expr.right.accept(this).asInstanceOf[Boolean]
    TruthValues.exOr(leftValue, rightValue)
  }

  def visit(expr: ConditionalExpression): Any = {
    val leftValue = expr.left.accept(this).asInstanceOf[Boolean]
    val rightValue = expr.right.accept(this).asInstanceOf[Boolean]
    TruthValues.conditional(leftValue, rightValue)
  }

  def visit(expr: BiconditionalExpression): Any = {
    val leftValue = expr.left.accept(this).asInstanceOf[Boolean]
    val rightValue = expr.right.accept(this).asInstanceOf[Boolean]
    TruthValues.biconditional(leftValue, rightValue)
  }

  def visit(expr: NotExpression): Any = {
    val exprValue = expr.expr.accept(this).asInstanceOf[Boolean]
    TruthValues.not(exprValue)
  }

  def visit(expr: VariableExpression): Any = env(expr.name)

}
package domain.logic

trait Visitor {

  def visit(expr: AndExpression): Any
  def visit(expr: OrExpression): Any
  def visit(expr: XorExpression): Any
  def visit(expr: ConditionalExpression): Any
  def visit(expr: BiconditionalExpression): Any
  def visit(expr: NotExpression): Any
  def visit(expr: VariableExpression): Any

}
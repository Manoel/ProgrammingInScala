package domain.expression

class Token(val value: Char) {

}

object Token {

  def token(value: Char) = new Token(value)

  def tokens = Map(
    '+' -> token('+'),
    '-' -> token('-'),
    '*' -> token('*'),
    '/' -> token('/'),
    '(' -> token('('),
    ')' -> token(')'),
    '^' -> token('^'))

  def tokensPosition(expr: String) =
    for (i <- 0 to expr.length - 1) (i, expr.charAt(i))

}
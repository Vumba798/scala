package Expr

  /*
trait Expr:
  def eval: Int = this match
    case Number(n) => n
    case Sum(e1, e2) => e1.eval + e2.eval
    case Prod(e1, e2) => e1.eval * e2.eval
    case Var(x) => throw Error(s"evals Var $x")

  def show: String = this match
    case Number(n) => n.toString
    case Sum(e1, e2) => s"${e1.show} + ${e2.show}"
    case Prod(e1, e2) =>
      def makeParentheses(e: Expr) = e match
        case x: Sum => s"(${x.show})"
        case x => x.show
      s"${makeParentheses(e1)} * ${makeParentheses(e2)}"



case class Number(n: Int) extends Expr
case class Sum(e1: Expr, e2: Expr) extends Expr
case class Prod(e1: Expr, e2: Expr) extends Expr
case class Var(x: String) extends Expr


   */
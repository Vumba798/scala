package redBookExercises.Chapter3

sealed trait List[+A]

case object Nil extends List[Nothing]
case class Cons[+A](head: A, tail: List[A]) extends List[A]

object List {
  def sum(ints: List[Int]): Int = ints match {
    case Cons(head: Int, tail: List[Int]) => head + sum(tail)
    case Nil => 0
  }

  def product(ds: List[Double]): Double = ds match {
    case Nil => 1.0
    case Cons(0.0, _) => 0
    case Cons(x, xs) => x * product(xs)
  }

  def apply[A](as: A*): List[A] =
    if as.isEmpty then Nil
    else Cons(as.head, apply(as.tail: _*))




  val example = Cons(1, Cons(2, Cons(3, Nil)))
  val example2 = List(1, 2, 3)
  val total = sum(example)
}

// exr 2
def tail[A](xs: List[A]): List[A] = xs match {
  case Nil => Nil
  case Cons(_, as) => as
}

// exr 3
def drop[A](n: Int, xs: List[A]): List[A] =
  if n == 0 then xs
  else drop(n - 1, tail(xs))

// exr 4
def dropWhile[A](f: A => Boolean)(xs: List[A]): List[A] = xs match {
  case Nil => Nil
  case Cons(a, as) if f(a) => dropWhile(f)(as)
  case _ => xs
}

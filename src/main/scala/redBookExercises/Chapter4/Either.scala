package redBookExercises.Chapter4

trait Either[+E, +A] {
  // exr 7
  def map[B](f: A => B): Either[E, B] = this match {
    case Left(x) => Left(x)
    case Right(x) => Right(f(x))
  }
  def flatmap[EE >: E, B](f: A => Either[EE, B]): Either[EE, B] = this match {
    case Left(e) => Left(e)
    case Right(x) => f(x)
  }
  def orElse[EE >: E, B >: A](b: => Either[EE, B]): Either[EE, B] = this match {
    case Left(e) => b
    case Right(x) => Right(x)
  }
  def map2[EE >: E, B, C](b: Either[EE, B])(f: (A, B) => C): Either[EE, C] = (this, b) match {
    case (Left(e), _) => Left(e)
    case (_, Left(e)) => Left(e)
    case (Right(aa), Right(bb)) => Right(f(aa, bb))
  }
}
case class Left[+E](value: E) extends Either[E, Nothing]
case class Right[+A](value: A) extends Either[Nothing, A]

object Either {
  def mean(xs: IndexedSeq[Double]): Either[String, Double] =
    if (xs.isEmpty)
      Left("mean of empty list")
    else
      Right(xs.sum / xs.length)

  def safeDiv(x: Double, y: Double): Either[Exception, Double] = {
    try {
      Right(x / y)
    } catch {
      case e: Exception => Left(e)
    }
    // exr 8
    /*
    def sequence[E, A](l: List[Either[E, A]]): Either[E, List[A]] = l match {
      case Left(e) => Left(e)
      case Right()
    }
      l.collect{ case Right(a) => a}
     */

  }

}

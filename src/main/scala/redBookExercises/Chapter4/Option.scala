package redBookExercises.Chapter4
import java.util.regex._

trait Option[+A] {
  // exr 1
  def map[B](f: A => B): Option[B] = this match {
    case Some(x) => Some(f(x))
    case None => None
  }

  def getOrElse[B >: A](default: => B): B = this match {
    case Some(x) => x
    case None => default
  }

  def flatMap[B](f: A => Option[B]): Option[B] = map(f).getOrElse(None)

  def orElse[B >: A](ob: => Option[B]): Option[B] = this match {
    case None => ob
    case _ => this
  }
  def filter(f: A => Boolean): Option[A] = this match {
    case Some(x) if f(x) => Some(x)
    case _ => None
  }
}
case class Some[+A](get: A) extends Option[A]
case object None extends Option[Nothing]

object Option {
  def mean(xs: Seq[Double]): Option[Double] =
    if (xs.isEmpty) None
    else Some(xs.sum / xs.length)

  // exr 2
  def variance(xs: Seq[Double]): Option[Double] =
    mean(xs).flatMap(m => mean(xs.map(x => math.pow(x - m, 2))))


}

object MyMatch {
  def pattern(s: String): Option[Pattern] =
    try {
      Some(Pattern.compile(s))
    } catch {
      case PatternSyntaxException => None
    }

  def mkMatcher(pat: String): Option[String => Boolean] =
    pattern(pat) map (p => (s: String) => p.matcher(s).matches())

  def bothMatch(pat1: String, pat2: String, s: String): Option[Boolean] =
    for {
      f <- mkMatcher(pat1)
      g <- mkMatcher(pat2)
    } yield f(s) && g(s)

  def bothMatch_1(pat1: String, pat2: String, s: String): Option[Boolean] =
    mkMatcher(pat1) flatMap (f =>
      mkMatcher(pat2) map (g =>
        f(s) && g(s)
      )
    )

  // exr 3
  def map2[A,B,C](a: Option[A], b: Option[B])(f: (A, B) => C): Option[C] =
    a.flatMap(aa => b.map(bb => f(aa, bb)))

  // exr 4
  def bothMatch_2(pat1: String, pat2: String, s: String): Option[Boolean] = {
    val f = mkMatcher(pat1)
    val g = mkMatcher(pat2)
    map2(f, g)((ff, gg) => ff(s) && gg(s))
  }

  // exr 5
  def sequence[A](a: List[Option[A]]): Option[List[A]] =
    a collect { case Some(x) => x } match {
      case Nil => None
      case l => Some(l)
    }

  def parsePatterns(a: List[String]): Option[List[Pattern]] =
    sequence(a map pattern)

  // exr 6
  def traverse[A, B](a: List[A])(f: A => Option[B]): Option[List[B]] = {
    val l = a collect {f(_) match {
      case Some(x) => x
    }}
    l match {
      case Nil => None
      case ll => Some(ll)
    }
  }


}
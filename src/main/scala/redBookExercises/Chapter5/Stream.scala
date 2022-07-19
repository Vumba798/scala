package redBookExercises.Chapter5

import redBookExercises.Chapter5.Stream.{cons, empty}

import scala.annotation.tailrec
import scala.collection.mutable.ListBuffer

trait Stream[+A] {
  def uncons: Option[(A, Stream[A])]
  def isEmpty: Boolean = uncons.isEmpty

  // exr 1
  def toList_1: List[A] = uncons match {
    case Some((h, t)) => h :: t.toList_1
    case None => List()
  }

  def toList_2: List[A] = {
    @tailrec
    def go(s: Stream[A], acc: List[A]): List[A] = s.uncons match {
      case Some((h, t)) => go(t, h :: acc)
      case None => acc
    }

    go(this, List())
  }

  // exr 2
  def take(n: Int): Stream[A] = uncons match {
    case Some((h, t)) if n > 1 => Stream.cons(h, t.take(n - 1))
    case Some((h, _)) if n == 1 => Stream.cons(h, Stream.empty)
    case _ => Stream.empty
  }

  // exr 3
  def takeWhile(p: A => Boolean): Stream[A] = uncons match {
    case Some((h, t)) if p(h) => Stream.cons(h, t.takeWhile(p))
    case _ => Stream.empty
  }

  def foldRight[B](z: => B)(f: (A, => B) => B): B = uncons match {
    case Some((h, t)) => f(h, t.foldRight(z)(f))
    case None => z
  }

  def exists(p: A => Boolean): Boolean =
    foldRight(false)((a, b) => p(a) || b)

  // exr 4
  def forAll(p: A => Boolean): Boolean =
    foldRight(true)((a, b) => p(a) && b)

  // exr 5
  def takeWhile_2(p: A => Boolean): Stream[A] =
    foldRight(Stream.empty[A]) { (h, t) =>
      if (p(h)) Stream.cons(h, t)
      else empty
    }

  // exr 6
  def map[B](f: A => B): Stream[B] =
    foldRight(empty[B]) ((h, t) => cons(f(h), t))

  def filter(p: A => Boolean): Stream[A] =
    foldRight(empty[A]) { (h, t) =>
      if (p(h)) cons(h, t)
      else t
    }

  def append[B>:A](s: => Stream[B]): Stream[B] =
    foldRight(s)((h, t) => cons(h, t))

  def flatMap[B>:A](f: A => Stream[B]): Stream[B] =
    foldRight(empty[B])((h, t) => f(h) append t)


  // exr 12
  def map_2[B](f: A => B): Stream[B] = Stream.unfold(uncons) {
    case Some((h, t)) => Some((f(h), t.uncons))
    case None => None
  }

  def take_2(n: Int): Stream[A] = Stream.unfold(this.uncons, n) {
    case (_, n) if n <= 0 => None
    case (None, _) => None
    case (Some((h, t)), n) => Some((h, (t.uncons, n - 1)))
  }

  def takeWhile_3(p: A => Boolean): Stream[A] = Stream.unfold(this.uncons) {
    case Some((h, t)) if p(h) => Some((h, t.uncons))
    case _ => None
  }

  def zipWith[B, C](oth: Stream[B])(f: (A, B) => C): Stream[C] = Stream.unfold((this.uncons, oth.uncons)) {
    case (Some((h1, t1)), Some((h2, t2))) => Some(f(h1, h2), (t1.uncons, t2.uncons))
    case _ => None
  }

  def zip[B](oth: Stream[B]): Stream[(A, B)] = zipWith(oth)((_,_))

  def zipAll[B](oth: Stream[B]): Stream[(Option[A], Option[B])] = Stream.unfold((this.uncons, oth.uncons)) {
    case (Some((h1, t1)), Some((h2, t2))) => Some((Some(h1), Some(h2)), (t1.uncons, t2.uncons))
    case (Some((h1, t1)), None) => Some((Some(h1), None), (t1.uncons, None))
    case (None, Some((h2, t2))) => Some((None, Some(h2)), (None, t2.uncons))
    case _ => None
  }

  // exr 14
  def tails: Stream[Stream[A]] = Stream.unfold(uncons) {
    case Some((h, t)) => Some(cons(h, t), t.uncons)
    case _ => None
  }


}


object Stream {
  def empty[A]: Stream[A] =
    new Stream[A] { def uncons: Option[(A, Stream[A])] = None }

  def cons[A](hd: => A, tl: => Stream[A]): Stream[A] =
    new Stream[A] {
      lazy val uncons: Option[(A, Stream[A])] = Some((hd, tl))
    }

  def apply[A](as: A*): Stream[A] =
    if (as.isEmpty) empty
    else cons(as.head, apply(as.tail: _*))

  // exr 7
  def constant[A](x: A): Stream[A] = cons(x, constant(x))

  // exr 8
  def from(n: Int): Stream[Int] = cons(n, from(n + 1))

  // exr 9
  def fibs: Stream[Int] = {
    def go(f0: Int, f1: Int): Stream[Int] =
      cons(f0, go(f1, f0 + f1))

    go(0, 1)

  }

  // exr 10
  def unfold[A, S](z: S)(f: S => Option[(A, S)]): Stream[A] = f(z) match {
    case Some((a, newZ)) => cons(a, unfold(newZ)(f))
    case None => empty
  }

  // exr 11
  def fibs_1: Stream[Int] = unfold((0, 1)) { p =>
      Some(p._1, (p._2, p._1 + p._2))
  }

  def from_1(n: Int): Stream[Int] = unfold(n)(n => Some((n, n + 1)))

  def constant_1[A](a: A): Stream[A] = unfold(a)(a => Some((a, a)))

  def ones_1: Stream[Int] = unfold(1)(x => Some(x, x))

  // exr 13
  @tailrec
  def startsWith[A](s: Stream[A], s2: Stream[A]): Boolean = (s.uncons, s2.uncons) match {
    case (Some((h, t)), Some((h2, t2))) if h == h2 => startsWith(t, t2)
    case (Some((h, t)), Some((h2, t2))) if h != h2 => false
    case (Some(_), None) => true
    case (None, Some(_)) => false
  }




}

package redBookExercises.Chapter3

import scala.annotation.tailrec

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

// exr 5
def setHead[A](l: List[A], newHead: A) = Cons(newHead, tail(l))

def append[A](a1: List[A], a2: List[A]): List[A] = a1 match {
  case Nil => a2
  case Cons(h, t) => Cons(h, append(t, a2))
}

// exr 6
def init[A](l: List[A]): List[A] = l match {
  case Nil => Nil
  case Cons(h, Nil) => Nil
  case Cons(h, t) => Cons(h, init(t))
}

def foldRight[A, B](l: List[A], z: B)(f: (A, B) => B): B = l match {
  case Nil => z
  case Cons(x, xs) => f(x, foldRight(xs, z)(f))
}

def sum2(l: List[Int]) = foldRight(l, 0)(_ + _)
def prod2(l: List[Double]) = foldRight(l, 1.0)(_ * _)

// exr 8
val exr8 = foldRight(List(1, 2, 3), Nil: List[Int])(Cons(_, _)) // returns List(1, 2, 3)

// exr 9
def length[A](l: List[A]): Int = foldRight(l, 0)((_, lenAcc: Int) => lenAcc + 1)

// exr 10
@tailrec
def foldLeft[A, B](l: List[A], z: B)(f: (B, A) => B): B = l match {
  case Cons(h, t) => foldLeft(t, f(z, h))(f)
  case Nil => z
}

// exr 11
def sum3(l: List[Int]): Int = foldLeft(l, 0)(_ + _)
def product3(l: List[Double]): Double = foldLeft(l, 1.0)(_ * _)
def length3[A](l: List[A]): Int = foldLeft(l, 0)((z, _) => z + 1)

// exr 12
def reverse[A](l: List[A]): List[A] = l match {
  case Cons(x, xs) => foldLeft(xs, Cons(x, Nil))((z, x) => Cons(x, z))
  case Nil => Nil
}

// exr 13
def foldLeft1[A, B](l: List[A], z: B)(f: (A, B) => B): B = foldRight(reverse(l), z)(f)
def foldRight1[A, B](l: List[A], z: B)(f: (B, A) => B) : B = foldLeft(reverse(l), z)(f)

// exr 14
def append1[A](a1: List[A], a2: List[A]): List[A] =
  reverse(foldLeft(a2, reverse(a1))((l, elem) => Cons(elem, l)))

// exr 15
def flat[A](l: List[List[A]]): List[A] =
  @tailrec
  def go[A](l: List[List[A]], acc: List[A]): List[A] = l match {
    case Nil => acc
    case Cons(h, t) =>
      go(t, foldLeft(h, acc)((z, y) => Cons(y, z)))
  }

  l match {
    case Nil => Nil
    case Cons(x, Nil) => x
    case Cons(x, xs) => reverse(go(xs, reverse(x)))
  }

// exr 16
def addOne(l: List[Int]): List[Int] = foldRight(l, Nil)((x: Int, z: List[Int]) => Cons(x + 1, z))

// exr 17
def stringinator(l: List[Double]): List[String] = foldRight(l, Nil)((x: Double, z: List[String]) => Cons(x.toString, z))

// exr 18
def map[A, B](l: List[A])(f: A => B): List[B] = foldRight(l, Nil)((x: A, z: List[B]) => Cons(f(x), z))

// exr 19
def filter[A](l: List[A])(f: A => Boolean): List[A] =
  @tailrec
  def go(d: List[A], acc: List[A]): List[A] = d match {
    case Nil => acc
    case Cons(x, xs) if f(x) => go(xs, Cons(x, acc))
    case Cons(x, xs) if !f(x) => go(xs, acc)
  }

  reverse(go(l, Nil))

// exr 20
def flatMap[A, B](l: List[A])(f: A => List[B]): List[B] =
  @tailrec
  def go(d: List[A], acc: List[B]): List[B] = d match {
    case Nil => acc
    case Cons(h, t) => go(t, foldLeft(f(h), acc)((z, h) => Cons(h, z)))
  }

  reverse(go(l, Nil))

// exr 21
def map1[A](l: List[A])(f: A => Boolean): List[A] = flatMap(l)(x => if f(x) then Cons(x, Nil) else Nil)

// exr 22
def sumCorresponding(l1: List[Int], l2: List[Int]): List[Int] =
  @tailrec
  def go(l1: List[Int], l2: List[Int], acc: List[Int]): List[Int] = (l1, l2) match {
    case (Cons(x, xs), Cons(y, ys)) => go(xs, ys, Cons(x + y, acc))
    case (Nil, Nil) => acc
  }
  assert(length(l1) == length(l2))
  go(l1, l2, Nil)

// exr 23
def alphaCorresponding[A, B](l1: List[A], l2: List[A])(f: (A, A) => B): List[B] =
  @tailrec
  def go(l1: List[A], l2: List[A], acc: List[B]): List[B] = (l1, l2) match {
    case (Cons(x, xs), Cons(y, ys)) => go(xs, ys, Cons(f(x, y), acc))
    case (Nil, Nil) => Nil
  }

  assert(length(l1) == length(l2))
  go(l1, l2, Nil)


@tailrec
def drop[A](l: List[A], n: Int): List[A] = l match {
  case Nil => Nil
  case Cons(x, xs) => if n <= 0 then l else drop(xs, n - 1)
}
// exr 24
def hasSubsequence[A](l: List[A], sub: List[A]): Boolean =
  @tailrec
  def go(l: List[A], sub: List[A], n: Int): Boolean =
    if n < 0 then false
    else if n == 0 then l == sub
    else if reverse(drop(reverse(l), n)) == sub then true
    else go(drop(l, 1), sub, n - 1)



  val n = length(l) - length(sub) + 1


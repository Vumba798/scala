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
def reverse1[A](l: List[A]): List[A] = l match {
  case Cons(x, xs) => foldLeft(xs, Cons(x, Nil))((z, x) => Cons(x, z))
  case Nil => Nil
}

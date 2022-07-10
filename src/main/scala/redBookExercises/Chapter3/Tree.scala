package redBookExercises.Chapter3
/*

sealed trait Tree[+A]
case class Leaf[A](value: A) extends Tree[A]
case class Branch[A](left: Tree[A], right: Tree[A]) extends Tree[A]

object Tree {
  // exr 25
  def size[A](t: Tree[A]): Int = t match {
    case Leaf(_) => 1
    case Branch(left, right) => size(left) + size(right)
  }

  // exr 26
  def maximum(t: Tree[Int]): Int = t match {
    case Leaf(x) => x
    case Branch(l, r) => maximum(l) max maximum(r)
  }

  // exr 27
  def depth[A](t: Tree[A]): Int = t match {
    case Leaf(_) => 1
    case Branch(l, r) => 1 + depth(l)
  }

  // exr 28
  def map[A, B](t: Tree[A])(f: A => B): Tree[B] = t match {
    case Leaf(x) => Leaf(f(x))
    case Branch(l, r) => Branch(map(l)(f), map(r)(f))
  }

  // exr 29
  def fold[A, B](t: Tree[A])(f: A => B)(op: (B, B) => B): B = t match {
    case Branch(l, r) => op(fold(l)(f)(op), fold(r)(f)(op))
    case Leaf(x) => f(x)
  }

  def size1[A](t: Tree[A]): Int = fold(t)(x => 1)(_ + _)
  def maximum1(t: Tree[Int]): Int = fold(t)(x => x)(_ max _)
  def depth1[A](t: Tree[A]): Int = fold(t)(x => 1)(1 + _ max _)
}

 */
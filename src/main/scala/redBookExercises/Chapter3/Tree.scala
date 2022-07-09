package redBookExercises.Chapter3

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
    case Branch(l, r) => 1 + (depth(l))
  }


}


package redBookExercies

import scala.annotation.tailrec

class firstChapter {
  def isSorted[T](d: Array[T], f: (T, T) => Boolean): Boolean =
    @tailrec
    def go(head: T, tail: Array[T]): Boolean =
      if tail.isEmpty then true
      else if f(head, tail.head) then go(tail.head, tail.tail)
      else false

    go(d.head, d.tail)

  def partiall[A, B, C](a: A, f: (A, B) => C): B => C = (b: B) => f(a, b)

}

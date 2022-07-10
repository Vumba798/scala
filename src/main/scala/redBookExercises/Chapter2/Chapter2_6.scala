package redBookExercises

import scala.annotation.tailrec

// Polymorphic functions

  /*
def isSorted[T](d: Array[T], f: (T, T) => Boolean): Boolean =
  @tailrec
  def go(head: T, tail: Array[T]): Boolean =
    if tail.isEmpty then true
    else if f(head, tail.head) then go(tail.head, tail.tail)
    else false

  go(d.head, d.tail)

def partiall[A, B, C](a: A, f: (A, B) => C): B => C = (b: B) => f(a, b)

def curry[A, B, C](f: (A, B) => C): A => B => C =
  (a: A) => (b: B) => f(a, b)

def uncurry[A, B, C](f: A => B => C): (A, B) => C =
  (a: A, b: B) => f(a)(b)

def compose[A, B, C](f: B => C, g: A => B): A => C =
  (a: A) => f(g(a))

   */
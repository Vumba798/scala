package MyList

class Cons[T](val head: T, val tail: List[T]) extends List[T] {
  def isEmpty = false
}

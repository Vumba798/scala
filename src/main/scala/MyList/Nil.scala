package MyList

import java.util.NoSuchElementException

class Nil[T]() extends List[T] {
  def isEmpty = true
  def head = throw NoSuchElementException()
  def tail = throw NoSuchElementException()
}

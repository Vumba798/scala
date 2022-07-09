import scala.annotation.tailrec
import scala.collection.mutable.ListBuffer
import redBookExercises.*
import redBookExercises.Chapter3._

object Main extends App:
  /*
  val l1 = Cons(1, Cons(2, Cons(3, Nil)))
  val l2 = Cons(4, Cons(5, Cons(6, Nil)))
  println(append(l1, l2))
  */
  /*
  val l3 = Cons(1, Cons(2, Nil))
  val l4 = Cons(3, Cons(4, Nil))
  val l5 = Cons(l3, Cons(l4, Nil))
  println(filter(flat(l5))(x => x % 2 == 0))
  */
  /*
  val l6 = Cons(1, Cons(2, Cons(3, Cons(4, Nil))))
  println(map1(l6)(x => x % 2 == 0))
  */
  /*
  val l7 = Cons(1, Cons(2, Nil))
  val l8 = Cons(3, Cons(4, Nil))
  println(sumCorresponding(l7, l8))
  */
  val l9 = Cons(1, Cons(2, Cons(3, Nil)))
  val l10 = Cons(1, Cons(2, Nil))
  println(l9 == l10)
end Main



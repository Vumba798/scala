import scala.annotation.tailrec
import scala.collection.mutable.ListBuffer
import redBookExercises.*
import redBookExercises.Chapter3._

object Main extends App:
  val l = Cons(-3, Cons(-2, Cons(-1, Cons(0, Cons(1, Nil)))))
  val dropWhileNegative = dropWhile((x: Int) => x < 0)
  println(dropWhileNegative(l))
end Main



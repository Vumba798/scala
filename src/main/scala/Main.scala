import scala.annotation.tailrec
import scala.collection.mutable.ListBuffer
import redBookExercises.*
import redBookExercises.Chapter3._

object Main extends App:
  val l = Cons(1, Cons(2, Cons(3, Nil)))
  println(length(l))
end Main



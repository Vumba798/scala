import redBookExercises.Chapter4.Option.variance
import redBookExercises.Chapter5.Laziness._
import redBookExercises.Chapter5._

import scala.annotation.tailrec
import scala.collection.mutable.ListBuffer

object Main extends App {
  val s1 = Stream.from(1)
  val s2 = Stream.cons(2, Stream.empty)
  println(Stream.startsWith(s1, s2))
}



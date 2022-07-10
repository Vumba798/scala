import scala.annotation.tailrec
import scala.collection.mutable.ListBuffer
import redBookExercises.*
import redBookExercises.Chapter3.{Branch, Leaf}
import redBookExercises.Chapter3.Tree.*

object Main extends App:
  val t1 = Branch(Branch(Leaf(1), Leaf(4)), Leaf(3))
  println(maximum1(t1))
end Main



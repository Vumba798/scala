import scala.annotation.tailrec
import scala.collection.mutable.ListBuffer

object Main extends App:
  val arr = Array(1, 5, 3, 4)
  println(isSorted(arr, (x, y) => x < y))
end Main



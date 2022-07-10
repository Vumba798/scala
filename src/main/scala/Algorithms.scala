/*
class Algorithms {
  def insert(x: Int, xs: List[Int]): List[Int] = xs match {
    case Nil => x :: Nil
    case y :: ys =>
      if x < y then x :: y :: ys
      else y :: insert(x, ys)
  }
  def insertionSort(xs: List[Int]): List[Int] =
    xs match {
      case Nil => Nil
      case y :: ys => this.insert(y, insertionSort(ys))
    }
}


 */
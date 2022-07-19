package redBookExercises.Chapter5

object Laziness {
  def if2[A](cond: Boolean, onTrue: => A, onFalse: => A): A =
    if (cond) onTrue
    else onFalse

  def pair(i: => Int): (Int, Int) = (i, i)
  def pair2(i: => Int): (Int, Int) = {lazy val j = i; (j, j)}

}
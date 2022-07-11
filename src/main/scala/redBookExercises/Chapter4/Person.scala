package redBookExercises.Chapter4

// exr 9
case class Person(name: Name, age: Age)
sealed class Name(val value: String)
sealed class Age(val value: Int)

// exr 9
object Person {
  def mkName(name: String): Either[String, Name] =
    if (name == "" || name == null) Left("Name is empty")
    else Right(new Name(name))

  def mkAge(age: Int): Either[String, Age] =
    if (age < 0) Left("Age is out of range")
    else Right(new Age(age))

  def mkPerson(name: String, age: Int): Either[String, Person] = {
    val a = mkName(name)
    val b  = mkAge(age)
    (a, b) match {
      case (Left(ae), Left(be)) => Left(ae + ";" + be)
      case (Right(_), Left(be)) => Left(be)
      case (Left(ae), Right(_)) => Left(ae)
      case (Right(aa), Right(bb)) => Right(Person(aa, bb))
    }


  }
}


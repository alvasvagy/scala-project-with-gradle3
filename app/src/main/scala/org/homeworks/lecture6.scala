package org.homeworks

import scala.util.{Failure, Success, Try}

object Lec6ForCompr extends App {
  case class ClassWithOption(id: Long, urza: Option[Urza])

  case class Urza(name: Option[String])

  val cwo  = Option(ClassWithOption(1, Some(Urza(Some("urza"))))) //

  // flatMap возвращает Option с результатами применения функции
  val name = cwo.flatMap(_.urza).flatMap(_.name) // из cwo получаем option(Urza(...)),
  // из него получаем option с именем функции (string)


  // Во что раскладывается for Comp
  val nameCorrect = cwo.flatMap { cwo =>
    cwo.urza.flatMap { urzo =>
      urzo.name.map { n =>
        n.capitalize
      }
    }
  }
  // ниже For-comprehension as syntactic sugar for nested map and flatMap calls
  val name1: Option[String] = for {
    cwo1: ClassWithOption <- cwo
    urza <- cwo1.urza
    name <- urza.name
  } yield name
  // тоже самое
  val name2 = for {
    ClassWithOption(_, n) <- cwo
    Urza(u)               <- n
    name                  <- u if name.nonEmpty
  } yield name

  println(s"name: $name")
  println(s"name1: $name1")
  println(s"name2: $name2")
}

object Lec6ForComprCustom extends App {
  trait ForCompSupport[A] {
    // реализация flatMap, map, etc
    // просто код из сурса скалы
    def flatMap[B](f: A => ForCompSupport[B]): ForCompSupport[B] =
      if (isEmpty) ForCompSupportEmpty[B]() else f(this.get)

    def map[B](f: A => B): ForCompSupport[B] =
      if (isEmpty) ForCompSupportEmpty[B]() else ForCompSupportTest(f(this.get))

    def withFilter(p: A => Boolean): ForCompSupport[A] =
      if (isEmpty || p(this.get)) this else ForCompSupportEmpty[A]()

    def isEmpty: Boolean = this == ForCompSupportEmpty[A]()

    def get: A
  }

  object ForCompSupport {
    def apply[A](i: A): ForCompSupport[A] = if (i == null) ForCompSupportEmpty() else ForCompSupportTest(i)
  }

  case class ForCompSupportEmpty[A]() extends ForCompSupport[A] {
    override def get: A = throw new Exception("ForCompSupportEmptty !!!")
  }

  case class ForCompSupportTest[A](i: A) extends ForCompSupport[A] {
    override def get: A = i
  }

  val fcst   = ForCompSupport(2).map(_ * 2)
  val fcsStr = ForCompSupport("any")

  val x = for {
    x <- fcst
    if x > 2
    x1 <- fcsStr
  } yield (x, x1)
}

object Lec6Try extends App {
  // from doc:
  // The Try type represents a computation that may fail during evaluation by raising an exception
  val maybeThrowException: Try[Int] = Try {
    1 / 0
  }

  val maybeThrowExceptionV2 = Try {
    1 / 1
  }

  maybeThrowException match {
    case Failure(exception) => println("exception")
    case Success(value)     => println(value)
  }

  val isSuccess: Boolean = maybeThrowException.isSuccess
  val isFailure = maybeThrowException.isFailure
  //  val getMaybe = maybeThrowException.get
  val getOrElse = maybeThrowException.getOrElse(3d)
  val mapTry    = maybeThrowException.map(_ * 2)
  val filterTry = mapTry.filter(_ < 2)
  maybeThrowException.foreach(println)

  val recoverFail = maybeThrowException.recover {
    case _: ArithmeticException =>
      println("Arichmetic")
      4d

    case x: RuntimeException =>
      println("fail")
      3d
  }
  println(s"recoverFail: ${recoverFail}")

  val newX = maybeThrowException.flatMap(_ => maybeThrowExceptionV2)

  val newX1 = for {
    n  <- maybeThrowException
    n1 <- maybeThrowExceptionV2
  } yield n1

  val x = Try {
    1 / 0
  }.toOption

}

object Lec6Either extends App {
  // Either => Left(A) or Right(B)
  def getInforFromUrL(url: String): Either[String, Int] = {
    if (url.isEmpty) Left("Empty url!")
    else Right(url.length)
  }

  val res1: Either[String, Int] = getInforFromUrL("AAA")
  println(res1)
  val res2: Either[String, Int] = getInforFromUrL("")
  println(res2)

  getInforFromUrL("BBB") match {
    case Left(valueLeft)   => println(valueLeft)
    case Right(valueRight) => println(valueRight)
  }

  getInforFromUrL("CCC").map(_.toLong)

  getInforFromUrL("DDD").left
  getInforFromUrL("DDD").right

  getInforFromUrL("EEE").flatMap { x =>
    getInforFromUrL("ZZZ").map { y => x + y }
  }

  val eezz: Either[String, Int] = for {
    eee <- getInforFromUrL("EEE")
    zzz <- getInforFromUrL("ZZZ")
  } yield eee + zzz

  val x: Option[Int] = getInforFromUrL("GGG").toOption

  case class PersonalInfo(id: Long, contractNumber: String, phone: String)
  val infos = List(
    PersonalInfo(1, "43", "99"),
    PersonalInfo(2, "", "1"),
    PersonalInfo(3, "99", ""),
    PersonalInfo(4, "11", "43"),
    PersonalInfo(5, "24", "22"),
  )

  def insertToDb(pi: PersonalInfo): Unit = println(s"Insert to DB : ${pi}")
  val result = infos.map {
    case pi: PersonalInfo if pi.contractNumber.isEmpty =>
      Left(s"${pi} => contractNumber is empty")

    case pi: PersonalInfo if pi.phone.isEmpty =>
      Left(s"$pi => phone is empty")

    case pi =>
      insertToDb(pi)
      Right(pi.id)
  }

  println("----------------------------")
  val resultStr = result.mkString(",\n")
  println(resultStr)

}


// Homework
object Homework extends App {
  def tryDivide (a:Int, b:Int): Try[Int] = {
    Try {a / b}
  }

  // no idea что значит сделать через options, пусть будет так
  def OptionDivide(a:Int, b:Int):Option[Int] = {
    b match
    {
      case 0 => None
      case _ => Option(a/b)
    }
  }

  def EitherDivide(a:Int, b:Int):Either[Int, String] = {
    b match
    {
      case 0 => Right("How dare thou divide by zero")
      case _ => Left(a/b)
    }
  }



}
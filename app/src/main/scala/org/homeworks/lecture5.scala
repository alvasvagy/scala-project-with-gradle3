


package org.example

object ScalaOption extends App {

  // Option(any) => Some(any) or None
  val o1 = Option(1)         // Some(1)
  val o2 = Option.empty[Int] // None
  val o3 = None              // None
  val o4 = Some(1)           // Some(1)
  val o5 = Option(null)      // None
  // val o6 = Some(null)

  val b1: Boolean = o1.isEmpty // true если none, иначе false 
  val b2: Boolean = o1.isDefined 
  val b3: Boolean = o1.nonEmpty // обратная к isEmpty

  val b4: Boolean = o1.contains(1) // содержится ли элемент

  val o_1: Option[Int] = o1.filter(_ > 0)    // возвращает элемент по предикату
  val o_2: Option[Int] = o1.filterNot(_ > 0) // Some(...) or None

  //option match {   case Some(x) => p(x)   case None    => false }
  val b5: Boolean = o1.exists(_ > 0) // true if p(x) == true, существует ли элемент удолетворяющий предикату. 
  //option match {   case Some(x) => p(x)   case None    => true }
  val b6 = o1.forall(_ > 0) // true if option.isEmpty or p(x) == true

  val i  = o1.get                                      // None => NoSuchElementException
  val i1 = o1.getOrElse(2)                             // if None => 2
  val i2 = o1.getOrElse(throw new Exception("babah!")) // if None => new Exception
}


/* 
flatten эквивалентен 
  option match {
  case Some(Some(b)) => Some(b)
  case _             => None
  }
 */
object ScalaOptionSomeSome extends App { 
  val o: Option[Option[Int]] = Option(Option(1)) 
  // [[x]] flatten => [x]
  // [[[x]]] flatten => [[x]]
  // [[[x]]] flatten flatten => [x]
  val x: Option[Int] = o.flatten
  println(x)                    // Some(1)
  println(Option(None).flatten) // None
}



/* 
map эквивалентно 
option match {
  case Some(x) => Some(f(x))
  case None    => None
} */
object ScalaOptionMap extends App {
  val o = Option(1)
  o.map(_ + 1)
  o.map(i => i + 1)
  o.map((i: Int) => i + 1)

  val o1 = o.map(_ + 1).map(_ + 1).map(_ + 1)
  println(o1) // Some(4)
  println("--------------")
  val o2 = o
    .map { i =>
      println(i)
      i + 1
    }
    .map { i =>
      println(i)
      i + 2
    }
    .filter { _ > 6 }
    .map { i =>
      println(i)
      i * 2
    }
  println(o2)
  println("----------------")
  println(o)
}

object ScalaOptionMapSomeSome extends App {
  val o = Option(Option(1))
  o.map(o => o.map(_ + 1)).flatten
  o.flatMap(o => o.map(_ + 1))
}


object OptionForeach extends App {
  val o = Option(1)
  o.foreach(println) // применяем функцию к значению в Option, если не None
  o.foreach(println(_))
  o.foreach(x => println(x))
}

object OptionMapDef extends App {
  val o                = Option(1)
  def inc(i: Int)      = i + 1
  val inc1: Int => Int = _ + 1
  println(o.map(inc1)) // Some(2)
  println(o.map(inc))  // Some(2)
}

object OptionWhen extends App { // when возвращает Some(a) если true, None иначе 
  val o = Option.when(1 == 2)("123")
  println(o) // None
  val o1 = Option.when(1 != 2)("123")
  println(s"o1 = ${o1}") // Some(123)
}

//val o_1: Option[Int] = o1.filter(_ > 0)

object OptionString {
  def checkstring(stri:Option[String], String str){
      if stri.exists(_ == str) println("Слово найдено") else println("Слово не найдено")
  }
}
package org.example


trait Figure(){
  def area():Double
} 

case class Circle(radius:Double) extends Figure {
  override def area(): Double = 2 * radius * Math.PI
}

case class Rectangle(firstside:Double, secondside:Double) extends Figure{
  override def area(): Double = firstside * secondside
}
case class Square(oneside:Double) extends Figure{
  override def area(): Double = Math.pow(oneside, 2)
}



object Lecture4 {

  def figure_checking(fig:Figure) = {
    fig match {
      case Circle(radius:Double) if radius >= 0 => println(s"Circle's radius is about ${radius.toInt}")
      case Rectangle(firstside, secondside)   => println(s"Rectangle's area is  ${fig.area()}" )
      case Square(oneside) => println(s"Isnt square some kind of rectangle? Idk")
    }
  }

  def type_figure_checking(fig:Figure) = {
    fig match {
      case Circle => println(s"Circle")
      case Rectangle => println(s"Rectangle" )
      case Square => println(s"Square")
    }
  }
  def main(args: Array[String]): Unit = {
    val r:Rectangle = new Rectangle(22.3, 21.2)
    figure_checking(r)
  }
}

package org.example

object Book{
  def createBook(auth:String, title:String, Year:Int):Book = {
    var bk:Book = new Book
    bk.Author = auth
    bk.Title = title
    bk.PublishedYear = Year
    bk
  }
}

class Book(){
  var Author:String = ""
  var Title:String = ""
  var PublishedYear:Int = 0
  override def toString(): String = s"Author: ${Author}\nTitle:${Title}\nPublished in the year: ${PublishedYear}"
}


class Figure(){
  
  def area():Double = 0
} 

case class Circle(radius:Double) extends Figure {
  override def area(): Double = 2 * radius * Math.PI
}

case class Rectangle(Firstside:Double, SecondSide:Double) extends Figure{
  override def area(): Double = Firstside * SecondSide;
}

object Lecture3 {
  def main(args: Array[String]): Unit = {
    val booke:Book = Book.createBook("Charles Dickens", "Great expectations", 1888)
    ShowArea(new Rectangle(39, 2))
    ShowArea(new Circle(5))
  }


  def ShowArea(Fig:Figure):Unit = {
    println(Fig.area())
  }
}

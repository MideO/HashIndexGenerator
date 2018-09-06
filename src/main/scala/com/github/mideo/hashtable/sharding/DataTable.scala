package com.github.mideo.hashtable.sharding


import com.github.mideo.hashtable.sharding.exceptions.{
  InvalidCoordinateException,
  OutOfRangeException,
  RowLimitReachedException
}

import scala.collection.mutable.{ArrayBuffer, ListBuffer}

sealed case class Dimension(x: Int, y: Int)

sealed case class Coordinate(x: Int, y: Int)

sealed case class Column(number: Int)


sealed class DataTable[T](dimension: Dimension) {
  def freeCoordinates(): List[Coordinate] = (0 until dimension.x).flatMap {
    x =>
      (0 until (dimension.y - internal(x).length)) map {
        Coordinate(x, _)
      }
  } toList

  private[sharding] val internal: List[ArrayBuffer[T]] = if (dimension.x <= 0 || dimension.y <=0) throw InvalidCoordinateException("Invalid Coordinates")
                                                else ListBuffer.fill(dimension.x)(new ArrayBuffer[T]).toList


  def put(t: T, col: Column): Coordinate = {
    col.number match {
      case index if index > dimension.x || index <= 0 => throw OutOfRangeException("Coordinate of range")
      case index =>
        val bucket = internal(index - 1)
        bucket.length match {
          case i if i < dimension.y =>
            bucket.append(t)
            Coordinate(col.number, bucket.length)
          case _ => throw RowLimitReachedException("Coordinate Already Populated")
        }
    }

  }
  def get(coordinates: Coordinate): T = internal(coordinates.x - 1)(coordinates.y - 1)
}


object DataTable {
  def apply[T](dimension: Dimension): DataTable[T] = {
    new DataTable(dimension)
  }
}

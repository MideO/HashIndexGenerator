package com.github.mideo.hashtable.sharding

import com.github.mideo.hashindex.exceptions.exceptions.{
  InvalidCoordinateException,
  OutOfRangeException,
  RowLimitReachedException
}

import scala.collection.mutable.{ArrayBuffer, ListBuffer}

sealed case class Dimension(x: Int, y: Int)

sealed case class Coordinate(x: Int, y: Int)

sealed case class ColumnSize(number: Int)


sealed class DataTable[T](dimension: Dimension) {
  def freeCoordinates(): List[Coordinate] = (0 until dimension.x).flatMap {
    x =>
      (0 until (dimension.y - dataTable(x).length)) map {
        Coordinate(x, _)
      }
  } toList

  private val dataTable: List[ArrayBuffer[T]] = if (dimension.x <= 0 || dimension.y <=0) throw InvalidCoordinateException("Invalid Coordinates")
                                                else ListBuffer.fill(dimension.x)(new ArrayBuffer[T]).toList


  def put(t: T, row: ColumnSize): Coordinate = {
    row.number match {
      case index if index > dimension.x || index <= 0 => throw OutOfRangeException("Coordinate of range")
      case index =>
        val bucket = dataTable(index - 1)
        bucket.length match {
          case i if i < dimension.x =>
            bucket.append(t)
            Coordinate(row.number, bucket.length)
          case _ => throw RowLimitReachedException("Coordinate Already Populated")
        }
    }

  }

  def get(coordinates: Coordinate): T = dataTable(coordinates.x - 1)(coordinates.y - 1)
}


object DataTable {
  def apply[T](dimension: Dimension): DataTable[T] = {
    new DataTable(dimension)
  }
}

package com.github.mideo.hashindex

import scala.collection.mutable.ArrayBuffer

case class Dimension(rows: Int, columns: Int)

case class Coordinates(row: Int, columnIndex: Int)

case class CoordinateAlreadyPopulatedException(private val message: String = "",
                                               private val cause: Throwable = None.orNull) extends Exception(message, cause)



class HashDataTable(dimension: Dimension) {
  private val columns: ArrayBuffer[AnyVal] = explode[AnyVal](dimension.columns, Int.MinValue)
  private val hashTable: ArrayBuffer[ArrayBuffer[AnyVal]] = explode[ArrayBuffer[AnyVal]](dimension.rows, columns)
  private def explode[K](pieces: Int, item: K): ArrayBuffer[K] = {
    ArrayBuffer(List.fill(pieces)(item): _*)
  }


  def put(t: AnyVal, coordinates: Coordinates): Unit = {
    hashTable(coordinates.row - 1)(coordinates.columnIndex - 1) match {
      case i if i != Int.MinValue => throw CoordinateAlreadyPopulatedException("Coordinate Already Populated")
      case _ => hashTable(coordinates.row - 1)(coordinates.columnIndex - 1) = t
    }
  }


  def get(coordinates: Coordinates): AnyVal = hashTable(coordinates.row - 1)(coordinates.columnIndex - 1)
}


object HashDataTable {
  def apply(dimension: Dimension): HashDataTable = {
    new HashDataTable(dimension)
  }
}

package com.github.mideo.hashindex

import scala.collection.mutable.ArrayBuffer

trait MaxSizeCollision {
  def detectCollision[T](row: Int, maxLength: Int, table: List[ArrayBuffer[T]]): Boolean = {
    table.length match {
      case length if length < row => true
      case _ =>  table(row - 1).length >= maxLength
    }
  }
}

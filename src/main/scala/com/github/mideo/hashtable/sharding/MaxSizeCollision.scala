package com.github.mideo.hashtable.sharding

import scala.collection.mutable.ArrayBuffer

trait MaxSizeCollision extends CollisionDetection {
  def detectCollision[T](row: Int, maxLength: Int, table: List[ArrayBuffer[T]]): Boolean = {
    table.length match {
      case length if length < row => true
      case _ => table(row - 1).length >= maxLength
    }
  }
}

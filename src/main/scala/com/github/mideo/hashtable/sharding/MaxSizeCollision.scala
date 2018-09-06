package com.github.mideo.hashtable.sharding

import scala.collection.mutable.ArrayBuffer

trait MaxSizeCollision extends CollisionDetection {
  def detectCollision[T](column: Int, maxLength: Int, table: List[ArrayBuffer[T]]): Boolean = {
    table.length match {
      case length if length < column => true
      case _ => table(column - 1).length == maxLength
    }
  }
}

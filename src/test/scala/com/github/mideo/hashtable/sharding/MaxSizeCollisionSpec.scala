package com.github.mideo.hashtable.sharding

import scala.collection.mutable.ArrayBuffer

class MaxSizeCollisionSpec
  extends HashTableShardingSpec {

  object Subject extends MaxSizeCollision

  it should "detect collision when dataTable columns are occupied to max size" in {
    forAll(Table("column", 1, 2, 3)) {
      column => Subject.detectCollision(column, 3, List(ArrayBuffer(1, 2, 3), ArrayBuffer(1, 2, 3), ArrayBuffer(1, 2, 3))) should equal(true)
    }
  }

  it should "detect collision when dataTable column does not exist" in {
    Subject.detectCollision(4, 3, List(ArrayBuffer(1, 2, 3), ArrayBuffer(1, 2, 3), ArrayBuffer(1, 2, 3))) should equal(true)

  }

  it should "not detect collision when dataTable column is not occupied to max size" in {
    Subject.detectCollision(2, 3, List(ArrayBuffer(1, 2, 3), ArrayBuffer(1, 2), ArrayBuffer(1, 2, 3))) should equal(false)
  }

}

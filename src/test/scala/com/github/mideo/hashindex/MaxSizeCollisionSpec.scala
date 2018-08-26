package com.github.mideo.hashindex

import scala.collection.mutable.ArrayBuffer

class MaxSizeCollisionSpec
  extends HashIndexTableSpec {

  object Subject extends MaxSizeCollision

  it should "detect collision when dataTable rows are occupied to max size" in {
    forAll(Table("row", 1, 2, 3)) {
      row => Subject.detectCollision(row, 3, List(ArrayBuffer(1, 2, 3), ArrayBuffer(1, 2, 3), ArrayBuffer(1, 2, 3))) should equal(true)
    }
  }

  it should "detect collision when dataTable row does not exist" in {
    Subject.detectCollision(4, 3, List(ArrayBuffer(1, 2, 3), ArrayBuffer(1, 2, 3), ArrayBuffer(1, 2, 3))) should equal(true)

  }

  it should "not detect collision when dataTable row is not occupied to max size" in {
    Subject.detectCollision(2, 3, List(ArrayBuffer(1, 2, 3), ArrayBuffer(1, 2), ArrayBuffer(1, 2, 3))) should equal(false)
  }

}

package com.github.mideo.hashindex

import scala.annotation.tailrec

trait Hashing {

  val Prime = BigInt(379)

  @tailrec final def hash(bigInt: BigInt, limit: Int): Int = {
    math.abs((bigInt % limit + 1).toInt) match {
      case i if i > limit => hash(bigInt / Prime, limit)
      case i if i == 0 => hash(bigInt * Prime, limit)
      case i => i % 100
    }
  }
}
package com.github.mideo.hashtable.sharding

import scala.annotation.tailrec

trait HashFunction {
  private val BigPrime = 10657


  private def generateNumber(a: Any): BigInt = {
    BigInt((a.toString.toCharArray map {
      Character.getNumericValue
    }).reduce(BigPrime * _ + BigPrime * _) * BigPrime)
  }

  @tailrec final def apply(a: Any, limit: Int): Int = {
    val bigInt: BigInt = generateNumber(a)
    math.abs((bigInt % limit + 1).toInt) match {
      case i if i > limit => apply(bigInt / BigPrime, limit)
      case i if i == 0 => apply(bigInt * BigPrime, limit)
      case i => i % 100
    }
  }
}
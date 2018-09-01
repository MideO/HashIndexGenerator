package com.github.mideo.hashindex

object RandomGenerator {
  def generateNumber(a: Any): BigInt = {
    val bigPrime = 10657
      BigInt((a.toString.toCharArray map {
      Character.getNumericValue
    }).reduce(bigPrime * _ + bigPrime * _) * bigPrime)
  }
}

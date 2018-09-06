package com.github.mideo.hashtable.sharding

import java.util.UUID

class HashFunctionSpec extends HashTableShardingSpec {
  val limit: Int = 100

  object AHashFunction extends HashFunction

  it should "generate a random int within bucket limit for a given object" in {
    val s: String = UUID.randomUUID().toString


    withClue(s"${AHashFunction(s, limit)} is less than limit 0") {
      AHashFunction.apply(s, limit) > 0 should be(true)
    }
    withClue(s"${AHashFunction(s, limit)} is greater than limit $limit") {
      AHashFunction.apply(s, limit) < limit should be(true)
    }
  }

  it should "generate a same int with different limits for a given object" in {
    val s: String = UUID.randomUUID().toString

    withClue(s"${AHashFunction(s, limit)} is not same as ${AHashFunction.apply(s, 200)}") {
      AHashFunction.apply(s, limit) should equal(AHashFunction.apply(s, 200))
    }
  }


  it should "always generate the same number for same string" in {
    val s: String = UUID.randomUUID().toString
    AHashFunction.apply(s, limit) should equal(AHashFunction(s, limit))
  }


  it should "generate unique number" in {
    AHashFunction(UUID.randomUUID().toString, limit) should not equal AHashFunction(UUID.randomUUID().toString, limit)
  }

  it should "generate unique numbers" in {
    val dataMap: List[Int] =
      (((0 until 1000000) map {
        _ => AHashFunction(UUID.randomUUID().toString, limit)
      } groupBy identity mapValues (_.size)).values toList) sorted

    withClue(s"Data not distributed across all buckets") {
      dataMap.size.toDouble / limit.toDouble should equal(1)
    }


  }

}

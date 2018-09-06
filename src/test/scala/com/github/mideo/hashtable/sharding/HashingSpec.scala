package com.github.mideo.hashtable.sharding

import java.util.UUID

class HashingSpec extends HashIndexTableSpec {
  val limit: Int = 100

  object Subject extends Hashing

  it should "generate a random int within bucket limit for a given object" in {
    val s: String = UUID.randomUUID().toString


    withClue(s"${Subject.hash(s, limit)} is less than limit 0") {
      Subject.hash(s, limit) > 0 should be(true)
    }
    withClue(s"${Subject.hash(s, limit)} is greater than limit $limit") {
      Subject.hash(s, limit) < limit should be(true)
    }
  }

  it should "generate a same int with different limits for a given object" in {
    val s: String = UUID.randomUUID().toString

    withClue(s"${Subject.hash(s, limit)} is not same as ${Subject.hash(s, 200)}") {
      Subject.hash(s, limit) should equal(Subject.hash(s, 200))
    }
  }


  it should "always generate the same number for same string" in {
    val s: String = UUID.randomUUID().toString
    Subject.hash(s, limit) should equal(Subject.hash(s, limit))
  }


  it should "generate unique number" in {
    Subject.hash(UUID.randomUUID().toString, limit) should not equal Subject.hash(UUID.randomUUID().toString, limit)
  }

  it should "generate unique numbers" in {
    val dataMap: List[Int] =
      (((0 until 1000000) map {
        _ => Subject.hash(UUID.randomUUID().toString, limit)
      } groupBy identity mapValues (_.size)).values toList) sorted

    withClue(s"Data not distributed across all buckets") {
      dataMap.size.toDouble / limit.toDouble should equal(1)
    }


  }

}

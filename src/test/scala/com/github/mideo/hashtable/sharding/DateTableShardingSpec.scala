package com.github.mideo.hashtable.sharding

import java.util.UUID

class DateTableShardingSpec extends HashTableShardingSpec {

  "DateTableSharding" should "distribute data across dataTable" in {
    forAll(Table("dimension", Dimension(20, 1), Dimension(4, 4), Dimension(20, 1000))) {
      dimension =>
        DateTableSharding[String](dimension)
          .shard((1 to dimension.x * dimension.y)
            map { _ => UUID.randomUUID().toString } toList).freeCoordinates() should equal(List.empty[Coordinate])
    }

  }
}
